package com.shop;
public class Itens {

    private int price;
    private String name;
    private int quantity;
    private int id;

    public Itens(int price, String name, int quantity, int id) {
        this.price = price;
        this.name = name;
        this.quantity = quantity;
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Id=" + id + " || Name=" + name + " || Price=" + price + "\nQuantity=" + quantity;
    }

    public String toStringAux() {
        return "Id=" + id + " || Name=" + name + " || Price=" + price;
    }
}
