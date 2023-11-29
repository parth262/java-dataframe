package dataframe.transformation.any;

import dataframe.transformation.PrimitiveTransformation;
import dataframe.transformation.base.from.FromAnyTransformation;
import dataframe.transformation.base.to.ToAnyTransformation;

public class Identity implements PrimitiveTransformation, FromAnyTransformation, ToAnyTransformation {

    @Override
    public Object apply(Object o) {
        return o;
    }
}
