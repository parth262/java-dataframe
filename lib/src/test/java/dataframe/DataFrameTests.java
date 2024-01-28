package dataframe;

import dataframe.transformation.ColumnTransformation;
import dataframe.transformation.any.Identity;
import dataframe.transformation.bool.Not;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dataframe.api.Functions.*;
import static org.junit.jupiter.api.Assertions.*;

class DataFrameTests {

    private final DataFrame dataframe;

    DataFrameTests() {
        var data = List.of(
            new Object[]{ 1, "parth", "pandya", true, "a/b/c" },
            new Object[]{ 2, "arpan", "shah", true, "d/e/f" },
            new Object[]{ 3, "ankit", "rathavi", false, "g/h/i" }
        );
        var columns = new String[] {"id", "first_name", "last_name", "is_experienced", "path"};
        dataframe = DataFrame.create(data, columns);
    }

    @Test void withColumnTest() {
        var dataframeCopy = dataframe.copy();
        dataframeCopy = dataframeCopy.withColumn("not_experienced", not("is_experienced"));
        dataframeCopy = dataframeCopy.withColumn("name", concat_ws(" ", "first_name", "last_name"));
        dataframeCopy = dataframeCopy.withColumn("first_name", capitalize("first_name"));
        dataframeCopy = dataframeCopy.withColumn("path_components", split("/", "path"));
        dataframeCopy = dataframeCopy.withColumn("dept", lit("IT"));
        dataframeCopy = dataframeCopy.withColumn("created_at", currentTimestamp());
        dataframeCopy = dataframeCopy.drop("is_experienced", "last_name");

        var expectedColumns = List.of(
            "id", "first_name", "path", "not_experienced",
            "name", "path_components", "dept", "created_at"
        );
        assertTrue(dataframeCopy.getColumns().containsAll(expectedColumns));
    }

    @Test void dropColumnsTest() {
        var dropColumns = new String[] {"id", "first_name", "last_name"};
        var remainingColumns = new String[] {"is_experienced", "path"};
        var newDataFrame = dataframe.drop(dropColumns);
        var newColumns = newDataFrame.getColumns().toArray(String[]::new);
        assertEquals(remainingColumns.length, newColumns.length);
        assertArrayEquals(remainingColumns, newColumns);
    }

    @Test void selectColumnsTest() {
        var selectColumns = new String[] {"id", "first_name", "last_name"};
        var newDataFrame = dataframe.select(selectColumns);
        var newColumns = newDataFrame.getColumns().toArray(String[]::new);
        assertEquals(3, newColumns.length);
        assertArrayEquals(selectColumns, newColumns);
    }

    @Test void emptyTest() {
        var emptyDataFrame = DataFrame.empty();
        assertEquals(0, emptyDataFrame.getColumns().size());
        assertEquals("", emptyDataFrame.toString());
    }

    @Test void copyTest() {
        var dataframeCopy = dataframe.copy();
        var dataframeString = dataframe.toString();
        var dataframeCopyString = dataframeCopy.toString();
        assertEquals(dataframeCopyString, dataframeString);
    }

    @Test void countTest() {
        assertEquals(3, dataframe.count());
    }

    @Test void whereTest() {
        var isExperiencedFilter = ColumnTransformation.of(new Identity(), "is_experienced");
        var newDataFrame = dataframe.where(isExperiencedFilter);
        assertEquals(2, newDataFrame.count());
        var notExperiencedFilter = ColumnTransformation.of(new Not(), "is_experienced");
        var newDataFrame2 = dataframe.where(notExperiencedFilter);
        assertEquals(1, newDataFrame2.count());
    }
}
