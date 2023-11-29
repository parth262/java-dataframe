package dataframe.transformation.base.array;

import dataframe.types.DataType;
import dataframe.types.LongType;

public interface LongArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new LongType();
    }
}
