package dataframe;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import dataframe.types.ArrayType;
import dataframe.types.Schema;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class Row {

    private static final Logger logger = LoggerFactory.getLogger(Row.class);
    private final ArrayList<Object> data;
    private final Schema schema;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    Row(ArrayList<Object> data, Schema schema) {
        this.data = data;
        this.schema = schema;
    }

    @SuppressWarnings("unchecked")
    Row add(int index, Object newValue, Schema newSchema) {
        var newData = (ArrayList<Object>) this.data.clone();
        if(index != -1) {
            newData.set(index, newValue);
        } else {
            newData.add(newValue);
        }
        return new Row(newData, newSchema);
    }

    Row concat(Row other, Schema newSchema) {
        var newData = new ArrayList<>();
        newData.addAll(this.data);
        newData.addAll(other.data);
        return new Row(newData, newSchema);
    }

    public Object get(int i) {
        return this.data.get(i);
    }

    public Object get(String column) {
        return this.data.get(this.fieldIndex(column));
    }
    int fieldIndex(String name) {
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
    public String getString(String column) {
        return String.valueOf(this.data.get(this.fieldIndex(column)));
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
                if (value instanceof Object[] values) {
                    try {
                        return objectMapper.writeValueAsString(values);
                    } catch (JsonProcessingException e) {
                        logger.error("Invalid array value: {}", value, e);
                        var stringValues = Arrays.stream(values).map(String::valueOf).toArray(String[]::new);
                        return String.format("[%s]", String.join(",", stringValues));
                    }
                }
                return String.valueOf(value);
            })
            .toList());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Row row = (Row) o;

        if(!schema.equals(row.schema)) return false;
        return schema.getFields().stream().allMatch(field -> {
            var dataType = field.dataType();
            var fieldName = field.name();
            var thisColumnValue = this.get(fieldName);
            var otherColumnValue = row.get(fieldName);
            if(dataType instanceof ArrayType) {
                var thisArrayValue = (Object[]) thisColumnValue;
                var otherArrayValue = (Object[]) otherColumnValue;
                return Arrays.equals(thisArrayValue, otherArrayValue);
            }
            return Objects.equals(thisColumnValue, otherColumnValue);
        });
    }

    public int compareOnColumn(Row row, String column){
        var columnType = this.schema.get(column).dataType().getSimpleName();
        System.out.println("column" +" "+columnType);
        return switch(columnType){
            case "string": yield this.getString(column).compareTo(row.getString(column));
            case "integer": yield Integer.compare(Integer.valueOf(this.getString(column)), Integer.valueOf(row.getString(column)));
            case "double": yield Double.compare(Double.valueOf(this.getString(column)), Double.valueOf(row.getString(column)));
            case "boolean": yield Boolean.compare(Boolean.valueOf(this.getString(column)), Boolean.valueOf(row.getString(column)));
            case "float": yield Float.compare(Float.valueOf(this.getString(column)), Float.valueOf(row.getString(column)));
            default: yield -1;
        };
    }

    @Override
    public int hashCode() {
        int result = data.hashCode();
        result = 31 * result + schema.hashCode();
        return result;
    }
}
