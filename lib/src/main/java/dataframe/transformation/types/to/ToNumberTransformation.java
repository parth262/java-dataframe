package dataframe.transformation.types.to;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;
import dataframe.types.IntegerType;
import dataframe.types.NumericType;

public interface ToNumberTransformation extends Transformation {

    @Override
    default DataType getOutputType() {
        return new NumericType() {
            @Override
            public String getSimpleName() {
                return "number";
            }
        };
    }
}
