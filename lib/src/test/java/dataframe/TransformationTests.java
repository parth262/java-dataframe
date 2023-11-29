package dataframe;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static dataframe.api.Functions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TransformationTests {

    private static DataFrame dataframe;

    @BeforeAll static void init() {
        var data = List.of(
            new Object[]{ 1, "parth", "pandya", true, "a/b/c" },
            new Object[]{ 2, "arpan", "shah", true, "d/e/f" },
            new Object[]{ 3, "ankit", "rathavi", false, "g/h/i" }
        );
        var columns = new String[] {"id", "first_name", "last_name", "is_experienced", "path"};
        dataframe = DataFrame.create(data, columns);
    }

    @Test void dataFrameTest() {
        dataframe = dataframe.withColumn("not_experienced", not("is_experienced"));
        dataframe = dataframe.withColumn("name", concat_ws(" ", "first_name", "last_name"));
        dataframe = dataframe.withColumn("first_name", capitalize("first_name"));
        dataframe = dataframe.withColumn("path_components", split("/", "path"));
        dataframe = dataframe.withColumn("dept", lit("IT"));
        dataframe = dataframe.withColumn("created_at", currentTimestamp());
        dataframe = dataframe.drop("is_experienced", "last_name");

        System.out.println(dataframe);

        var expectedColumns = List.of(
            "id", "first_name", "path", "not_experienced",
            "name", "path_components", "dept", "created_at"
        );
        assertTrue(dataframe.getColumns().containsAll(expectedColumns));
    }
}
