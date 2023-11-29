package dataframe.transformation.base.array;

import dataframe.types.DataType;
import dataframe.types.TimestampType;

public interface TimestampArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new TimestampType();
    }
}
