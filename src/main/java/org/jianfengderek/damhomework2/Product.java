package org.jianfengderek.damhomework2;

import com.google.gson.GsonBuilder;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    private String pluno;

    private String dptno;

    private String bndno;

    public Product() {}

    public Product(String pluno, String dptno, String bndno) {
        this.pluno = pluno;
        this.dptno = dptno;
        this.bndno = bndno;
    }

    public String getPluno() {
        return pluno;
    }

    public void setPluno(String pluno) {
        this.pluno = pluno;
    }

    public String getDptno() {
        return dptno;
    }

    public void setDptno(String dptno) {
        this.dptno = dptno;
    }

    public String getBndno() {
        return bndno;
    }

    public void setBndno(String bndno) {
        this.bndno = bndno;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(pluno, product.pluno) &&
                Objects.equals(dptno, product.dptno) &&
                Objects.equals(bndno, product.bndno);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pluno, dptno, bndno);
    }

    @Override
    public String toString() {
        return new GsonBuilder().create().toJson(this);
    }

}
