package dataframe.transformation.any;

import dataframe.transformation.base.NonNullPrimitiveTransformation;
import dataframe.transformation.types.from.FromAnyTransformation;
import dataframe.transformation.types.to.ToAnyTransformation;

import java.util.Map;

public class DirectMap extends NonNullPrimitiveTransformation implements FromAnyTransformation, ToAnyTransformation {
    public final Map<Object, Object> cases;
    public DirectMap(Map<Object, Object> cases) {
        this.cases = cases;
    }

    @Override
    protected Object applyPrimitive(Object o) {
        return cases.getOrDefault(o, o);
    }
}
