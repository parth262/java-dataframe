package dataframe.transformation.number;

import dataframe.transformation.string.StringArrayTransformation;
import dataframe.transformation.types.to.ToNumberTransformation;
import dataframe.transformation.types.to.ToStringTransformation;

public class Sum extends NumberArrayTransformation implements ToNumberTransformation {

    @Override
    protected Object applyOnNumber(Number... s) {
        Number firstValue = s[0];
        Number secondValue = s[1];
        if(firstValue instanceof Double || secondValue instanceof Double) {
            return String.valueOf(firstValue.doubleValue() + secondValue.doubleValue());
        } else if(firstValue instanceof Float || secondValue instanceof Float){
            return String.valueOf(firstValue.floatValue() + secondValue.floatValue());
        } else if(firstValue instanceof Long || secondValue instanceof Long){
            return String.valueOf(firstValue.longValue() + secondValue.longValue());
        } else if(firstValue instanceof Integer || secondValue instanceof Integer){
            return String.valueOf(firstValue.floatValue() + secondValue.floatValue());
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + firstValue.getClass());
        }

    }
}
