package dataframe.transformation.string;

import dataframe.transformation.types.array.to.ToStringArrayTransformation;

public class Split extends StringTransformation implements ToStringArrayTransformation {

    private final String regex;
    private int limit = 0;

    public Split(String regex) {
        this.regex = regex;
    }

    public Split(String regex, int limit) {
        this(regex);
        this.limit = limit;
    }

    @Override
    protected Object applyOnString(String s) {
        return s.split(regex, limit);
    }
}
