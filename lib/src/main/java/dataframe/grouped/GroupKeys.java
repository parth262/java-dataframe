package dataframe.grouped;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public record GroupKeys(Object... keys) {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GroupKeys groupKeys = (GroupKeys) o;
        return Set.of(keys).containsAll(List.of(groupKeys.keys));
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(keys);
    }
}
