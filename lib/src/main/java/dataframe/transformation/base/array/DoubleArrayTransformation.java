package dataframe.transformation.base.array;

import dataframe.types.DataType;
import dataframe.types.DoubleType;

public interface DoubleArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new DoubleType();
    }
}
