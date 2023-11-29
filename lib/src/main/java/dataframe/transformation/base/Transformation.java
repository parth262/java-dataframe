package dataframe.transformation.base;

import dataframe.types.DataType;

public interface Transformation {
    DataType getInputType();
    DataType getOutputType();
}
