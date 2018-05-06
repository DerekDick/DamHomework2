package org.jianfengderek.damhomework2;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import java.io.Serializable;

public class StatisticsItem implements Serializable {

    private RawDataType rawDataType;

    private ItemType itemType;

    private Integer minsup;

    private Double maxMemoryUsage;

    private Integer totalTimeInMillisSecond;

    public StatisticsItem() {
    }

    public StatisticsItem(RawDataType rawDataType,
                          ItemType itemType,
                          Integer minsup,
                          Double maxMemoryUsage,
                          Integer totalTimeInMillisSecond) {
        this.rawDataType = rawDataType;
        this.itemType = itemType;
        this.minsup = minsup;
        this.maxMemoryUsage = maxMemoryUsage;
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

    public Double getMaxMemoryUsage() {
        return maxMemoryUsage;
    }

    public void setMaxMemoryUsage(Double maxMemoryUsage) {
        this.maxMemoryUsage = maxMemoryUsage;
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
                Objects.equal(maxMemoryUsage, that.maxMemoryUsage) &&
                Objects.equal(totalTimeInMillisSecond, that.totalTimeInMillisSecond);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(rawDataType,
                itemType,
                minsup,
                maxMemoryUsage,
                totalTimeInMillisSecond);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
