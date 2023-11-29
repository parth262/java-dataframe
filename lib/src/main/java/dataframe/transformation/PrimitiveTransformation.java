package dataframe.transformation;

import dataframe.transformation.base.Transformation;

import java.util.function.Function;

public interface PrimitiveTransformation extends Transformation, Function<Object, Object> {
}
