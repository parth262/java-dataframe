package dataframe.transformation.base.array.from;

import dataframe.transformation.base.array.ArrayTransformation;
import dataframe.types.ArrayType;
import dataframe.types.DataType;

public interface FromArrayTransformation extends ArrayTransformation {

    @Override
    default DataType getInputType() {
        return new ArrayType(getElementType());
    }
}
