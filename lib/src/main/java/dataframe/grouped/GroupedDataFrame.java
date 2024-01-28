package dataframe.grouped;

import dataframe.DataFrame;

import java.util.HashMap;
import java.util.Map;

public class GroupedDataFrame {

    private final Map<GroupKeys, DataFrame> groupedDataFrame;

    public GroupedDataFrame(Map<GroupKeys, DataFrame> groupedDataFrame) {
        this.groupedDataFrame = new HashMap<>(groupedDataFrame);
    }

    public final DataFrame getDataFrame(Object... keys) {
        var groupKeys = new GroupKeys(keys);
        return this.groupedDataFrame.getOrDefault(groupKeys, DataFrame.empty());
    }

}
