package com.projecte.menus;

import com.projecte.entitats.Usuari;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
        int id = 1;

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

                        if (!cont.equals(cont2)) {
                            System.out.println("Les contrasenyes no coincideixen");
                        }
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

                    ArrayList<Usuari> usuaris = new ArrayList<>();

                    File fitxer = new File("usuaris.txt");

                    if (fitxer.exists()) {
                        id = 1;
                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuaris.txt"))) {
                            try {
                                while (true) {
                                    usuaris.add((Usuari) ois.readObject());
                                    id++;
                                }
                            } catch (EOFException e) {
                            }

                        } catch (Exception e) {
                            System.out.println("ERROR: " + e.getMessage());
                            break;
                        }
                    }

                    Usuari user = new Usuari(id, nom, cognom, email, poblacio, data, cont2, Usuari.Rol.NORMAL);

                    usuaris.add(user);

                    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("usuaris.txt"))) {
                        for (Usuari u : usuaris) {
                            oos.writeObject(u);
                        }
                    } catch (Exception e) {
                        System.out.println("Error de guardado " + e.getMessage());
                        break;
                    }

                    // Crear carpeta personal
                    String nomCarpeta = id + email.split("@")[0];
                    File carpetaUsuari = new File(nomCarpeta);
                    carpetaUsuari.mkdir();

                    try {
                        new File(carpetaUsuari, "pelicules.llista").createNewFile();
                        new File(carpetaUsuari, "actors.llista").createNewFile();
                        new File(carpetaUsuari, "directors.llista").createNewFile();
                    } catch (Exception e) {

                    }
                    System.out.println("Usuari creat correctament");
                    break;
                case "2":
                    System.out.println("--- LOGIN ---");

                    boolean loginCorrecte = false;

                    while (!loginCorrecte) {

                        System.out.println("Introdueix el teu nom d'usuari: ");
                        String u = sc.nextLine();
                        System.out.println("Introedueix la teua contrasenya: ");
                        String pswd = sc.nextLine();

                        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("usuaris.txt"))) {
                            ArrayList<Usuari> llista = new ArrayList<Usuari>();

                            try {
                                while (true) {
                                    llista.add((Usuari) ois.readObject());
                                }
                            } catch (EOFException e) {
                            }

                            boolean trobat = false;

                            for (Usuari usuari : llista) {

                                if (usuari.getNom().equals(u) &&
                                        usuari.getContrasenya().equals(pswd)) {

                                    System.out.println("Credencials correctes");
                                    System.out.println("Benvingut " + usuari.getNom() + " " + usuari.getCognoms());

                                    loginCorrecte = true;
                                    trobat = true;

                                    MenuLlistats.executarMenu(usuari);
                                    break;
                                }
                            }
                            if (!trobat) {
                                System.out.println("Usuari o contrasenya incorrectes");
                            }
                        } catch (Exception e) {

                        }
                    }
                    break;
                case "3":

                    System.out.println("Programa tancat");
                    break;
                default:
                    System.out.println("Opcio incorrecta");
            }

        } while (!opcion.equals("3"));
    }
}
