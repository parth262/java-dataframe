package dataframe.transformation.types.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.IntegerType;

public interface FromIntegerTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new IntegerType();
    }
}
