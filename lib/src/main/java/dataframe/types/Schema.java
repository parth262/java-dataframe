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

    @SuppressWarnings("unchecked")
    public Schema drop(String... columns) {
        var newFields = (ArrayList<SchemaField>) this.fields.clone();
        for(var column: columns) {
            newFields.removeIf(field -> field.name().equals(column));
        }
        return new Schema(newFields);
    }

    public List<SchemaField> getFields() {
        return List.copyOf(this.fields);
    }
}
