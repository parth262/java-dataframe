package dataframe.exceptions;

public class InvalidTransformationOnMultipleColumnsException extends RuntimeException {
    public InvalidTransformationOnMultipleColumnsException(String errorMessage) {
        super(errorMessage);
    }
}
