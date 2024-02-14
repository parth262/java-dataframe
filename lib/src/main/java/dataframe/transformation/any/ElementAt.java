package dataframe.transformation.any;

import dataframe.transformation.base.GenericArrayTransformation;
import dataframe.types.ArrayType;
import dataframe.types.DataType;

public class ElementAt extends GenericArrayTransformation {
    private final int at;
    private final ArrayType dataType;

    public ElementAt(ArrayType dataType, int at) {
        this.dataType = dataType;
        this.at = at;
    }

    @Override
    public DataType getInputType() {
        return this.dataType;
    }

    @Override
    public DataType getOutputType() {
        return this.dataType.getItemType();
    }

    @Override
    protected Object applyArray(Object... objects) {
        if (this.at >= objects.length) {
            return null;
        }
        return objects[this.at];
    }
}
