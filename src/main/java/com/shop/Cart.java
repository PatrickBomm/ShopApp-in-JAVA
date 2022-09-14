package com.shop;

import java.util.*;

public class Cart {
    
    ArrayList<Itens> itens = new ArrayList<Itens>();
    private int totalItens;
    private int totalValue;

    public Cart(int totalItens) {
        this.totalItens = 0;
        this.totalValue = 0;
    }

    

    public int getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(int totalItens) {
        this.totalItens = totalItens;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }
    
    public void addItens(Itens item) {
        if (totalItens < itens.size()) {
            itens.get(totalItens) = item;
            totalItens++;
            totalValue += item.getPrice();
        } else {
            System.out.println("Cart is full");
        }
    }
    
    public void removeItens(Itens item) {
        if (totalItens > 0) {
            for (int i = 0; i < itens.length; i++) {
                if (itens[i].getId() == item.getId()) {
                    itens[i] = null;
                    totalItens--;
                    totalValue -= item.getPrice();
                }
            }
        } else {
            System.out.println("Cart is empty");
        }
    }
    
    public void showItens() {
        if (totalItens > 0) {
            for (int i = 0; i < itens.length; i++) {
                if (itens[i] != null) {
                    System.out.println(itens[i].toString());
                }
            }
        } else {
            System.out.println("Cart is empty");
        }
    }
    
    public void showTotalValue() {
        System.out.println("Total value: " + totalValue);
    }

}
