package dataframe.transformation.number;

import dataframe.transformation.base.PrimitiveArrayTransformation;
import dataframe.transformation.types.array.from.FromNumberArrayTransformation;
import dataframe.transformation.types.array.from.FromStringArrayTransformation;

import java.text.NumberFormat;
import java.text.ParseException;

public abstract class NumberArrayTransformation extends PrimitiveArrayTransformation implements FromNumberArrayTransformation {
    @Override
    protected Object applyArray(Object... o) {
        var numberArray = new Number[o.length];
        for(int i=0;i<o.length;i++) {
            try {
                numberArray[i] = NumberFormat.getInstance().parse(String.valueOf(o[i]));
            } catch (ParseException pe){
                throw new UnsupportedOperationException("Values should be of numeric type, found "+ o[i]);
            }
        }
        return this.applyOnNumber(numberArray);

    }

    protected abstract Object applyOnNumber(Number... s);
}
