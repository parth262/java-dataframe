package dataframe.transformation.types.array;

import dataframe.types.DataType;
import dataframe.types.IntegerType;

public interface IntegerArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new IntegerType();
    }
}
