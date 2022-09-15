package com.shop;

import java.io.*;
import java.util.*;

public class ManipulateFiles {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Itens> allItens = new ArrayList<Itens>();

    public static void saveFile(ArrayList<Itens> a) throws IOException {

        try {
            FileWriter file = new FileWriter("assets/Items.txt");
            PrintWriter saveFile = new PrintWriter(file);
            saveFile.println(
                    "==========================================================================================");
            saveFile.println(
                    "                                        Shop Items                                        ");
            saveFile.println(
                    "==========================================================================================");
            saveFile.println(
                    "     Id       ||       Name       ||      Price       ||   Quantity   ||      Weight");
            for (Itens i : a) {
                String aux = modifyItensToSave(i);
                saveFile.println(aux);
            }

            double total = 0;
            for (Itens i : App.itensToSave) {
                total += (i.getPrice() * i.getQuantity());
            }
            saveFile.println(
                    "\n\nTotal value of the shop: R$" + total + "0");

            file.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String modifyItensToSave(Itens i) {
        String aux = "";
        int auxID = String.valueOf(i.getId()).length();
        // Id
        for (int a = 0; a < (14 - auxID) / 2; a++) {
            aux += " ";
        }
        aux += i.getId();
        for (int a = 0; a < (14 - auxID) / 2; a++) {
            aux += " ";
        }
        if ((auxID) % 2 != 0) {
            aux += " ";
        }
        aux += "||";

        // Name
        int auxName = i.getName().length();
        int spacesName = 18 - auxName;
        for (int l = 0; l < spacesName / 2; l++) {
            aux += " ";
        }
        aux += i.getName();
        for (int l = 0; l < spacesName / 2; l++) {
            aux += " ";
        }
        if (auxName % 2 != 0) {
            aux += " ";
        }
        aux += "||";

        // Price
        int auxPrice = String.valueOf(i.getPrice()).length();
        for (int a = 0; a < (18 - auxPrice) / 2; a++) {
            aux += " ";
        }
        aux += i.getPrice();
        for (int a = 0; a < (18 - auxPrice) / 2; a++) {
            aux += " ";
        }
        if ((auxPrice % 2) != 0) {
            aux += " ";
        }
        aux += "||";

        // Quantity
        int auxQuantity = String.valueOf(i.getQuantity()).length();
        for (int a = 0; a < (14 - auxQuantity) / 2; a++) {
            aux += " ";
        }
        aux += i.getQuantity();
        for (int a = 0; a < (14 - auxQuantity) / 2; a++) {
            aux += " ";
        }
        aux += "||";

        // Weight
        if (i.getClass().getName().equals("com.shop.FuitsAndVegetables")) {
            FuitsAndVegetables f = (FuitsAndVegetables) i;
            int auxWeight = String.valueOf(f.getWeight()).length();
            for (int a = 0; a < (9 - auxWeight); a++) {
                aux += " ";
            }
            if (auxWeight % 2 != 0 && auxWeight != 1) {
                aux += " ";
            }
            aux += f.getWeight();
        } else {
            aux += "        0";
        }

        return aux;

    }

    // save the cart in a file
    public static void saveCartAtFile(ArrayList<ItensAtCart> a) throws IOException {

        try {
            FileWriter file = new FileWriter("assets/Cart.txt");
            PrintWriter saveFile = new PrintWriter(file);
            saveFile.println(
                    "==========================================================================================");
            saveFile.println(
                    "                                        Your Cart                                         ");
            saveFile.println(
                    "==========================================================================================");
            saveFile.println(
                    "     Id       ||       Name       ||      Price       ||   Quantity   ||      Weight");
            for (ItensAtCart i : a) {
                String aux = modifyItensToSaveAtCart(i);
                saveFile.println(aux);
            }

            double total = Cart.showTotalValue();

            saveFile.println(
                    "\n\nTotal: R$ " + total + "0");
            file.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String modifyItensToSaveAtCart(ItensAtCart i) {
        String aux = "";

        int auxID = String.valueOf(i.getItem().getId()).length();
        // Id
        for (int a = 0; a < (14 - auxID) / 2; a++) {
            aux += " ";
        }
        aux += i.getItem().getId();
        for (int a = 0; a < (14 - auxID) / 2; a++) {
            aux += " ";
        }
        if ((14 - auxID) % 2 != 0) {
            aux += " ";
        }

        // Name
        int auxName = i.getItem().getName().length();
        int spacesName = 18 - auxName;
        for (int l = 0; l < spacesName / 2; l++) {
            aux += " ";
        }
        aux += i.getItem().getName();
        for (int l = 0; l < spacesName / 2; l++) {
            aux += " ";
        }
        if (auxName % 2 != 0) {
            aux += " ";
        }
        aux += "||";

        // Price
        int auxPrice = String.valueOf(i.getItem().getPrice()).length();
        for (int a = 0; a < (18 - auxPrice) / 2; a++) {
            aux += " ";
        }
        aux += i.getItem().getPrice();
        for (int a = 0; a < (18 - auxPrice) / 2; a++) {
            aux += " ";
        }
        aux += "||";

        // Quantity
        int auxQuantity = String.valueOf(i.getQuantity()).length();
        for (int a = 0; a < (14 - auxQuantity) / 2; a++) {
            aux += " ";
        }
        aux += i.getQuantity();
        for (int a = 0; a < (14 - auxQuantity) / 2; a++) {
            aux += " ";
        }
        aux += "||";

        // Weight
        if (i.getClass().getName().equals("com.shop.FuitsAndVegetables")) {
            FuitsAndVegetables f = (FuitsAndVegetables) i.getItem();
            int auxWeight = String.valueOf(f.getWeight()).length();
            for (int a = 0; a < (9 - auxWeight) / 2; a++) {
                aux += " ";
            }
            aux += f.getWeight();
        } else {
            aux += "        0";
        }

        return aux;

    }

}
