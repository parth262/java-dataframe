package dataframe.transformation.number;

import dataframe.transformation.types.to.ToNumberTransformation;

public class Subtract extends NumberArrayTransformation implements ToNumberTransformation {

    @Override
    protected Object applyOnNumber(Number... s) {
        Number firstValue = s[0];
        Number secondValue = s[1];
        if(firstValue instanceof Double || secondValue instanceof Double) {
            return firstValue.doubleValue() - secondValue.doubleValue();
        } else if(firstValue instanceof Float || secondValue instanceof Float){
            return firstValue.floatValue() - secondValue.floatValue();
        } else if(firstValue instanceof Long || secondValue instanceof Long){
            return firstValue.longValue() - secondValue.longValue();
        } else if(firstValue instanceof Integer || secondValue instanceof Integer){
            return firstValue.floatValue() - secondValue.floatValue();
        } else {
            throw new UnsupportedOperationException("Unsupported data type: " + firstValue.getClass());
        }

    }
}
