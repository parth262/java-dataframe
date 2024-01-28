package dataframe.transformation.types.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.AnyType;
import dataframe.types.DataType;

public interface FromAnyTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new AnyType();
    }
}
