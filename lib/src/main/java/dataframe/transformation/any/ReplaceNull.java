package dataframe.transformation.any;

import dataframe.transformation.base.PrimitiveTransformation;
import dataframe.transformation.types.from.FromAnyTransformation;
import dataframe.transformation.types.to.ToAnyTransformation;

import java.util.Objects;

public class ReplaceNull implements PrimitiveTransformation, FromAnyTransformation, ToAnyTransformation {

    private final Object defaultValue;
    private final boolean emptyStringIsNull;

    public ReplaceNull(Object defaultValue) {
        this(defaultValue, false);
    }

    public ReplaceNull(Object defaultValue, boolean emptyStringIsNull) {
        this.defaultValue = defaultValue;
        this.emptyStringIsNull = emptyStringIsNull;
    }

    @Override
    public Object apply(Object value) {
        if (Objects.isNull(value)) {
            return defaultValue;
        }
        if(value instanceof String valueString && valueString.isEmpty() && emptyStringIsNull) {
            return defaultValue;
        }
        return value;
    }
}
