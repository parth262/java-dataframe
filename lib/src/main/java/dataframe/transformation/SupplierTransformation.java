package dataframe.transformation;

import dataframe.transformation.base.from.FromVoidTransformation;

import java.util.function.Supplier;

public interface SupplierTransformation extends FromVoidTransformation, Supplier<Object> {
}
