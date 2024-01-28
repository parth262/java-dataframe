package dataframe.transformation.types.array;

import dataframe.types.DataType;
import dataframe.types.DoubleType;

public interface DoubleArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new DoubleType();
    }
}
