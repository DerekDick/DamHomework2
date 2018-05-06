package org.jianfengderek.damhomework2.prefixspan;

import org.jianfengderek.damhomework2.ItemType;
import org.jianfengderek.damhomework2.RawDataType;

import java.util.ArrayList;
import java.util.List;

public class PrefixSpanStatisticsCollector {
    
    private List<PrefixSpanStatisticsItem> statisticsItemList;
    
    public PrefixSpanStatisticsCollector() {
    }
    
    public List<PrefixSpanStatisticsItem> getStatisticsItemList() {
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
        String line_totalTimeInMillisSecond = outputLineList.get(2);
        Integer totalTimeInMillisSecond = Integer.valueOf(line_totalTimeInMillisSecond.substring(14,
                line_totalTimeInMillisSecond.length() - 3));
        String line_frequentSequencesCount = outputLineList.get(3);
        Integer frequentSequencesCount = Integer.valueOf(line_frequentSequencesCount.substring(28,
                line_frequentSequencesCount.length()));
        String line_maxMemoryUsage = outputLineList.get(4);
        Double maxMemoryUsage = Double.valueOf(line_maxMemoryUsage.substring(18,
                line_maxMemoryUsage.length()));

        PrefixSpanStatisticsItem statisticsItem = new PrefixSpanStatisticsItem(
                rawDataType,
                itemType,
                minsup,
                maxMemoryUsage,
                frequentSequencesCount,
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
    public List<PrefixSpanStatisticsItem> extractStatistics(RawDataType rawDataType,
                                                            ItemType itemType) {
        List<PrefixSpanStatisticsItem> resultList = new ArrayList<>();
        for (PrefixSpanStatisticsItem statisticsItem : statisticsItemList) {
            if ((rawDataType == statisticsItem.getRawDataType()) &&
                    itemType == statisticsItem.getItemType()) {
                resultList.add(statisticsItem);
            }
        }

        return resultList;
    }
    
}
