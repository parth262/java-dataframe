package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.StringType;

public interface FromStringTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new StringType();
    }
}
