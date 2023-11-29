package dataframe.transformation.base.array;

import dataframe.types.DataType;
import dataframe.types.DateType;

public interface DateArrayTransformation extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new DateType();
    }
}
