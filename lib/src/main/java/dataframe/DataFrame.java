package dataframe;

import dataframe.api.Functions;
import dataframe.grouped.GroupKeys;
import dataframe.grouped.GroupedDataFrame;
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

    public List<Object[]> getRows() {
        return Arrays.stream(this.rows).map(Row::getValues).toList();
    }

    public static DataFrame empty() {
        return new DataFrame(new Row[]{}, Schema.empty());
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

    public int count() {
        return this.rows.length;
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

    public DataFrame select(String... columns) {
        var newSchema = this.schema.select(columns);
        var newRows = Arrays.stream(this.rows)
            .map(row -> row.getBySchema(newSchema))
            .toArray(Row[]::new);
        return new DataFrame(newRows, newSchema);
    }

    public DataFrame drop(String... columns) {
        var newSchema = this.schema.drop(columns);
        var newRows = Arrays.stream(this.rows)
            .map(row -> row.getBySchema(newSchema))
            .toArray(Row[]::new);
        return new DataFrame(newRows, newSchema);
    }

    public DataFrame where(ColumnTransformation transformation) {
        var schema = this.schema.copy();
        var filteredRows = Arrays.stream(this.rows).parallel()
            .filter(row -> {
                var value = ColumnTransformationEvaluator.evaluate(transformation, row);
                assert value instanceof Boolean;
                return (boolean) value;
            }).toArray(Row[]::new);
        return new DataFrame(filteredRows, schema);
    }

    public DataFrame copy() {
        var rowsCopy = Arrays.copyOf(this.rows, this.rows.length);
        return new DataFrame(rowsCopy, schema.copy());
    }

    public GroupedDataFrame groupBy(String... columns) {
        var dataframeGroups = Arrays.stream(this.rows).collect(
                Collectors.groupingBy(
                        row -> new GroupKeys(row.select(columns)),
                        Collectors.collectingAndThen(
                                Collectors.toList(),
                                rows -> new DataFrame(
                                        rows.toArray(Row[]::new),
                                        this.schema.copy()
                                )
                        )
                )
        );
        return new GroupedDataFrame(dataframeGroups);
    }

    public DataFrame join(DataFrame other, String leftColumn, String rightColumn) {
        var leftField = this.schema.get(leftColumn);
        var rightField = other.schema.get(rightColumn);
        assert leftField.dataType().equals(rightField.dataType());
        var newSchema = this.schema.join(other.schema);
        var otherDataFrameGrouped = other.groupBy(rightColumn);
        var newRows = Arrays.stream(this.rows).flatMap(row -> {
            var value = row.get(leftColumn);
            var otherDataFrame = otherDataFrameGrouped.getDataFrame(value);
            return row.join(otherDataFrame.rows, newSchema);
        }).toArray(Row[]::new);
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DataFrame other = (DataFrame) o;
        return this.toString().equals(other.toString());
    }

    @Override
    public int hashCode() {
        return this.toString().hashCode();
    }
}
