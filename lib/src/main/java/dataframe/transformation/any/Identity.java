package dataframe.transformation.any;

import dataframe.transformation.base.PrimitiveTransformation;
import dataframe.transformation.types.from.FromAnyTransformation;
import dataframe.transformation.types.to.ToAnyTransformation;

public class Identity implements PrimitiveTransformation, FromAnyTransformation, ToAnyTransformation {

    @Override
    public Object apply(Object o) {
        return o;
    }
}
