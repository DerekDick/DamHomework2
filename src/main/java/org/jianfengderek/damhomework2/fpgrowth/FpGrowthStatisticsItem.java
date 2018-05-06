package org.jianfengderek.damhomework2.fpgrowth;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;
import org.jianfengderek.damhomework2.ItemType;
import org.jianfengderek.damhomework2.RawDataType;
import org.jianfengderek.damhomework2.StatisticsItem;

import java.io.Serializable;

public class FpGrowthStatisticsItem extends StatisticsItem implements Serializable {

    private Integer transactionCount;

    private Integer frequentItemSetsCount;

    public FpGrowthStatisticsItem() {
    }

    public FpGrowthStatisticsItem(RawDataType rawDataType,
                                  ItemType itemType,
                                  Integer minsup,
                                  Integer transactionCount,
                                  Double maxMemoryUsage,
                                  Integer frequentItemSetsCount,
                                  Integer totalTimeInMillisSecond) {
        super(rawDataType, itemType, minsup, maxMemoryUsage, totalTimeInMillisSecond);

        this.transactionCount = transactionCount;
        this.frequentItemSetsCount = frequentItemSetsCount;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Integer getFrequentItemSetsCount() {
        return frequentItemSetsCount;
    }

    public void setFrequentItemSetsCount(Integer frequentItemSetsCount) {
        this.frequentItemSetsCount = frequentItemSetsCount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        FpGrowthStatisticsItem that = (FpGrowthStatisticsItem) o;
        return Objects.equal(transactionCount, that.transactionCount) &&
                Objects.equal(frequentItemSetsCount, that.frequentItemSetsCount);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(super.hashCode(), transactionCount, frequentItemSetsCount);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
