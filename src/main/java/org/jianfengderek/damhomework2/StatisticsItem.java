package org.jianfengderek.damhomework2;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class StatisticsItem implements Serializable {

    private RawDataType rawDataType;

    private ItemType itemType;

    private Integer minsup;

    private Integer transactionCount;

    private Double maxMemoryUsage;

    private Integer frequentItemSetsCount;

    private Integer totalTimeInMillisSecond;

    public StatisticsItem() {
    }

    public StatisticsItem(RawDataType rawDataType,
                          ItemType itemType,
                          Integer minsup,
                          Integer transactionCount,
                          Double maxMemoryUsage,
                          Integer frequentItemSetsCount,
                          Integer totalTimeInMillisSecond) {
        this.rawDataType = rawDataType;
        this.itemType = itemType;
        this.minsup = minsup;
        this.transactionCount = transactionCount;
        this.maxMemoryUsage = maxMemoryUsage;
        this.frequentItemSetsCount = frequentItemSetsCount;
        this.totalTimeInMillisSecond = totalTimeInMillisSecond;
    }

    public RawDataType getRawDataType() {
        return rawDataType;
    }

    public void setRawDataType(RawDataType rawDataType) {
        this.rawDataType = rawDataType;
    }

    public ItemType getItemType() {
        return itemType;
    }

    public void setItemType(ItemType itemType) {
        this.itemType = itemType;
    }

    public Integer getMinsup() {
        return minsup;
    }

    public void setMinsup(Integer minsup) {
        this.minsup = minsup;
    }

    public Integer getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Integer transactionCount) {
        this.transactionCount = transactionCount;
    }

    public Double getMaxMemoryUsage() {
        return maxMemoryUsage;
    }

    public void setMaxMemoryUsage(Double maxMemoryUsage) {
        this.maxMemoryUsage = maxMemoryUsage;
    }

    public Integer getFrequentItemSetsCount() {
        return frequentItemSetsCount;
    }

    public void setFrequentItemSetsCount(Integer frequentItemSetsCount) {
        this.frequentItemSetsCount = frequentItemSetsCount;
    }

    public Integer getTotalTimeInMillisSecond() {
        return totalTimeInMillisSecond;
    }

    public void setTotalTimeInMillisSecond(Integer totalTimeInMillisSecond) {
        this.totalTimeInMillisSecond = totalTimeInMillisSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StatisticsItem that = (StatisticsItem) o;
        return rawDataType == that.rawDataType &&
                itemType == that.itemType &&
                Objects.equal(minsup, that.minsup) &&
                Objects.equal(transactionCount, that.transactionCount) &&
                Objects.equal(maxMemoryUsage, that.maxMemoryUsage) &&
                Objects.equal(frequentItemSetsCount, that.frequentItemSetsCount) &&
                Objects.equal(totalTimeInMillisSecond, that.totalTimeInMillisSecond);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rawDataType,
                itemType,
                minsup,
                transactionCount,
                maxMemoryUsage,
                frequentItemSetsCount,
                totalTimeInMillisSecond);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
