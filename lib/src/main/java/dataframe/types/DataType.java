package dataframe.types;

public abstract class DataType {
    public abstract String getSimpleName();

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof DataType other)) {
            return false;
        }
        return this.getSimpleName().equals(other.getSimpleName());
    }
}
