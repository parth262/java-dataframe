package dataframe.transformation.base.array;

import dataframe.types.DataType;
import dataframe.types.StringType;

public interface StringArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new StringType();
    }
}
