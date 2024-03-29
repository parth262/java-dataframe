package dataframe.transformation.types.array;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;

public interface ArrayTransformation extends Transformation {
    DataType getElementType();
}
