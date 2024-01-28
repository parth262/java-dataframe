package dataframe.transformation.types.array.from;

import dataframe.transformation.types.array.ArrayTransformation;
import dataframe.types.ArrayType;
import dataframe.types.DataType;

public interface FromArrayTransformation extends ArrayTransformation {

    @Override
    default DataType getInputType() {
        return new ArrayType(getElementType());
    }
}
