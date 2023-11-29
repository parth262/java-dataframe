package dataframe.transformation.any;

import dataframe.transformation.PrimitiveTransformation;
import dataframe.transformation.base.from.FromAnyTransformation;
import dataframe.transformation.base.to.ToBooleanTransformation;

import java.util.Objects;

public class NotNull implements PrimitiveTransformation, FromAnyTransformation, ToBooleanTransformation {

    @Override
    public Boolean apply(Object o) {
        return Objects.nonNull(o);
    }
}
