package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Concat extends StringArrayTransformation implements ToStringTransformation {
    private final String separator;
    private static final String DEFAULT_SEPARATOR = "";

    public Concat() {
        this(DEFAULT_SEPARATOR);
    }

    public Concat(String separator) {
        this.separator = separator;
    }

    @Override
    protected Object applyOnString(String... s) {
        return String.join(separator, s);
    }
}
