package com.sky.sqlite;

import org.litepal.crud.DataSupport;

/**
 * @author 施 凯 沅
 * @version 0.0.1
 */
public class BigBook  extends DataSupport {
    private String name;
    private String author;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "BigBook{" +
                "name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", price=" + price +
                '}';
    }
}
