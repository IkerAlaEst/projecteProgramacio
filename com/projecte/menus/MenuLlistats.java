package com.projecte.menus;

import java.util.Scanner;

import com.projecte.entitats.Usuari;

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
               afegirElementLlistat();
               break;
            case "0":
               System.out.println("Tancant sessió...");
               break;
            default:
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
      
   }

   private static void afegirElementLlistat() {
      
   }
}