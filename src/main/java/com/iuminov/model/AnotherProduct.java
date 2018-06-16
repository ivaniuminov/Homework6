package com.iuminov.model;

import com.iuminov.annotations.Id;
import com.iuminov.annotations.Name;
import com.iuminov.annotations.Price;

public class AnotherProduct<T extends AnotherProduct<T>> implements Comparable<T> {

    @Id
    private long sId;

    @Name
    private String fullName;

    @Price
    private Double sPrice;

    public AnotherProduct() {
    }

    public AnotherProduct(long sId, String fullName, Double sPrice) {
        this.sId = sId;
        this.fullName = fullName;
        this.sPrice = sPrice;
    }

    public AnotherProduct(String fullName, Double sPrice) {
        this.fullName = fullName;
        this.sPrice = sPrice;
    }

    public long getsId() {
        return sId;
    }

    public void setsId(long sId) {
        this.sId = sId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Double getsPrice() {
        return sPrice;
    }

    public void setsPrice(Double sPrice) {
        this.sPrice = sPrice;
    }

    @Override
    public String toString() {
        return "AnotherProduct{" +
                "sId=" + sId +
                ", fullName='" + fullName + '\'' +
                ", sPrice=" + sPrice +
                '}';
    }

    @Override
    public int compareTo(AnotherProduct p) {
        char a = this.fullName.toLowerCase().charAt(0);
        char b = p.fullName.toLowerCase().charAt(0);
        return Character.compare(a, b);
    }
}
