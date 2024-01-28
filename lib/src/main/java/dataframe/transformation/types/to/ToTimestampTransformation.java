package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.TimestampType;

public interface ToTimestampTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new TimestampType();
    }
}
