package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Lower extends StringTransformation implements ToStringTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.toLowerCase();
    }
}
