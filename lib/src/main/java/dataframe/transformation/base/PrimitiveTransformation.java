package dataframe.transformation.base;

import java.util.function.Function;

public interface PrimitiveTransformation extends Transformation, Function<Object, Object> {
}
