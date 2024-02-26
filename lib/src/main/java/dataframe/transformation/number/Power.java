package dataframe.transformation.number;

import dataframe.transformation.types.to.ToNumberTransformation;

public class Power extends NumberArrayTransformation implements ToNumberTransformation {

    @Override
    protected Object applyOnNumber(Number... s) {
        Number firstValue = s[0];
        Number secondValue = s[1];
        return String.valueOf(Math.pow(firstValue.doubleValue(), secondValue.doubleValue()));
    }
}
