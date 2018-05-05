package org.jianfengderek.damhomework2;

import com.google.common.base.Objects;
import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.List;

public class Transaction implements Serializable {

    private String uid;

    private String sldat;

    private String vipno;

    private List<Product> productList;

    public Transaction() {}

    public Transaction(String uid, String sldat, String vipno, List<Product> productList) {
        this.uid = uid;
        this.sldat = sldat;
        this.vipno = vipno;
        this.productList = productList;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getSldat() {
        return sldat;
    }

    public void setSldat(String sldat) {
        this.sldat = sldat;
    }

    public String getVipno() {
        return vipno;
    }

    public void setVipno(String vipno) {
        this.vipno = vipno;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void setProductList(List<Product> productList) {
        this.productList = productList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return Objects.equal(uid, that.uid) &&
                Objects.equal(sldat, that.sldat) &&
                Objects.equal(vipno, that.vipno) &&
                Objects.equal(productList, that.productList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(uid, sldat, vipno, productList);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
