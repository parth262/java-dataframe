package dataframe.transformation.base;

import dataframe.transformation.types.from.FromVoidTransformation;

import java.util.function.Supplier;

public interface SupplierTransformation extends FromVoidTransformation, Supplier<Object> {
}
