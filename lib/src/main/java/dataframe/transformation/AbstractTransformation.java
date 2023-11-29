package dataframe.transformation;

import dataframe.transformation.base.Transformation;
import dataframe.types.DataType;

public abstract class AbstractTransformation implements Transformation {
    private final DataType inputType;
    private final DataType outputType;

    protected AbstractTransformation(DataType inputType, DataType outputType) {
        this.inputType = inputType;
        this.outputType = outputType;
    }

    @Override
    public DataType getInputType() {
        return this.inputType;
    }

    @Override
    public DataType getOutputType() {
        return this.outputType;
    }
}
