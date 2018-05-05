package org.jianfengderek.damhomework2;

import java.util.ArrayList;
import java.util.List;

public class StatisticsCollector {

    private List<StatisticsItem> statisticsItemList;

    public StatisticsCollector() {
    }

    public List<StatisticsItem> getStatisticsItemList() {
        return statisticsItemList;
    }

    /**
     * Collects an item of statistics.
     *
     * @param rawDataType The type of the raw data.
     * @param itemType The type of the item.
     * @param minsup The support threshold.
     * @param outputLineList The list of the output line.
     */
    public void collect(RawDataType rawDataType,
                        ItemType itemType,
                        int minsup,
                        List<String> outputLineList) {
        String line_transactionCount = outputLineList.get(2);
        Integer transactionCount = Integer.valueOf(line_transactionCount.substring(36,
                line_transactionCount.length()));
        String line_maxMemoryUsage = outputLineList.get(3);
        Double maxMemoryUsage = Double.valueOf(line_maxMemoryUsage.substring(19,
                line_maxMemoryUsage.length() - 4));
        String line_frequentItemSetCount = outputLineList.get(4);
        Integer frequentItemSetCount = Integer.valueOf(line_frequentItemSetCount.substring(27,
                line_frequentItemSetCount.length()));
        String line_totalTimeInMillisSecond = outputLineList.get(5);
        Integer totalTimeInMillisSecond = Integer.valueOf(line_totalTimeInMillisSecond.substring(14, line_totalTimeInMillisSecond.length() - 3));

        StatisticsItem statisticsItem = new StatisticsItem(
                rawDataType,
                itemType,
                minsup,
                transactionCount,
                maxMemoryUsage,
                frequentItemSetCount,
                totalTimeInMillisSecond
        );
        if (null == statisticsItemList) {
            statisticsItemList = new ArrayList<>();
        }
        statisticsItemList.add(statisticsItem);
    }

    /**
     * Extracts the list of all the statistics by rawDataType and itemType.
     *
     * @param rawDataType The type of the raw data.
     * @param itemType The type of the item.
     * @return The list of all the statistics by rawDataType and itemType.
     */
    public List<StatisticsItem> extractStatistics(RawDataType rawDataType, ItemType itemType) {
        List<StatisticsItem> resultList = new ArrayList<>();
        for (StatisticsItem statisticsItem : statisticsItemList) {
            if ((rawDataType == statisticsItem.getRawDataType()) &&
                    itemType == statisticsItem.getItemType()) {
                resultList.add(statisticsItem);
            }
        }

        return resultList;
    }

}
