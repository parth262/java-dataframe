package dataframe.transformation.base.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.BooleanType;
import dataframe.types.DataType;

public interface ToBooleanTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new BooleanType();
    }
}
