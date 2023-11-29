package dataframe.transformation.string;

import dataframe.transformation.base.to.ToBooleanTransformation;

public class NotEmpty extends StringTransformation implements ToBooleanTransformation {

    @Override
    protected Object applyOnString(String s) {
        return !s.isEmpty();
    }
}
