package com.projecte.menus;

import java.util.ArrayList;
import java.util.Scanner;

import com.projecte.entitats.ListItem;
import com.projecte.entitats.Usuari;
import com.projecte.main.GestorLlistes;

public class MenuLlistats {
   private static Scanner sc = new Scanner(System.in);
   public static void executarMenu(Usuari usuari){
      System.out.println("Benvingut de nou " + usuari.getNom() + " " + usuari.getCognoms());
      String opcion;
      do {
         mostrarMenu();
         opcion = retornarString("Introdueix una opció: ");
         switch (opcion) {
            case "1":
               veureLlistat(usuari);
               break;
            case "2":
               afegirElementLlistat(usuari);
               break;
            case "0":
               System.out.println("Tancant sessió...");
               break;
            default:
               System.out.println("ERROR: Valor introduit (" + opcion + ") no reconegut.");
               break;
         }
      } while (opcion != "0");
   }

   private static void mostrarMenu() {
      System.out.println("--- MENÚ PRINCIPAL ---");
      System.out.println("1.- Veure llistat");
      System.out.println("2.- Afegir elements a un llistat");
      System.out.println("0.- Tancar sessió");
   }

   private static String retornarString(String missatge) {
      System.out.println(missatge);
      return sc.nextLine();
   }

   private static void veureLlistat(Usuari usuari) {
      System.out.println("--- ELIGEIX L'OPERACIÓ A FER ---");
      System.out.println("1.- Veure llistat de películes personal");
      System.out.println("2.- Veure llistat de directors personal");
      System.out.println("3.- Veure llistat de actors personal");
      System.out.println("4.- Veure llistat de películes global");
      System.out.println("5.- Veure llistat de directors global");
      System.out.println("6.- Veure llistat de actors global");
      System.out.println("0.- Cancelar operació");
      String opcion = retornarString("Introdueix què llistat dessitges veure: ");
      ArrayList<ListItem> llista = null;
      switch (opcion) {
         case "1":
            llista = usuari.getLlistaPelis();
            break;
         case "2":
            llista = usuari.getLlistaDire();
            break;
         case "3":
            llista = usuari.getLlistaActor();
            break;
         case "4":
            llista = GestorLlistes.getLlistaPelis();
            break;
         case "5":
            llista = GestorLlistes.getLlistaDire();
            break;
         case "6":
            llista = GestorLlistes.getLlistaActor();
            break;
         case "0":
            System.out.println("Se cancelarà l'operació");
            break;
         default:
            System.out.println("ERROR: Valor introduit (" + opcion + ") no reconegut. Es cancelarà l'operació");
            break;
      }

      if (llista != null) {
         mostrarLlista(llista);
         do {
            opcion = retornarString("Vols veure detalls de un element en específic (s/n)?: ");
         } while (!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("n"));

         if (opcion.equalsIgnoreCase("s")) {
            retornarElementoEnLista(llista);
         }
      }
   }

   private static void afegirElementLlistat(Usuari usuari) {
      
   }

   private static <T> void mostrarLlista(ArrayList<T> llista) {
      int contador = 0;
      for (T element : llista) {
         System.out.println(++contador + ") " + element);
      }
   }

   private static <T> T retornarElementoEnLista(ArrayList<T> llista) {
      if (llista.size() <= 0) {
         System.out.println("ERROR: No se puede elegir un elemento de una lista vacía, deteniendo acción.");
      }
      int opcion;
      do {
         opcion = retornarInt("Introdueix un objecte a elegir: ");
         if (opcion < 1 || opcion > llista.size()) {
            System.out.println("ERROR: Elige un elemento de entre 1 y " + llista.size());
         }
      } while (opcion < 1 || opcion > llista.size());
      return llista.get(opcion);
   }

   private static int retornarInt(String missatge) {
      do {
         System.out.println(missatge);
         if (sc.hasNextInt()) {
            return sc.nextInt();
         } else {
            System.out.println("ERROR: El valor introducido (" + sc.nextLine() + ") no es un número");
         }
      } while (true);
   }
}