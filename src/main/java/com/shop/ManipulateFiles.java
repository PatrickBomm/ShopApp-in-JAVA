package com.shop;

import java.io.*;
import java.util.*;

public class ManipulateFiles {

    static Scanner sc = new Scanner(System.in);
    static ArrayList<Itens> allItens = new ArrayList<Itens>();

    public static void gravarArquivo(ArrayList<Itens> a) throws IOException {

        try {
            FileWriter arq = new FileWriter("assets/Items.txt");
            PrintWriter gravarArq = new PrintWriter(arq);
            gravarArq.println(
                    "==========================================================================================");
            gravarArq.println(
                    "                                        Shop Items                                        ");
            gravarArq.println(
                    "==========================================================================================");
            gravarArq.println(
                    "     Id       ||       Name       ||      Price       ||   Quantity   ||      Weight");
            for (Itens i : a) {
                String aux = modifyItensToSave(i);
                gravarArq.println(aux);
            }

            arq.close();
        } catch (IOException e) {
            System.out.println("Error: " + e);
        }
    }

    public static String modifyItensToSave(Itens i) {
        String aux = "";
        if (i.getId() > 9) {
            aux += "      ";
            aux += i.getId() + "      ||";
        } else if (i.getId() > 99) {
            aux += "     ";
            aux += i.getId() + "     ||";
        } else {
            aux += "      ";
            aux += i.getId() + "       ||";
        }

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

        if (i.getPrice() > 9999) {
            aux += "   ";
            aux += i.getPrice() + "   ||";
        } else if (i.getPrice() > 999 && i.getPrice() < 10000) {
            aux += "       ";
            aux += i.getPrice() + "       ||";

        } else if (i.getPrice() > 99 && i.getPrice() < 1000) {
            aux += "       ";
            aux += i.getPrice() + "        ||";
        } else if (i.getPrice() > 9) {
            aux += "       ";
            aux += i.getPrice() + "         ||";
        } else {
            aux += "        ";
            aux += i.getPrice() + "         ||";
        }

        if (i.getQuantity() > 999) {
            aux += "    ";
            aux += i.getQuantity() + "     ||";
        } else if (i.getQuantity() > 99) {
            aux += "     ";
            aux += i.getQuantity() + "      ||";

        } else if (i.getQuantity() > 9) {
            aux += "      ";
            aux += i.getQuantity() + "      ||";
        } else {
            aux += "   ";
            aux += i.getQuantity() + "   ||";
        }

        if (i.getClass().getName().equals("com.shop.FuitsAndVegetables")) {
            FuitsAndVegetables f = (FuitsAndVegetables) i;
            if (f.getWeight() > 999) {
                aux += "      ";
                aux += f.getWeight();
            } else if (f.getWeight() > 99) {
                aux += "       ";
                aux += f.getWeight();
            } else if (f.getWeight() > 9) {
                aux += "       ";
                aux += f.getWeight();
            } else {
                aux += "        ";
                aux += f.getWeight();
            }

        } else {
            aux += "        0";
        }

        return aux;

    }
}
