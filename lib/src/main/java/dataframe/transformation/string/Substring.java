package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Substring extends StringTransformation implements ToStringTransformation {
    private final int start;
    private int end = -1;
    public Substring(int start) {
        this.start = start;
    }

    public Substring(int start, int end) {
        this(start);
        this.end = end;
    }

    @Override
    protected Object applyOnString(String s) {
        if(end > 0) {
            return s.substring(start, end);
        }
        return s.substring(start);
    }
}
