package dataframe.transformation.types.array;

import dataframe.types.DataType;
import dataframe.types.NumericType;
import dataframe.types.StringType;

public interface NumberArrayTransformation  extends ArrayTransformation {
    @Override
    default DataType getElementType() {
        return new NumericType() {
            @Override
            public String getSimpleName() {
                return "number";
            }
        };
    }
}
