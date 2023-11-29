package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Upper extends StringTransformation implements ToStringTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.toUpperCase();
    }
}
