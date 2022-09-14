package com.shop;

import java.util.*;

public class Cart {

    static ArrayList<ItensAtCart> cart = new ArrayList<ItensAtCart>();
    private static int totalItens;
    private int totalValue;

    public Cart() {
        Cart.totalItens = 0;
        this.totalValue = 0;
    }

    public ArrayList<ItensAtCart> getCart() {
        return cart;
    }

    public int getTotalItens() {
        return totalItens;
    }

    public void setTotalItens(int totalItens) {
        Cart.totalItens = totalItens;
    }

    public int getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(int totalValue) {
        this.totalValue = totalValue;
    }

    public void addItens(Itens item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }
        if (item == null) {
            System.out.println("Item not found");
            return;
        }

        for (ItensAtCart i : cart) {
            if (i.getItem().equals(item)) {
                i.setQuantity(i.getQuantity() + quantity);
                return;
            }
        }

        ItensAtCart itensCart = new ItensAtCart(item, quantity);
        cart.add(itensCart);

        totalItens++;
        int price = item.getPrice() * quantity;
        totalValue += price;

    }

    public static void removeItens(ItensAtCart item, int quantity) {
        if (quantity <= 0) {
            System.out.println("Invalid quantity");
            return;
        }
        if (item == null) {
            System.out.println("Item not found");
            return;
        }
        if (totalItens > 0) {
            for (int i = 0; i < cart.size(); i++) {
                if (quantity > item.getQuantity()) {
                    System.out.println("\nYou can't remove more itens than you have in your cart");
                    return;
                }
                if (cart.get(i).getItem().getId() == item.getItem().getId()) {
                    if (quantity > cart.get(i).getQuantity()) {
                        System.out.println("\nYou can't remove more itens than you have in your cart");
                        return;
                    }
                    if (quantity == cart.get(i).getQuantity()) {
                        int aux = cart.get(i).getQuantity();
                        int quantityItensAtShop = item.getItem().getQuantity();
                        for (ItensAtCart j : cart) {
                            aux = j.getQuantity();
                            quantityItensAtShop = j.getItem().getQuantity();
                            for (Itens itens : App.itensToSave) {
                                if (itens.getId() == j.getItem().getId()) {
                                    itens.setQuantity(quantityItensAtShop + aux);
                                }
                            }
                        }
                        cart.remove(i);
                    } else {
                        int quantityItensAtShop = item.getItem().getQuantity();
                        for (Itens itens : App.itensToSave) {
                            if (itens.getId() == item.getItem().getId()) {
                                itens.setQuantity(quantityItensAtShop + quantity);
                            }
                        }
                        cart.get(i).setQuantity(cart.get(i).getQuantity() - quantity);
                    }
                }
            }

        }
    }

    public void showItens() {
        if (totalItens > 0) {
            for (ItensAtCart item : cart) {
                if (item != null) {
                    System.out.println(item.toString());
                }
            }
        } else {
            System.out.println("\nCart is empty");
        }
    }

    public void showTotalValue() {
        if (cart.isEmpty()) {
            System.out.println("\nCart is empty");
            return;
        } else {
            System.out.println("Total value of the cart: R$" + totalValue + ",00");
        }
    }

    public static void removeAllItens() {
        int aux = 0;
        int quantityItensAtShop = 0;
        for (ItensAtCart i : cart) {
            aux = i.getQuantity();
            quantityItensAtShop = i.getItem().getQuantity();
            for (Itens itens : App.itensToSave) {
                if (itens.getId() == i.getItem().getId()) {
                    itens.setQuantity(quantityItensAtShop + aux);
                }

            }

        }
        cart.clear();
    }
}
