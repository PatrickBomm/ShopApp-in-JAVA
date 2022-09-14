package com.shop;

import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TestCart {

    // testes de adicionar itens ao carrinho

    @Test
    public void testAddItem() {
        Cart cart = new Cart();
        FuitsAndVegetables item = new FuitsAndVegetables(10, 1, "banana", 1, 1);
        cart.addItens(item, 1);
        assertTrue(cart.getCart().size() == 1);
        assertEquals(cart.getCart().get(0).getItem(), item);
    }

    @Test
    public void testAddItem2() {
        Cart cart = new Cart();
        Itens item = new Itens(10, "car", 1, 1);
        cart.addItens(item, 1);
        assertTrue(cart.getCart().get(0).getQuantity() == 1);
        assertEquals(cart.getCart().get(0).getItem().getPrice(), 10);
        assertEquals(cart.getCart().get(0).getItem().getName(), "car");
    }

    @Test
    public void testRemoveItem() {
        Cart cart = new Cart();
        Itens item = new Itens(10, "car", 1, 1);
        cart.addItens(item, 1);
        Cart.removeItens(cart.getCart().get(0), 1);
        assertTrue(cart.getCart().size() == 0);
    }

    @Test
    public void testRemoveItem2() {
        Cart cart = new Cart();
        Itens item = new Itens(10, "car", 1, 1);
        cart.addItens(item, 1);
        Itens item2 = new Itens(10, "ball", 1, 1);
        cart.addItens(item2, 1);
        Cart.removeItens(cart.getCart().get(0), 1);
        assertEquals(cart.getCart().size(), 1);
    }

}
