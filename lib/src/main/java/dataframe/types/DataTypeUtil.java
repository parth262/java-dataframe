package dataframe.types;

import java.util.Arrays;
import java.util.stream.Collectors;

public class DataTypeUtil {

    public static DataType infer(Object value) {
        if(value instanceof Object[] values) {
            var arrayElementType = inferArrayItemType(values);
            return new ArrayType(arrayElementType);
        }
        if(value instanceof Integer) {
            return new IntegerType();
        }
        if(value instanceof Long) {
            return new LongType();
        }
        if(value instanceof Double) {
            return new DoubleType();
        }
        if(value instanceof Boolean) {
            return new BooleanType();
        }
        if(value instanceof String) {
            return new StringType();
        }
        throw new RuntimeException("Unrecognized dataFrame type for value: " + value);
    }

    public static DataType inferArrayItemType(Object[] values) {
        var valueTypes = Arrays.stream(values)
                .map(DataTypeUtil::infer)
                .collect(Collectors.toSet());
        if (valueTypes.size() == 1) {
            return valueTypes.iterator().next();
        }
        return new AnyType();
    }

}
