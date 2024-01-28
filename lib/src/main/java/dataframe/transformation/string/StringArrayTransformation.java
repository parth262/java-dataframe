package dataframe.transformation.string;

import dataframe.transformation.base.PrimitiveArrayTransformation;
import dataframe.transformation.types.array.from.FromStringArrayTransformation;

public abstract class StringArrayTransformation
    extends PrimitiveArrayTransformation
    implements FromStringArrayTransformation
{

    @Override
    protected Object applyArray(Object... o) {
        var stringArray = new String[o.length];
        for(int i=0;i<o.length;i++) {
            stringArray[i] = String.valueOf(o[i]);
        }
        return this.applyOnString(stringArray);
    }

    protected abstract Object applyOnString(String... s);
}
