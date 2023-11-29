package dataframe;

import dataframe.transformation.ColumnTransformation;
import dataframe.transformation.ColumnTransformationEvaluator;
import dataframe.types.DataTypeUtil;
import dataframe.types.Schema;
import dataframe.types.SchemaField;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataFrame {
    private final Row[] rows;
    private final Schema schema;

    private DataFrame(Row[] rows, Schema schema) {
        this.rows = rows;
        this.schema = schema;
    }

    public List<String> getColumns() {
        return this.schema.fieldNames();
    }

    public static DataFrame create(List<Object[]> data, String[] columns) {
        var fields = new ArrayList<SchemaField>();
        var firstRow = data.get(0);
        for(int i=0;i<columns.length;i++) {
            var columnValue = firstRow[i];
            var dataType = DataTypeUtil.infer(columnValue);
            fields.add(new SchemaField(columns[i], dataType));
        }
        var schema = new Schema(fields);
        var rows = data.stream().map(dataRow -> {
            var dataList = new ArrayList<>(Arrays.asList(dataRow));
            return new Row(dataList, schema);
        }).toArray(Row[]::new);
        return new DataFrame(rows, schema);
    }

    public DataFrame withColumn(String newColumn, ColumnTransformation columnTransformation) {
        var existingIndex = this.schema.indexOf(newColumn);
        var newSchema = this.schema.addColumn(newColumn, columnTransformation.transformation().getOutputType());
        var newRows = Arrays.stream(this.rows).parallel().map(row -> {
            var newValue = ColumnTransformationEvaluator.evaluate(columnTransformation, row);
            return row.add(existingIndex, newValue, newSchema);
        }).toArray(Row[]::new);
        return new DataFrame(newRows, newSchema);
    }

    public DataFrame drop(String... columns) {
        var newSchema = this.schema.drop(columns);
        var newRows = Arrays.stream(this.rows)
            .map(row -> row.drop(columns, newSchema))
            .toArray(Row[]::new);
        return new DataFrame(newRows, newSchema);
    }

    @Override
    public String toString() {
        var stringArray = new ArrayList<String>();
        var header = this.schema.getFields().stream()
            .map(field -> String.format("%s (%s)", field.name(), field.dataType().getSimpleName()))
                .collect(Collectors.joining("\t"));
        stringArray.add(header);
        stringArray.addAll(Arrays.stream(this.rows).map(Row::toString).toList());
        return String.join("\n", stringArray);
    }
}
