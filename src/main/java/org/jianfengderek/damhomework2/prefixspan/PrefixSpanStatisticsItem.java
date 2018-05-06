package org.jianfengderek.damhomework2.prefixspan;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;
import org.jianfengderek.damhomework2.ItemType;
import org.jianfengderek.damhomework2.RawDataType;
import org.jianfengderek.damhomework2.StatisticsItem;

import java.io.Serializable;

public class PrefixSpanStatisticsItem extends StatisticsItem implements Serializable {

    private Integer frequentSequencesCount;

    public PrefixSpanStatisticsItem() {
    }

    public PrefixSpanStatisticsItem(RawDataType rawDataType,
                                    ItemType itemType,
                                    Integer minsup,
                                    Double maxMemoryUsage,
                                    Integer frequentSequencesCount,
                                    Integer totalTimeInMillisSecond) {
        super(rawDataType, itemType, minsup, maxMemoryUsage, totalTimeInMillisSecond);

        this.frequentSequencesCount = frequentSequencesCount;
    }

    public Integer getFrequentSequencesCount() {
        return frequentSequencesCount;
    }

    public void setFrequentSequencesCount(Integer frequentSequencesCount) {
        this.frequentSequencesCount = frequentSequencesCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        PrefixSpanStatisticsItem that = (PrefixSpanStatisticsItem) o;
        return Objects.equal(frequentSequencesCount, that.frequentSequencesCount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), frequentSequencesCount);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
