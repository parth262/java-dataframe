package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.AnyType;
import dataframe.types.DataType;

public interface ToAnyTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new AnyType();
    }
}
