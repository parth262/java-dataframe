package dataframe.transformation.number;

import dataframe.transformation.types.to.ToNumberTransformation;

public class Divide extends NumberArrayTransformation implements ToNumberTransformation {

    @Override
    protected Object applyOnNumber(Number... s) {
        Number firstValue = s[0];
        Number secondValue = s[1];
        if(secondValue.doubleValue() ==0.0) {
            throw new UnsupportedOperationException("Unsupported data, divide by zero: " + secondValue);
        }
        return String.valueOf(firstValue.doubleValue() / secondValue.doubleValue());
    }
}
