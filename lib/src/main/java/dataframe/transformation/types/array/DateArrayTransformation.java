package dataframe.transformation.types.array;

import dataframe.types.DataType;
import dataframe.types.DateType;

public interface DateArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new DateType();
    }
}
