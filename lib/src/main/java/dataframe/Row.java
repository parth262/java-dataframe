package dataframe;

import dataframe.types.Schema;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;


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
        if(index != -1) {
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
        if(fieldIndex == -1) {
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

    @SuppressWarnings("unchecked")
    public Row drop(String[] columns, Schema newSchema) {
        var newData = (ArrayList<Object>) this.data.clone();
        var indicesToRemove = Arrays.stream(columns)
            .map(this::fieldIndex)
            .sorted(Comparator.reverseOrder())
            .toArray(Integer[]::new);
        for(int index: indicesToRemove) {
            newData.remove(index);
        }
        return new Row(newData, newSchema);
    }

    @Override
    public String toString() {
        return String.join("\t", this.data.stream()
            .map(value -> {
                if (value instanceof Object[]) {
                    var values = Arrays.stream((Object[]) value).map(Object::toString).toList();
                    return String.format("[%s]", String.join(", ", values));
                }
                return value.toString();
            })
            .toList());
    }
}
