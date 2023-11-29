package dataframe.transformation.base.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.DateType;

public interface FromDateTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new DateType();
    }
}
