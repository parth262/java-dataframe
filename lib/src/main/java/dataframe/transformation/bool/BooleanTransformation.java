package dataframe.transformation.bool;

import dataframe.transformation.NonNullPrimitiveTransformation;
import dataframe.transformation.base.from.FromBooleanTransformation;

public abstract class BooleanTransformation
    extends NonNullPrimitiveTransformation
    implements FromBooleanTransformation
{
    @Override
    public Object applyPrimitive(Object o) {
        return this.applyForBoolean((boolean) o);
    }

    protected abstract Object applyForBoolean(boolean b);
}
