package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.LongType;

public interface FromLongTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new LongType();
    }
}
