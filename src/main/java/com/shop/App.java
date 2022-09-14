package com.shop;

import java.io.IOException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {

    static int passwordTry = 0;
    static double timer = 0;

    static ArrayList<Itens> allItens = new ArrayList<Itens>();
    static Scanner sc = new Scanner(System.in);
    static Cart cart = new Cart();

    public static void main(String[] args) throws InterruptedException, IOException {
        loadItens();
        view();
    }

    public static void loadItens() {

        // itens
        Itens carrinho = new Itens(0, "Carrinho", 10, 0);
        Itens mouse = new Itens(50, "Mouse", 10, 1);
        Itens teclado = new Itens(100, "Teclado", 10, 2);
        Itens monitor = new Itens(500, "Monitor", 10, 3);
        Itens notebook = new Itens(1000, "Notebook", 10, 4);
        Itens celular = new Itens(2000, "Celular", 10, 5);

        allItens.add(carrinho);
        allItens.add(mouse);
        allItens.add(teclado);
        allItens.add(monitor);
        allItens.add(notebook);
        allItens.add(celular);

        // fruits and vegetables
        FuitsAndVegetables strawberry = new FuitsAndVegetables(30, 1, "Strawberry", 10, 6);
        FuitsAndVegetables pear = new FuitsAndVegetables(35, 1, "Pear", 10, 7);
        FuitsAndVegetables grape = new FuitsAndVegetables(40, 1, "Grape", 10, 8);
        FuitsAndVegetables kiwi = new FuitsAndVegetables(45, 1, "Kiwi", 10, 9);
        FuitsAndVegetables tomato = new FuitsAndVegetables(50, 1, "Tomato", 10, 10);
        FuitsAndVegetables carrot = new FuitsAndVegetables(55, 1, "Carrot", 10, 11);
        FuitsAndVegetables onion = new FuitsAndVegetables(60, 1, "Onion", 10, 12);
        FuitsAndVegetables potato = new FuitsAndVegetables(65, 1, "Potato", 10, 13);

        allItens.add(strawberry);
        allItens.add(pear);
        allItens.add(grape);
        allItens.add(kiwi);
        allItens.add(tomato);
        allItens.add(carrot);
        allItens.add(onion);
        allItens.add(potato);

    }

    public static void view() throws InterruptedException, IOException {
        boolean cond = true;
        while (cond) {

            System.out.println("\nWelcome to the shop\n");
            System.out.println("1 - Add itens to cart");
            System.out.println("2 - Remove itens from cart");
            System.out.println("3 - Show itens in cart");
            System.out.println("4 - Search for an item by id");
            System.out.println("5 - Remove all itens from cart");
            System.out.println("6 - Total value of the cart");
            System.out.println("7 - Admin panel");
            System.out.println("0 - Exit");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nItens at shop:\n");
                    for (Itens itens : allItens) {
                        System.out.println(itens);
                    }
                    System.out.println("\nType the id of the item you want to add to the cart: ");
                    int id = sc.nextInt();
                    System.out.println("\nType the quantity of the item you want to add to the cart: ");
                    int quantity = sc.nextInt();
                    for (Itens i : allItens) {
                        if (i.getId() == id) {
                            if (quantity <= i.getQuantity()) {
                                cart.addItens(i, quantity);
                                i.setQuantity(i.getQuantity() - quantity);
                            } else {
                                System.out.println("Not enough quantity at shop");
                            }
                        }
                    }
                    break;
                case 2:
                    System.out.println("\nItens in cart:\n");
                    for (ItensAtCart itens : cart.getCart()) {
                        System.out.println(itens);
                    }
                    System.out.println("\nType the id of the item you want to remove from the cart: ");
                    int id2 = sc.nextInt();
                    System.out.println("\nType the quantity of the item you want to remove from the cart: ");
                    int quantity2 = sc.nextInt();
                    for (ItensAtCart i : cart.getCart()) {
                        if (i.getItem().getId() == id2) {
                            cart.removeItens(i, quantity2);
                            if (cart.cart.contains(i) == false) {
                                System.out.println("Item removed from cart");
                            }
                        }

                    }
                    break;
                case 3:

                    if (cart.cart.isEmpty()) {
                        System.out.println("Cart is empty");
                    } else {
                        System.out.println("\nItens in cart:\n");
                        for (ItensAtCart itens : cart.getCart()) {
                            System.out.println(itens);
                        }
                    }
                    break;
                case 4:
                    System.out.println("\nType the id of the item you want to search: ");
                    int id3 = sc.nextInt();
                    for (Itens i : allItens) {
                        if (i.getId() == id3) {
                            System.out.println(i);
                        }
                    }
                    break;
                case 5:
                    cart.removeAllItens();
                    if (cart.cart.isEmpty()) {
                        System.out.println("Cart is empty");
                    }
                    break;
                case 6:
                    cart.showTotalValue();
                    break;
                case 7:
                    if (passwordTry >= 3) {
                        double auxTimer = System.currentTimeMillis();
                        if ((auxTimer - timer) > 1000) {
                            passwordTry = 0;
                            break;
                        } else {
                            if (System.getProperty("os.name").contains("Windows")) {
                                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                            }
                            System.out.println("You have exceeded the number of attempts");
                            break;
                        }
                    }
                    System.out.println("\nType the password: ");
                    String password = sc.next();
                    if (password.equals("admin") || password.equals("Admin") || password.equals("ADMIN")) {
                        boolean cond2 = true;
                        while (cond2) {
                            System.out.println("\nWelcome to the admin panel\n");
                            System.out.println("1 - Add itens to shop");
                            System.out.println("2 - Remove itens from shop");
                            System.out.println("3 - Show itens in shop");
                            System.out.println("4 - Search for an item by id");
                            System.out.println("5 - Remove all itens from shop");
                            System.out.println("6 - Total value of the shop");
                            System.out.println("0 - Exit");
                            int option2 = sc.nextInt();

                            switch (option2) {
                                case 1:
                                    System.out.println("\nType the id of the item you want to add to the shop: ");
                                    int id4 = sc.nextInt();
                                    System.out.println("Type the name of the item you want to add to the shop: ");
                                    String name = sc.next();
                                    System.out.println("Type the price of the item you want to add to the shop: ");
                                    int price = sc.nextInt();
                                    System.out.println("Type the quantity of the item you want to add to the shop: ");
                                    int quantity3 = sc.nextInt();
                                    System.out.println(
                                            "Type the kg of the item you want to add to the shop: (If the item is not a fruit or vegetable, type 0)");
                                    int kg = sc.nextInt();
                                    if (kg > 0) {
                                        FuitsAndVegetables item = new FuitsAndVegetables(id4, price, name, quantity3,
                                                kg);
                                        allItens.add(item);
                                    } else {
                                        Itens item = new Itens(price, name, quantity3, id4);
                                        allItens.add(item);
                                    }
                                    break;
                                case 2:
                                    System.out.println("\nItens at shop:\n");
                                    for (Itens itens : allItens) {
                                        System.out.println(itens);
                                    }
                                    System.out.println("\nType the id of the item you want to remove from the shop: ");
                                    int id5 = sc.nextInt();
                                    System.out.println(
                                            "\nType the quantity of the item you want to remove from the shop: ");
                                    int quantity4 = sc.nextInt();
                                    for (Itens i : allItens) {
                                        if (i.getId() == id5) {
                                            if (quantity4 == i.getQuantity()) {
                                                allItens.remove(i);
                                                System.out.println("Item removed from shop");
                                                break;
                                            }
                                            if (quantity4 <= i.getQuantity() && quantity4 > 0) {
                                                i.setQuantity(i.getQuantity() - quantity4);
                                            } else {
                                                System.out.println("Not enough quantity at shop");
                                            }
                                        }
                                    }
                                    break;
                                case 3:
                                    System.out.println("\nItens at shop:\n");
                                    for (Itens itens : allItens) {
                                        System.out.println(itens);
                                    }
                                    break;
                                case 4:
                                    System.out.println("\nType the id of the item you want to search: ");
                                    int id6 = sc.nextInt();
                                    for (Itens i : allItens) {
                                        if (i.getId() == id6) {
                                            System.out.println(i);
                                        }
                                    }
                                    break;
                                case 5:
                                    allItens.removeAll(allItens);
                                    if (allItens.isEmpty()) {
                                        System.out.println("Shop is empty");
                                    }
                                    break;
                                case 6:
                                    int total = 0;
                                    for (Itens i : allItens) {
                                        total += i.getPrice() * i.getQuantity();
                                    }
                                    System.out.println("Total value of the shop: " + total);
                                    break;
                                case 0:
                                    cond2 = false;
                                    if (System.getProperty("os.name").contains("Windows")) {
                                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                                    }
                                    break;
                            }

                        }
                    } else {
                        System.out.println("Wrong password");
                        passwordTry++;
                        if (passwordTry >= 3) {
                            timer = System.currentTimeMillis();
                        }
                    }
                    break;
                case 0:
                    cond = false;
                    if (System.getProperty("os.name").contains("Windows")) {
                        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid option");
                    break;
            }
        }

    }
}
