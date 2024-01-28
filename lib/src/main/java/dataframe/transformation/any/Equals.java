package dataframe.transformation.any;

import dataframe.transformation.base.PrimitiveTransformation;
import dataframe.transformation.types.from.FromAnyTransformation;
import dataframe.transformation.types.to.ToBooleanTransformation;

public class Equals implements PrimitiveTransformation, FromAnyTransformation, ToBooleanTransformation {

    private final Object value;

    public Equals(Object value) {
        this.value = value;
    }

    @Override
    public Object apply(Object o) {
        return o.equals(this.value);
    }
}
