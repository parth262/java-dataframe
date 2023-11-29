package dataframe.transformation.base.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.DateType;

public interface ToDateTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new DateType();
    }
}
