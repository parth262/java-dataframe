package dataframe.transformation;

import dataframe.transformation.base.Transformation;

public record ColumnTransformation(
    Transformation transformation,
    String... columns
) {

    public static ColumnTransformation of(Transformation transformation) {
        return new ColumnTransformation(transformation);
    }

    public static ColumnTransformation of(Transformation transformation, String column) {
        return new ColumnTransformation(transformation, column);
    }

    public static ColumnTransformation of(Transformation transformation, String... columns) {
        return new ColumnTransformation(transformation, columns);
    }
}
