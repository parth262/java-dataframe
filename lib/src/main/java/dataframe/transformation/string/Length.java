package dataframe.transformation.string;

import dataframe.transformation.types.to.ToIntegerTransformation;

public class Length extends StringTransformation implements ToIntegerTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.length();
    }
}
