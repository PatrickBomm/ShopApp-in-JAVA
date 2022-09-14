package com.shop;

public class FuitsAndVegetables extends Itens {
    private int weight;

    public FuitsAndVegetables(int price, int weight, String name, int quantity, int id) {
        super(price, name, quantity, id);
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Id=" + getId() + " || Name=" + getName() + " || Price=" + getPrice() + "\nQuantity=" + getQuantity() + " || Weight=" + weight;
    }
    
}
    

