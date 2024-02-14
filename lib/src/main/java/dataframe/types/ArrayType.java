package dataframe.types;

public class ArrayType extends DataType {
    private final DataType itemType;

    public ArrayType(DataType itemType) {
        this.itemType = itemType;
    }

    public DataType getItemType() {
        return itemType;
    }

    @Override
    public String getSimpleName() {
        return String.format("array<%s>", this.itemType.getSimpleName());
    }
}
