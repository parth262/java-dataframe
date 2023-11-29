package dataframe.transformation.string;

import dataframe.transformation.base.to.ToStringTransformation;

public class Mask extends StringTransformation implements ToStringTransformation {
    private int characters = -1;
    private char maskCharacter = '*';
    private Direction direction = Direction.LEFT;

    public Mask() {}

    public Mask(int characters) {
        this.characters = characters;
    }

    public Mask(int characters, String direction) {
        this(characters);
        try {
            this.direction = Direction.valueOf(direction.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("Allowed values for direction are left and right");
        }
    }

    public Mask(int characters, char maskCharacter) {
        this(characters);
        this.maskCharacter = maskCharacter;
    }

    public Mask(int characters, char maskCharacter, String direction) {
        this(characters, direction);
        this.maskCharacter = maskCharacter;
    }

    @Override
    protected Object applyOnString(String s) {
        if(characters <= 0) {
            return String.valueOf(maskCharacter).repeat(s.length());
        }
        var maskString = String.valueOf(maskCharacter).repeat(characters);
        return switch (direction) {
            case LEFT -> maskString + s.substring(characters);
            case RIGHT -> s.substring(0, s.length() - characters) + maskString;
        };
    }

    enum Direction {
        LEFT,
        RIGHT
    }
}
