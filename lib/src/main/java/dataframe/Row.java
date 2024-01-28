package dataframe;

import dataframe.types.Schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Row {

    private final ArrayList<Object> data;
    private final Schema schema;

    public Row(ArrayList<Object> data, Schema schema) {
        this.data = data;
        this.schema = schema;
    }

    @SuppressWarnings("unchecked")
    public Row add(int index, Object newValue, Schema newSchema) {
        var newData = (ArrayList<Object>) this.data.clone();
        if (index != -1) {
            newData.set(index, newValue);
        } else {
            newData.add(newValue);
        }
        return new Row(newData, newSchema);
    }

    public Object get(int i) {
        return this.data.get(i);
    }

    public Object get(String column) {
        return this.data.get(this.fieldIndex(column));
    }

    public int fieldIndex(String name) {
        var fieldIndex = this.schema.indexOf(name);
        if (fieldIndex == -1) {
            throw new RuntimeException("Unresolved field: " + name);
        }
        return fieldIndex;
    }

    public boolean getBoolean(int i) {
        return this.<Boolean>getAs(i);
    }

    public String getString(int i) {
        return this.getAs(i);
    }

    public Integer getInteger(int i) {
        return this.<Integer>getAs(i);
    }

    public Long getLong(int i) {
        return this.<Long>getAs(i);
    }

    public Double getDouble(int i) {
        return this.<Double>getAs(i);
    }

    @SuppressWarnings("unchecked")
    public <T> T getAs(int i) {
        return (T) get(i);
    }

    public Object[] select(String... columns) {
        var result = new Object[columns.length];
        for(int i=0;i<columns.length;i++) {
            var column = columns[i];
            var value = this.get(column);
            result[i] = value;
        }
        return result;
    }

    public Row getBySchema(Schema newSchema) {
        var newData = newSchema.fieldNames().stream()
                .map(this::get)
                .collect(Collectors.toCollection(ArrayList::new));
        return new Row(newData, newSchema);
    }

    public Object[] getValues() {
        return this.data.toArray();
    }

    Stream<Row> join(Row[] rows, Schema newSchema) {
        if(rows.length == 0) {
            var newData = newSchema.getFields()
                    .stream().map(schemaField -> {
                        var columnIndex = this.schema.indexOf(schemaField.name());
                        if(columnIndex == -1) {
                            return null;
                        }
                        return this.get(columnIndex);
                    }).collect(Collectors.toCollection(ArrayList<Object>::new));
            return Stream.of(new Row(newData, newSchema));
        }
        return Arrays.stream(rows)
                .map(row -> {
                    var newData = new ArrayList<>(this.data);
                    newData.addAll(row.data);
                    return new Row(newData, newSchema);
                });
    }

    @Override
    public String toString() {
        return String.join("\t", this.data.stream()
                .map(value -> {
                    if (value instanceof Object[]) {
                        var values = Arrays.stream((Object[]) value).map(String::valueOf).toList();
                        return String.format("[%s]", String.join(", ", values));
                    }
                    return String.valueOf(value);
                })
                .toList());
    }
}
