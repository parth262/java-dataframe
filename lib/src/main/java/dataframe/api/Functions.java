package dataframe.api;

import dataframe.transformation.ColumnTransformation;
import dataframe.transformation.any.*;
import dataframe.transformation.bool.Not;
import dataframe.transformation.date.Format;
import dataframe.transformation.string.*;
import dataframe.transformation.supplier.CurrentTimestamp;
import dataframe.transformation.supplier.Literal;
import dataframe.types.ArrayType;
import dataframe.types.SchemaField;

import java.util.Map;

import static dataframe.transformation.ColumnTransformation.of;

public class Functions {

    public static ColumnTransformation concat(String... columns) {
        return of(new Concat(), columns);
    }

    public static ColumnTransformation concat_ws(String separator, String... columns) {
        return of(new Concat(separator), columns);
    }

    public static ColumnTransformation not(String column) {
        return of(new Not(), column);
    }

    public static ColumnTransformation equals(String column, Object value) {
        return of(new Equals(value), column);
    }

    public static ColumnTransformation trim(String column) {
        return of(new Trim(), column);
    }

    public static ColumnTransformation lit(Object value) {
        return of(new Literal(value));
    }

    public static ColumnTransformation currentTimestamp() {
        return of(new CurrentTimestamp());
    }

    public static ColumnTransformation capitalize(String column) {
        return of(new Capitalize(), column);
    }

    public static ColumnTransformation upper(String column) {
        return of(new Upper(), column);
    }

    public static ColumnTransformation lower(String column) {
        return of(new Lower(), column);
    }

    public static ColumnTransformation dateFormat(String column, String pattern) {
        return of(new Format(pattern), column);
    }

    public static ColumnTransformation map(String column, Map<Object, Object> cases) {
        return of(new DirectMap(cases), column);
    }

    public static ColumnTransformation col(String column) {
        return of(new Identity(), column);
    }

    public static ColumnTransformation mask(String column, int characters, char maskCharacter, String direction) {
        return of(new Mask(characters, maskCharacter, direction), column);
    }

    public static ColumnTransformation replace(String column, String regex, String replaceWith) {
        return of(new RegexReplace(regex, replaceWith), column);
    }

    public static ColumnTransformation substring(String column, int start) {
        return of(new Substring(start), column);
    }

    public static ColumnTransformation substring(String column, int start, int end) {
        return of(new Substring(start, end), column);
    }

    public static ColumnTransformation isEmpty(String column) {
        return of(new IsEmpty(), column);
    }

    public static ColumnTransformation notEmpty(String column) {
        return of(new NotEmpty(), column);
    }

    public static ColumnTransformation isNull(String column) {
        return of(new IsNull(), column);
    }

    public static ColumnTransformation notNull(String column) {
        return of(new NotNull(), column);
    }

    public static ColumnTransformation isEquals(String column, Object value) {
        return of(new Equals(value), column);
    }

    public static ColumnTransformation split(String column, String regex) {
        return of(new Split(regex), column);
    }

    public static ColumnTransformation elementAt(SchemaField field, int at) {
        if(field.dataType() instanceof ArrayType arrayType) {
            return of(new ElementAt(arrayType, at), field.name());
        }
        throw new IllegalArgumentException("column with array type expected, received: " + field.dataType().getSimpleName());
    }

    public static ColumnTransformation pad(
            String column, char paddingCharacter,
            String direction, int maxValueLength
    ) {
        return of(new Padding(paddingCharacter, direction, maxValueLength), column);
    }

}
