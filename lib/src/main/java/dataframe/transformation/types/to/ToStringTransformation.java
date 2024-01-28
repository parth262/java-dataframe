package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.StringType;

public interface ToStringTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new StringType();
    }
}
