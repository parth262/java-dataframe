package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.LongType;

public interface ToLongTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new LongType();
    }
}
