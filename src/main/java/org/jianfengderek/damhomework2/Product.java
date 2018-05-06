package org.jianfengderek.damhomework2;

import com.google.gson.GsonBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.Serializable;
import java.util.Objects;

public class Product implements Serializable {

    // For logging
    private static final Logger logger = LoggerFactory.getLogger(Product.class);

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

    /**
     * Returns the value of the item of {@code itemType}.
     *
     * @param itemType The type of the item.
     * @return The value of the item of {@code itemType}.
     */
    public String item(ItemType itemType) {
        String item = null;

        switch (itemType) {
            case PLUNO: {
                item = pluno;

                break;
            }

            case DPTNO: {
                item = dptno;

                break;
            }

            case BNDNO: {
                item = bndno;

                break;
            }

            default: {
                logger.error("Illegal ItemType: " + itemType);

                break;
            }
        }

        return item;
    }

}
