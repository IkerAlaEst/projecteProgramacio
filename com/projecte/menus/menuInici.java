package com.projecte.menus;

import com.projecte.entitats.Usuari;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

public class menuInici {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        String opcion;
        String cont;
        String cont2;
        int id = -1;

        do {

            System.out.println("--- MENU INICI ---");
            System.out.println("1.- Registrarse");
            System.out.println("2.- Iniciar Sessió");
            System.out.println("3.- Tancar el programa");
            System.out.println("Tria una opcio: ");
            opcion = sc.nextLine();

            switch (opcion) {
                case "1":
                    System.out.println("--- REGISTRE ---");
                    System.out.println("Introdueix el teu nom: ");
                    String nom = sc.nextLine();
                    System.out.println("Introdueix els teus cognoms");
                    String cognom = sc.nextLine();
                    System.out.println("Introdueix el correu electronic: ");
                    String email = sc.nextLine();

                    do {
                        System.out.println("Introdueix la teua contrasenya: ");
                        cont = sc.nextLine();
                        System.out.println("Confirma la teua contrasenya");
                        cont2 = sc.nextLine();
                    } while (!cont.equals(cont2));

                    System.out.println("Introdueix la teua poblacio: ");
                    String poblacio = sc.nextLine();
                    LocalDate data;
                    while (true) {
                        try {
                            System.out.println("Introdueix la teua data de naiximent (dd/MM/yyyy): ");
                            data = LocalDate.parse(sc.nextLine(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                            break;
                        } catch (DateTimeParseException e) {
                            System.out.println("Error, introdueix el format correcte");
                        }

                    }
                    ;

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuaris.txt"))) {
                        try {
                            while (true) {
                                ois.readObject();
                                id++;
                            }
                        } catch (EOFException e) {
                        }

                    } catch (Exception e) {
                        System.out.println("ERROR: " + e.getMessage());
                        break;
                    }

                    Usuari user = new Usuari(nom, cognom, email, poblacio, data, cont2, Usuari.Rol.NORMAL);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuaris.txt", true))) {
                        oos.writeObject(user);
                    } catch (Exception e) {
                        System.out.println("Error de guardado " + e.getMessage());
                        break;
                    }
                    System.out.println("Usuario creado correctamenete");
                    break;
                case "2":
                    System.out.println("--- LOGIN ---");
                    System.out.println("Introdueix el teu nom d'usuari: ");
                    String u = sc.nextLine();
                    System.out.println("Introedueix la teua contrasenya: ");
                    String pswd = sc.nextLine();

                    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuaris.txt"))) {
                        ArrayList<Usuari> usuaris = (ArrayList<Usuari>) (ois.readObject());
                        Usuari otro = (Usuari) (ois.readObject());

                        for (Usuari usuari : usuaris) {
                            if (usuari.getNom()
                                    .equals(otro.getNom() && usuari.getContrasenya().equals(otro.getContrasenya()))) {

                            }
                        }
                    } catch (Exception e) {

                    }

                    MenuLlistats.executarMenu(user);
                    break;
                default:
            }

        } while (opcion != "3");
    }
}
