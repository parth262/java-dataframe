package dataframe.transformation;

import java.util.Objects;

public abstract class NonNullPrimitiveTransformation implements PrimitiveTransformation {

    public Object apply(Object o) {
        if(Objects.isNull(o)) {
            return null;
        }
        return this.applyPrimitive(o);
    }

    protected abstract Object applyPrimitive(Object o);
}
