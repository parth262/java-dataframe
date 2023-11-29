package dataframe.api;

import dataframe.transformation.ColumnTransformation;
import dataframe.transformation.any.DirectMap;
import dataframe.transformation.bool.Not;
import dataframe.transformation.any.Identity;
import dataframe.transformation.date.Format;
import dataframe.transformation.string.Capitalize;
import dataframe.transformation.string.Concat;
import dataframe.transformation.string.Split;
import dataframe.transformation.string.Trim;
import dataframe.transformation.supplier.CurrentTimestamp;
import dataframe.transformation.supplier.Literal;

import java.util.Map;

public class Functions {

    public static ColumnTransformation concat(String... columns) {
        return new ColumnTransformation(new Concat(), columns);
    }

    public static ColumnTransformation concat_ws(String separator, String... columns) {
        return new ColumnTransformation(new Concat(separator), columns);
    }

    public static ColumnTransformation not(String column) {
        return new ColumnTransformation(new Not(), column);
    }

    public static ColumnTransformation trim(String column) {
        return new ColumnTransformation(new Trim(), column);
    }

    public static ColumnTransformation split(String regex, String column) {
        return new ColumnTransformation(new Split(regex), column);
    }

    public static ColumnTransformation lit(Object value) {
        return new ColumnTransformation(new Literal(value));
    }

    public static ColumnTransformation currentTimestamp() {
        return new ColumnTransformation(new CurrentTimestamp());
    }

    public static ColumnTransformation capitalize(String column) {
        return new ColumnTransformation(new Capitalize(), column);
    }

    public static ColumnTransformation dateFormat(String column, String pattern) {
        return new ColumnTransformation(new Format(pattern), column);
    }

    public static ColumnTransformation map(String column, Map<Object, Object> cases) {
        return new ColumnTransformation(new DirectMap(cases), column);
    }

    public static ColumnTransformation col(String column) {
        return new ColumnTransformation(new Identity(), column);
    }

}
