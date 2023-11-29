package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.BooleanType;
import dataframe.types.DataType;

public interface FromBooleanTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new BooleanType();
    }
}
