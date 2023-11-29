package dataframe.transformation.base.array.to;

import dataframe.transformation.base.array.ArrayTransformation;
import dataframe.types.ArrayType;
import dataframe.types.DataType;

public interface ToArrayTransformation extends ArrayTransformation {

    @Override
    default DataType getOutputType() {
        return new ArrayType(getElementType());
    }
}
