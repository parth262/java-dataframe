package transformation;

import dataframe.transformation.base.PrimitiveArrayTransformation;
import dataframe.types.AnyType;
import dataframe.types.BooleanType;
import dataframe.types.DataType;
import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TransformationTests {

    @Test void primitiveArrayTransformation() {
        var values = new Object[] {"a", null, "b"};
        var pAT = new PrimitiveArrayTransformation() {

            @Override
            public DataType getInputType() {
                return new AnyType();
            }

            @Override
            public DataType getOutputType() {
                return new BooleanType();
            }

            @Override
            protected Object applyArray(Object... objects) {
                for (var o: objects) {
                    if(Objects.isNull(o)) {
                        return true;
                    }
                }
                return false;
            }
        };
        var output = pAT.applyArray(values);
        assert output instanceof Boolean;
        var boolOutput = (Boolean) output;
        assertTrue(boolOutput);
    }

}
