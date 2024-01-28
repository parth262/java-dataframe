package dataframe.transformation.types.array;

import dataframe.types.BooleanType;
import dataframe.types.DataType;

public interface BooleanArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new BooleanType();
    }
}
