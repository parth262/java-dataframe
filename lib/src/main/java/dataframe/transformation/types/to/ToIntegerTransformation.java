package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.IntegerType;

public interface ToIntegerTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new IntegerType();
    }
}
