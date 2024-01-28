package dataframe.transformation.bool;

import dataframe.transformation.types.to.ToBooleanTransformation;

public class Not extends BooleanTransformation implements ToBooleanTransformation {

    @Override
    protected Object applyForBoolean(boolean b) {
        return !b;
    }
}
