package dataframe;

import dataframe.api.Functions;
import dataframe.transformation.ColumnTransformation;
import dataframe.transformation.any.Identity;
import dataframe.transformation.bool.Not;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static dataframe.api.Functions.*;
import static org.junit.jupiter.api.Assertions.*;

class DataFrameTests {

    private final DataFrame dataframe;

    DataFrameTests() {
        var data = List.of(
                new Object[]{1, "parth", "pandya", true, "a/b/c"},
                new Object[]{2, "arpan", "shah", true, "d/e/f"},
                new Object[]{3, "ankit", "rathavi", false, "g/h/i"}
        );
        var columns = new String[]{"id", "first_name", "last_name", "is_experienced", "path"};
        dataframe = DataFrame.create(data, columns);
    }

    @Test
    void withColumnTest() {
        var dataframeCopy = dataframe.copy();
        dataframeCopy = dataframeCopy.withColumn("not_experienced", not("is_experienced"));
        dataframeCopy = dataframeCopy.withColumn("name", concat_ws(" ", "first_name", "last_name"));
        dataframeCopy = dataframeCopy.withColumn("first_name", capitalize("first_name"));
        dataframeCopy = dataframeCopy.withColumn("path_components", split("path", "/"));
        dataframeCopy = dataframeCopy.withColumn("dept", lit("IT"));
        dataframeCopy = dataframeCopy.withColumn("created_at", currentTimestamp());
        dataframeCopy = dataframeCopy.drop("is_experienced", "last_name");

        var expectedColumns = List.of(
                "id", "first_name", "path", "not_experienced",
                "name", "path_components", "dept", "created_at"
        );
        assertTrue(dataframeCopy.getColumns().containsAll(expectedColumns));
    }

    @Test
    void dropColumnsTest() {
        var dropColumns = new String[]{"id", "first_name", "last_name"};
        var remainingColumns = new String[]{"is_experienced", "path"};
        var newDataFrame = dataframe.drop(dropColumns);
        var newColumns = newDataFrame.getColumns().toArray(String[]::new);
        assertEquals(remainingColumns.length, newColumns.length);
        assertArrayEquals(remainingColumns, newColumns);
    }

    @Test
    void selectColumnsTest() {
        var selectColumns = new String[]{"id", "first_name", "last_name"};
        var newDataFrame = dataframe.select(selectColumns);
        var newColumns = newDataFrame.getColumns().toArray(String[]::new);
        assertEquals(3, newColumns.length);
        assertArrayEquals(selectColumns, newColumns);
    }

    @Test
    void emptyTest() {
        var emptyDataFrame = DataFrame.empty();
        assertEquals(0, emptyDataFrame.getColumns().size());
        assertEquals("", emptyDataFrame.toString());
    }

    @Test
    void copyTest() {
        var dataframeCopy = dataframe.copy();
        var dataframeString = dataframe.toString();
        var dataframeCopyString = dataframeCopy.toString();
        assertEquals(dataframeCopyString, dataframeString);
    }

    @Test
    void countTest() {
        assertEquals(3, dataframe.count());
    }

    @Test
    void whereTest() {
        var isExperiencedFilter = ColumnTransformation.of(new Identity(), "is_experienced");
        var newDataFrame = dataframe.where(isExperiencedFilter);
        assertEquals(2, newDataFrame.count());
        var notExperiencedFilter = ColumnTransformation.of(new Not(), "is_experienced");
        var newDataFrame2 = dataframe.where(notExperiencedFilter);
        assertEquals(1, newDataFrame2.count());
    }

    @Test
    void joinTest() {
        var projectsData = List.of(
                new Object[]{1, 1, "NCBI", "Data Engineer"},
                new Object[]{2, 1, "Gogotech", "Data Engineer"},
                new Object[]{3, 2, "NCBI", "Data Engineer"},
                new Object[]{4, 2, "IKEA", "Data Engineer"}
        );
        var columns = new String[]{"project_id", "employee_id", "project_name", "designation"};
        var projectsDataFrame = DataFrame.create(projectsData, columns);
        var joinedDataFrame = dataframe.join(projectsDataFrame, "id", "employee_id");
        System.out.println(joinedDataFrame);
        assertEquals(5, joinedDataFrame.count());
        assertTrue(
                joinedDataFrame.getColumns().containsAll(dataframe.getColumns())
                        && joinedDataFrame.getColumns().containsAll(Arrays.asList(columns))
        );
        var projectIdForEmployee1 = joinedDataFrame
                .where(Functions.isEquals("id", 1))
                .select("project_id");
        for (var row : projectIdForEmployee1.getRows()) {
            assertNotNull(row[0]);
        }
        var projectIdForEmployee3 = joinedDataFrame
                .where(Functions.isEquals("id", 3))
                .select("project_id");
        for (var row : projectIdForEmployee3.getRows()) {
            assertNull(row[0]);
        }
    }

    @Test
    void groupByTest() {
        var groupedDataFrame = dataframe.groupBy("is_experienced");
        var isExperienced = dataframe.where(Functions.col("is_experienced"));
        var isNotExperienced = dataframe.where(Functions.not("is_experienced"));

        assertEquals(isExperienced, groupedDataFrame.getDataFrame(true));
        assertEquals(isNotExperienced, groupedDataFrame.getDataFrame(false));
    }

    @Test
    void unionAllTest() {
        var newData = List.of(
                new Object[]{4, "kruti", "mehta", false, "p/q/r"},
                new Object[]{5, "deep", "erda", true, "m/n/o"}
        );
        var columns = new String[]{"id", "first_name", "last_name", "is_experienced", "path"};
        var expectedData = List.of(
                new Object[]{1, "parth", "pandya", true, "a/b/c"},
                new Object[]{2, "arpan", "shah", true, "d/e/f"},
                new Object[]{3, "ankit", "rathavi", false, "g/h/i"},
                new Object[]{4, "kruti", "mehta", false, "p/q/r"},
                new Object[]{5, "deep", "erda", true, "m/n/o"}
        );
        var otherDataFrame = DataFrame.create(newData, columns);
        var expectedDF = DataFrame.create(expectedData, columns);
        var actualDF = dataframe.unionAll(otherDataFrame);
        assertEquals(expectedDF, actualDF);
    }

    @Test
    void concatTest() {
        var newData = List.of(
                new Object[] {"A,B"},
                new Object[] {"A"},
                new Object[] {"A"}
        );
        var columns = new String[]{"project"};
        var expectedData = List.of(
                new Object[]{1, "parth", "pandya", true, "a/b/c", "A,B"},
                new Object[]{2, "arpan", "shah", true, "d/e/f", "A"},
                new Object[]{3, "ankit", "rathavi", false, "g/h/i", "A"}
        );
        var expectedColumns =new String[]{"id", "first_name", "last_name", "is_experienced", "path", "project"};
        var df = DataFrame.create(newData, columns);
        var expectedDF = DataFrame.create(expectedData, expectedColumns);
        var actualDF = dataframe.concat(df);
        assertEquals(expectedDF, actualDF);
    }

    @Test
    void sortAscTest(){
        var dfList = List.of(
                new Object[]{1, "parth", "pandya", true, "a/b/c", "A,B"},
                new Object[]{2, "arpan", "shah", true, "d/e/f", "A"},
                new Object[]{3, "ankit", "rathavi", false, "g/h/i", "A"}
        );
        var expectedList = List.of(
                new Object[]{3, "ankit", "rathavi", false, "g/h/i", "A"},
                new Object[]{2, "arpan", "shah", true, "d/e/f", "A"},
                new Object[]{1, "parth", "pandya", true, "a/b/c", "A,B"}
        );
        var columns =new String[]{"id", "first_name", "last_name", "is_experienced", "path", "project"};
        var df = DataFrame.create(dfList, columns);
        var expectedDF = DataFrame.create(expectedList, columns);
        var newDF = df.sort("first_name");
        assertEquals(newDF, expectedDF);

    }

    @Test
    void sortDescTest(){
        var dfList = List.of(
                new Object[]{2, "ankit", "rathavi", false, "g/h/i", "A"},
                new Object[]{1, "parth", "pandya", true, "a/b/c", "A,B"},
                new Object[]{3, "arpan", "shah", true, "d/e/f", "A"}
        );
        var expectedList = List.of(
                new Object[]{3, "arpan", "shah", true, "d/e/f", "A"},
                new Object[]{2, "ankit", "rathavi", false, "g/h/i", "A"},
                new Object[]{1, "parth", "pandya", true, "a/b/c", "A,B"}
        );
        var columns =new String[]{"id", "first_name", "last_name", "is_experienced", "path", "project"};
        var df = DataFrame.create(dfList, columns);
        var expectedDF = DataFrame.create(expectedList, columns);
        var newDF = df.sort("id", "desc");
        assertEquals(newDF, expectedDF);

    }
}
