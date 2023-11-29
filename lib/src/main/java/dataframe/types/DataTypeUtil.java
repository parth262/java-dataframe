package dataframe.types;

public class DataTypeUtil {

    public static DataType infer(Object value) {
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
        throw new RuntimeException("Unrecognized data type for value: " + value);
    }

}
