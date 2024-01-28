package dataframe.types;

import java.util.ArrayList;
import java.util.List;

public class Schema {
    private final ArrayList<SchemaField> fields;
    public Schema(ArrayList<SchemaField> fields) {
        this.fields = fields;
    }

    public static Schema empty() {
        return new Schema(new ArrayList<>());
    }

    public List<String> fieldNames() {
        return this.fields.stream().map(SchemaField::name).toList();
    }

    public int indexOf(String name) {
        for (int i = 0; i < this.fields.size(); i++) {
            if(fields.get(i).name().equals(name)) {
                return i;
            }
        }
        return -1;
    }

    public SchemaField get(String name) {
        return this.fields.stream()
                .filter(schemaField -> schemaField.name().equals(name))
                .findFirst().orElseThrow();
    }

    @SuppressWarnings("unchecked")
    public Schema addColumn(String newColumn, DataType dataType) {
        if(this.indexOf(newColumn) != -1) {
            return this;
        }
        var newFields = (ArrayList<SchemaField>) this.fields.clone();
        var newField = new SchemaField(newColumn, dataType);
        newFields.add(newField);
        return new Schema(newFields);
    }

    public Schema select(String... columns) {
        var newFields = new ArrayList<SchemaField>();
        for(var column: columns) {
            var columnIndex = this.indexOf(column);
            if (columnIndex == -1) {
                throw new RuntimeException("Unresolved column: " + column);
            }
            newFields.add(this.fields.get(columnIndex));
        }
        return new Schema(newFields);
    }

    @SuppressWarnings("unchecked")
    public Schema drop(String... columns) {
        var newFields = (ArrayList<SchemaField>) this.fields.clone();
        for(var column: columns) {
            newFields.removeIf(field -> field.name().equals(column));
        }
        return new Schema(newFields);
    }

    public Schema join(Schema other) {
        var newFields = new ArrayList<>(this.fields);
        newFields.addAll(other.fields);
        return new Schema(newFields);
    }

    public List<SchemaField> getFields() {
        return List.copyOf(this.fields);
    }

    public Schema copy() {
        var fieldsCopy = new ArrayList<>(this.fields);
        return new Schema(fieldsCopy);
    }
}
