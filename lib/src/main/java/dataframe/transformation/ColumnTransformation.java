package dataframe.transformation;

import dataframe.transformation.base.Transformation;

public record ColumnTransformation(
    Transformation transformation,
    String... columns
) {}
