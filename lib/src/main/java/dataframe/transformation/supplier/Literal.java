package dataframe.transformation.supplier;

import dataframe.transformation.SupplierTransformation;
import dataframe.types.DataType;
import dataframe.types.DataTypeUtil;

public class Literal implements SupplierTransformation {

    private final Object value;
    private final DataType outputType;
    public Literal(Object value) {
        this.value = value;
        this.outputType = DataTypeUtil.infer(value);
    }

    @Override
    public Object get() {
        return this.value;
    }

    @Override
    public DataType getOutputType() {
        return this.outputType;
    }
}
