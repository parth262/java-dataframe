package dataframe.transformation.supplier;

import dataframe.transformation.SupplierTransformation;
import dataframe.transformation.base.to.ToTimestampTransformation;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class CurrentTimestamp implements SupplierTransformation, ToTimestampTransformation {
    private static final DateTimeFormatter DEFAULT_TIMESTAMP_FORMAT = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public Object get() {
        return ZonedDateTime.now().format(DEFAULT_TIMESTAMP_FORMAT);
    }
}
