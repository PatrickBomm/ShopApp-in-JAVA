package com.shop;

public class ItensAtCart {
    
    private Itens item;
    private int quantity;

    public ItensAtCart(Itens item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Itens getItem() {
        return item;
    }

    public void setItem(Itens item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return item.toStringAux() + " || Quantity=" + quantity + "\n";
    }
    
}
