package dataframe.transformation.string;

import dataframe.transformation.base.NonNullPrimitiveTransformation;
import dataframe.transformation.types.from.FromStringTransformation;

public abstract class StringTransformation
    extends NonNullPrimitiveTransformation
    implements FromStringTransformation
{

    @Override
    public Object applyPrimitive(Object o) {
        return this.applyOnString(o.toString());
    }

    protected abstract Object applyOnString(String s);
}
