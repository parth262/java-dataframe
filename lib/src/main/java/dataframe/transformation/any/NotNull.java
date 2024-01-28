package dataframe.transformation.any;

import dataframe.transformation.base.PrimitiveTransformation;
import dataframe.transformation.types.from.FromAnyTransformation;
import dataframe.transformation.types.to.ToBooleanTransformation;

import java.util.Objects;

public class NotNull implements PrimitiveTransformation, FromAnyTransformation, ToBooleanTransformation {

    @Override
    public Boolean apply(Object o) {
        return Objects.nonNull(o);
    }
}
