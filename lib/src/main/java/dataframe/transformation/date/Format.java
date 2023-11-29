package dataframe.transformation.date;

import dataframe.transformation.base.to.ToStringTransformation;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Format extends DateTransformation implements ToStringTransformation {

    private final DateTimeFormatter formatter;

    public Format(String pattern) {
        this.formatter = DateTimeFormatter.ofPattern(pattern);
    }

    @Override
    protected Object applyOnDate(LocalDate date) {
        return date.format(this.formatter);
    }
}
