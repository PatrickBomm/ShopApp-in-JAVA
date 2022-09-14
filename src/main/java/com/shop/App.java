package com.shop;

import java.io.IOException;
import java.text.ParseException;
import java.util.*;

/**
 * Hello world!
 *
 */
public class App {

    static int passwordTry = 0;
    static double timer = 0;

    static ArrayList<Itens> itensToSave = new ArrayList<Itens>();
    static Scanner sc = new Scanner(System.in);
    static Cart cart = new Cart();

    public static void main(String[] args) throws InterruptedException, IOException {
        try {
            loadItens();
            mainView();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
    }

    public static void loadItens() throws IOException, ParseException {

        // itens
        Itens carrinho = new Itens(0, "Carrinho", 10, 0);
        Itens mouse = new Itens(50, "Mouse", 10, 1);
        Itens teclado = new Itens(100, "Teclado", 10, 2);
        Itens monitor = new Itens(500, "Monitor", 10, 3);
        Itens notebook = new Itens(1000, "Notebook", 10, 4);
        Itens celular = new Itens(2000, "Celular", 10, 5);

        itensToSave.add(carrinho);
        itensToSave.add(mouse);
        itensToSave.add(teclado);
        itensToSave.add(monitor);
        itensToSave.add(notebook);
        itensToSave.add(celular);

        // fruits and vegetables
        FuitsAndVegetables strawberry = new FuitsAndVegetables(30, 1, "Strawberry", 10, 6);
        FuitsAndVegetables pear = new FuitsAndVegetables(35, 1, "Pear", 10, 7);
        FuitsAndVegetables grape = new FuitsAndVegetables(40, 1, "Grape", 10, 8);
        FuitsAndVegetables kiwi = new FuitsAndVegetables(45, 1, "Kiwi", 10, 9);
        FuitsAndVegetables tomato = new FuitsAndVegetables(50, 1, "Tomato", 10, 10);
        FuitsAndVegetables carrot = new FuitsAndVegetables(55, 1, "Carrot", 10, 11);
        FuitsAndVegetables onion = new FuitsAndVegetables(60, 1, "Onion", 10, 12);
        FuitsAndVegetables potato = new FuitsAndVegetables(65, 1, "Potato", 10, 13);

        itensToSave.add(strawberry);
        itensToSave.add(pear);
        itensToSave.add(grape);
        itensToSave.add(kiwi);
        itensToSave.add(tomato);
        itensToSave.add(carrot);
        itensToSave.add(onion);
        itensToSave.add(potato);
        ManipulateFiles.gravarArquivo(itensToSave);
    }

    public static void view() throws InterruptedException, IOException {
        boolean cond = true;
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
        }

        while (cond) {

            System.out.println("\nWelcome to the shop\n");
            System.out.println("1 - Add itens to cart");
            System.out.println("2 - Remove itens from cart");
            System.out.println("3 - Show itens in cart");
            System.out.println("4 - Search for an item by id");
            System.out.println("5 - Remove all itens from cart");
            System.out.println("6 - Total value of the cart");
            System.out.println("7 - Back to the main menu");
            System.out.println("0 - Exit");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    System.out.println("\nItens at shop:\n");
                    for (Itens itens : itensToSave) {
                        System.out.println(itens);
                    }
                    System.out.println("\nType the id of the item you want to add to the cart: ");
                    int id = sc.nextInt();
                    System.out.println("\nType the quantity of the item you want to add to the cart: ");
                    int quantity = sc.nextInt();
                    for (Itens i : itensToSave) {
                        if (i.getId() == id) {
                            if (quantity <= i.getQuantity()) {
                                cart.addItens(i, quantity);
                                i.setQuantity(i.getQuantity() - quantity);
                                ManipulateFiles.gravarArquivo(itensToSave);
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
                            Cart.removeItens(i, quantity2);
                            ManipulateFiles.gravarArquivo(itensToSave);
                            if (Cart.cart.contains(i) == false) {
                                System.out.println("Item removed from cart");
                            }
                        }

                    }
                    break;
                case 3:

                    if (Cart.cart.isEmpty()) {
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
                    for (Itens i : itensToSave) {
                        if (i.getId() == id3) {
                            System.out.println(i);
                        }
                    }
                    break;
                case 5:
                    Cart.removeAllItens();
                    ManipulateFiles.gravarArquivo(itensToSave);
                    if (Cart.cart.isEmpty()) {
                        System.out.println("Cart is empty");
                    }
                    break;
                case 6:
                    cart.showTotalValue();
                    break;
                case 7:
                    cond = false;
                    mainView();
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
                    double startCounter = System.currentTimeMillis();
                    System.out.println("\nGoing back to the Shop menu in 3 seconds");
                    while (System.currentTimeMillis() - startCounter < 3000) {
                    }
                    break;
            }
        }
    }

    public static void viewAdmin() throws InterruptedException, IOException {

        if (passwordTry >= 3) {
            double auxTimer = System.currentTimeMillis();
            if ((auxTimer - timer) > 60000) {
                passwordTry = 0;
            } else {
                if (System.getProperty("os.name").contains("Windows")) {
                    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                }
                System.out.println("\nYou have exceeded the number of attempts! Wait "
                        + (60000 - (auxTimer - timer)) / 1000 + " seconds to try again!\n");
                double startCounter = System.currentTimeMillis();
                while (System.currentTimeMillis() - startCounter < 2000) {
                }
                mainView();
            }
        }
        System.out.println("\nType the password: ");
        String password = sc.next();
        if (password.equals("admin") || password.equals("Admin") || password.equals("ADMIN")
                || password.equals("1234")) {
            boolean cond2 = true;
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }
            while (cond2) {

                System.out.println("\nWelcome to the admin panel\n");
                System.out.println("1 - Add itens to shop");
                System.out.println("2 - Remove itens from shop");
                System.out.println("3 - Show itens in shop");
                System.out.println("4 - Search for an item by id");
                System.out.println("5 - Remove all itens from shop");
                System.out.println("6 - Total value of the shop");
                System.out.println("7 - Back to the main menu");
                System.out.println("0 - Exit");
                int option2 = sc.nextInt();

                switch (option2) {
                    case 1:
                        boolean authID = false;
                        System.out.println("\nType the id of the item you want to add to the shop: ");

                        String ids = "\n";
                        for (Itens i : itensToSave) {
                            ids += i.getId() + ", ";
                        }
                        ids += ".\n";

                        System.out.println("Note: The ids already used: " + ids);
                        int id4 = sc.nextInt();
                        if (id4 < 0) {
                            System.out.println("\nInvalid id");
                            break;
                        }
                        for (Itens i : itensToSave) {
                            if (i.getId() == id4) {
                                System.out.println("\nId already exists");
                                authID = true;
                                break;
                            }
                        }
                        if (authID == true) {
                            double startCounter = System.currentTimeMillis();
                            System.out.println("\nGoing back to the Admin menu in 3 seconds");
                            while (System.currentTimeMillis() - startCounter < 2500) {
                            }
                            break;
                        }
                        System.out.println("\nType the name of the item you want to add to the shop: ");
                        String name = sc.next();
                        if (name.length() < 1) {
                            System.out.println("\nInvalid name");
                            break;
                        }
                        System.out.println("\nType the price of the item you want to add to the shop: ");
                        int price = sc.nextInt();
                        if (price < 0) {
                            System.out.println("\nInvalid price");
                            break;
                        }
                        System.out.println("\nType the quantity of the item you want to add to the shop: ");
                        int quantity3 = sc.nextInt();
                        if (quantity3 < 0) {
                            System.out.println("\nInvalid quantity");
                            break;
                        }
                        System.out.println(
                                "\nType the kg of the item you want to add to the shop: (If the item is not a fruit or vegetable, type 0)");
                        int kg = sc.nextInt();
                        if (kg > 0) {
                            FuitsAndVegetables item = new FuitsAndVegetables(price, kg, name, quantity3, id4);
                            itensToSave.add(item);
                            ManipulateFiles.gravarArquivo(itensToSave);

                        } else {
                            Itens item = new Itens(price, name, quantity3, id4);
                            itensToSave.add(item);
                            ManipulateFiles.gravarArquivo(itensToSave);

                        }
                        break;
                    case 2:
                        System.out.println("\nItens at shop:\n");
                        for (Itens itens : itensToSave) {
                            System.out.println(itens);
                        }
                        System.out.println("\nType the id of the item you want to remove from the shop: ");
                        int id5 = sc.nextInt();
                        System.out.println(
                                "\nType the quantity of the item you want to remove from the shop: ");
                        int quantity4 = sc.nextInt();
                        for (Itens i : itensToSave) {
                            if (i.getId() == id5) {
                                if (quantity4 == i.getQuantity()) {
                                    itensToSave.remove(i);
                                    ManipulateFiles.gravarArquivo(itensToSave);
                                    System.out.println("\nItem removed from shop");
                                    break;
                                }
                                if (quantity4 <= i.getQuantity() && quantity4 > 0) {
                                    i.setQuantity(i.getQuantity() - quantity4);
                                    ManipulateFiles.gravarArquivo(itensToSave);
                                } else {
                                    System.out.println("\nNot enough quantity at shop");
                                    break;
                                }
                            }
                        }
                        break;
                    case 3:
                        System.out.println("\nItens at shop:\n");
                        if (itensToSave.isEmpty()) {
                            System.out.println("\nShop is empty");
                            break;
                        }
                        for (Itens itens : itensToSave) {
                            System.out.println(itens);
                        }
                        break;
                    case 4:
                        if (itensToSave.isEmpty()) {
                            System.out.println("\nShop is empty");
                            break;
                        }
                        System.out.println("\nType the id of the item you want to search: ");
                        int id6 = sc.nextInt();
                        for (Itens i : itensToSave) {
                            if (i.getId() == id6) {
                                System.out.println(i);
                            }
                        }
                        break;
                    case 5:
                        if (itensToSave.isEmpty()) {
                            System.out.println("\nShop is empty");
                            break;
                        }
                        itensToSave.removeAll(itensToSave);
                        ManipulateFiles.gravarArquivo(itensToSave);
                        System.out.println("\nAll itens removed from shop");
                        break;
                    case 6:
                        if (itensToSave.isEmpty()) {
                            System.out.println("\nShop is empty");
                            break;
                        }
                        double total = 0;
                        for (Itens i : itensToSave) {
                            total += (i.getPrice() * i.getQuantity());
                        }
                        System.out.println("\nTotal value of the shop: R$" + total);

                        break;
                    case 7:
                        cond2 = false;
                        break;
                    case 0:
                        cond2 = false;
                        if (System.getProperty("os.name").contains("Windows")) {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        }
                        System.exit(0);
                        break;
                    default:
                        System.out.println("\nInvalid option");
                        double startCounter = System.currentTimeMillis();
                        System.out.println("\nGoing back to the Admin menu in 3 seconds");
                        while (System.currentTimeMillis() - startCounter < 2500) {
                        }
                        break;
                }

            }
        } else {
            System.out.println("\n!!Wrong password!!\n");
            passwordTry++;
            if (passwordTry >= 3) {
                timer = System.currentTimeMillis();
            }
            double startCounter = System.currentTimeMillis();
            System.out.println("\nGoing back to the Main menu in 3 seconds");
            while (System.currentTimeMillis() - startCounter < 2500) {
            }
        }
        mainView();
    }

    public static void mainView() throws InterruptedException, IOException {

        boolean cond = true;

        while (cond) {

            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            }

            System.out.println("\nWelcome to Main menu\n");
            System.out.println("1 - View shop");
            System.out.println("2 - View Admin panel");
            System.out.println("0 - Exit");

            int option = sc.nextInt();

            switch (option) {
                case 1:
                    try {
                        view();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 2:
                    try {
                        viewAdmin();
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case 0:
                    cond = false;
                    if (System.getProperty("os.name").contains("Windows")) {
                        try {
                            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
                        } catch (IOException | InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.exit(0);
                    break;
                default:
                    System.out.println("\nInvalid option");
                    double startCounter = System.currentTimeMillis();
                    System.out.println("\nGoing back to the Main menu in 3 seconds");
                    while (System.currentTimeMillis() - startCounter < 2500) {
                    }
                    break;
            }

        }
    }
}
