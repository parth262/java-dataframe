package dataframe.transformation.base.array;

import dataframe.types.AnyType;
import dataframe.types.DataType;

public interface AnyArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new AnyType();
    }
}
