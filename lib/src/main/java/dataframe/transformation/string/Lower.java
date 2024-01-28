package dataframe.transformation.string;

import dataframe.transformation.types.to.ToStringTransformation;

public class Lower extends StringTransformation implements ToStringTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.toLowerCase();
    }
}
