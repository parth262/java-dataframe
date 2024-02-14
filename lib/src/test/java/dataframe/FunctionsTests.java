package dataframe;

import dataframe.api.Functions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionsTests {

    @Test
    void splitTest() {
        var newData = List.of(
                new Object[]{1, "John Doe", "john.doe@email.com"},
                new Object[]{2, "Harley Davidson", "harley.davidson@bikers.com"}
        );
        var columns = new String[]{"id", "full_name", "email"};
        var expectedData = List.of(
                new Object[]{new String[] {"John", "Doe"}},
                new Object[]{new String[] {"Harley", "Davidson"}}
        );
        var expectedColumns = new String[]{"full_name_split"};
        var df = DataFrame.create(newData, columns);
        df = df.withColumn("full_name_split", Functions.split("full_name", " "));
        var actualDF = df.select("full_name_split");
        var expectedDF = DataFrame.create(expectedData, expectedColumns);
        assertEquals(expectedDF, actualDF);
    }

    @Test void elementAtTest() {
        var data = List.of(
                new Object[]{new String[] {"John", "Doe"}},
                new Object[]{new String[] {"Harley", "Davidson"}}
        );
        var columns = new String[]{"full_name_split"};
        var expectedData = List.of(
                new Object[]{"John"},
                new Object[]{"Harley"}
        );
        var expectedColumns = new String[]{"first_name"};
        var df = DataFrame.create(data, columns);
        var field = df.getField("full_name_split");
        df = df.withColumn("first_name", Functions.elementAt(field, 0));
        var actualDF = df.select("first_name");
        var expectedDf = DataFrame.create(expectedData, expectedColumns);
        assertEquals(expectedDf, actualDF);
    }

    @Test void paddingTest() {
        var data = List.of(
                new Object[] {"12345"},
                new Object[] {"6746234"},
                new Object[] {"674623488"}
        );
        var columns = new String[] {"zip_code"};
        var expectedData = List.of(
                new Object[] {"000012345"},
                new Object[] {"006746234"},
                new Object[] {"674623488"}
        );
        var expectedColumns = new String[] {"pad_zip_code"};
        var df = DataFrame.create(data, columns);
        var paddingFunction = Functions.pad("zip_code", '0', "left", 9);
        df = df.withColumn("pad_zip_code", paddingFunction);
        var actualDF = df.select("pad_zip_code");
        var expectedDF = DataFrame.create(expectedData, expectedColumns);
        assertEquals(expectedDF, actualDF);
    }

}
