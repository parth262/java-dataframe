package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class RegexReplace extends StringTransformation implements ToStringTransformation {
    private final String regex;
    private final String replaceWith;

    public RegexReplace(String regex, String replaceWith) {
        this.regex = regex;
        this.replaceWith = replaceWith;
    }


    @Override
    protected Object applyOnString(String s) {
        return s.replaceAll(regex, replaceWith);
    }
}
