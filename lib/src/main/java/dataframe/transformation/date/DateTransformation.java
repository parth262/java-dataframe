package dataframe.transformation.date;

import dataframe.transformation.NonNullPrimitiveTransformation;
import dataframe.transformation.base.from.FromDateTransformation;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public abstract class DateTransformation
    extends NonNullPrimitiveTransformation
    implements FromDateTransformation
{

    @Override
    public Object applyPrimitive(Object o) {
        if(o instanceof String s) {
            try {
                var dateValue = LocalDate.parse(s);
                return this.applyOnDate(dateValue);
            } catch (DateTimeParseException e) {
                throw new RuntimeException("value must be a date");
            }
        } else if (!(o instanceof LocalDate)) {
            throw new RuntimeException("value must be a date");
        }
        return this.applyOnDate((LocalDate) o);
    }

    protected abstract Object applyOnDate(LocalDate date);
}
