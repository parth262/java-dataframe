package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Capitalize extends StringTransformation implements ToStringTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }
}
