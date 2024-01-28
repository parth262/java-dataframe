package dataframe.transformation;

import dataframe.Row;
import dataframe.transformation.base.PrimitiveArrayTransformation;
import dataframe.transformation.base.PrimitiveTransformation;
import dataframe.transformation.base.SupplierTransformation;

import java.util.Arrays;

public class ColumnTransformationEvaluator {

    public static Object evaluate(ColumnTransformation columnTransformation, Row row) {
        var transformation = columnTransformation.transformation();
        if (transformation instanceof SupplierTransformation) {
            return ((SupplierTransformation) transformation).get();
        }
        if(transformation instanceof PrimitiveArrayTransformation arrayTransformation) {
            var columnValues = Arrays.stream(columnTransformation.columns()).parallel()
                .map(row::get)
                .toArray();
            return arrayTransformation.apply(columnValues);
        }
        if(transformation instanceof PrimitiveTransformation primitiveTransformation) {
            if(columnTransformation.columns().length > 1) {
                var errorMessage = String.format(
                    "%s: This transformation takes only 1 column",
                    primitiveTransformation.getClass().getSimpleName()
                );
                throw new RuntimeException(errorMessage);
            }
            var columnValue = row.get(columnTransformation.columns()[0]);
            return primitiveTransformation.apply(columnValue);
        }
        throw new RuntimeException("Transformation not supported");
    }
}
