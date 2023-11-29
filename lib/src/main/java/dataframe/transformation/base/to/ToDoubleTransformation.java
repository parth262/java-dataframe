package dataframe.transformation.base.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.DoubleType;

public interface ToDoubleTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new DoubleType();
    }
}
