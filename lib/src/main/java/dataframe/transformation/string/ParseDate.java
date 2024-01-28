package dataframe.transformation.string;

import dataframe.transformation.types.to.ToDateTransformation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ParseDate extends StringTransformation implements ToDateTransformation {

    private final DateTimeFormatter formatter;

    public ParseDate(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    protected Object applyOnString(String s) {
        return LocalDate.parse(s, this.formatter);
    }
}
