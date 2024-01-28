package dataframe.transformation.base;

public abstract class PrimitiveArrayTransformation implements PrimitiveTransformation {

    public Object apply(Object o) {
        if(!(o instanceof Object[])) {
            throw new RuntimeException("Array input required");
        }
        return this.applyArray((Object[]) o);
    }

    protected abstract Object applyArray(Object... objects);
}
