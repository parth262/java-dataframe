package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.DoubleType;

public interface FromDoubleTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new DoubleType();
    }
}
