package dataframe.transformation.string;

import dataframe.transformation.types.to.ToStringTransformation;

public class Padding extends StringTransformation implements ToStringTransformation {

    private final char paddingCharacter;
    private final Direction direction;
    private final int maxValueLength;

    public Padding(char paddingCharacter, String direction, int maxValueLength) {
        this.paddingCharacter = paddingCharacter;
        this.direction = Direction.valueOf(direction.toUpperCase());
        this.maxValueLength = maxValueLength;
    }

    @Override
    protected Object applyOnString(String s) {
        if (maxValueLength <= s.length()) {
            return s;
        }
        var padLength = maxValueLength - s.length();
        var padString = String.valueOf(paddingCharacter).repeat(padLength);
        return switch (direction) {
            case LEFT -> padString + s;
            case RIGHT -> s + padString;
        };
    }

    enum Direction {
        LEFT,
        RIGHT
    }
}
