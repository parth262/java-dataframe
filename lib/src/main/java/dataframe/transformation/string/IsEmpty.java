package dataframe.transformation.string;

import dataframe.transformation.base.to.ToBooleanTransformation;

public class IsEmpty extends StringTransformation implements ToBooleanTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.isEmpty();
    }
}
