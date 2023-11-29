package dataframe.transformation;

public abstract class PrimitiveArrayTransformation extends NonNullPrimitiveTransformation {

    @Override
    protected Object applyPrimitive(Object o) {
        if(!(o instanceof Object[])) {
            throw new RuntimeException("Array input required");
        }
        return this.applyArray((Object[]) o);
    }

    protected abstract Object applyArray(Object... o);
}
