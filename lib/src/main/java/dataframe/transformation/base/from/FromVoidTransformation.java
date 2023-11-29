package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.VoidType;

public interface FromVoidTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new VoidType();
    }
}
