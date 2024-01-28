package dataframe.transformation.types.from;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.TimestampType;

public interface FromTimestampTransformation extends Transformation {

    @Override
    default DataType getInputType() {
        return new TimestampType();
    }
}
