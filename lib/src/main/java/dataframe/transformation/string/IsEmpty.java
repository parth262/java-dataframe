package dataframe.transformation.string;

import dataframe.transformation.types.to.ToBooleanTransformation;

public class IsEmpty extends StringTransformation implements ToBooleanTransformation {

    @Override
    protected Object applyOnString(String s) {
        return s.isEmpty();
    }
}
