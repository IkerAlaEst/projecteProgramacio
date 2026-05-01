package com.projecte.menus;

import java.util.ArrayList;

import com.projecte.entitats.Actor;
import com.projecte.entitats.Director;
import com.projecte.entitats.ListItem;
import com.projecte.entitats.Pelicula;
import com.projecte.entitats.Usuari;
import com.projecte.main.GestorLlistes;

public class MenuLlistats {
   public static void executarMenu(Usuari usuari){
      GestorFitxerUsuaris.actualitzarLlistatsLocals(usuari);
      GestorLlistes.actualitzarLlistatsLocal();
      System.out.println("Benvingut de nou " + usuari.getNom() + " " + usuari.getCognoms());
      String opcion;
      do {
         mostrarMenu();
         opcion = UI.retornarString("Introdueix una opció: ");
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
      System.out.println("\n--- MENÚ PRINCIPAL ---");
      System.out.println("1.- Veure llistat");
      System.out.println("2.- Afegir elements a un llistat");
      System.out.println("0.- Tancar sessió");
   }

   private static void veureLlistat(Usuari usuari) {
      System.out.println("\n--- ELIGEIX L'OPERACIÓ A FER ---");
      System.out.println("1.- Veure llistat de películes personal");
      System.out.println("2.- Veure llistat de directors personal");
      System.out.println("3.- Veure llistat de actors personal");
      System.out.println("4.- Veure llistat de películes global");
      System.out.println("5.- Veure llistat de directors global");
      System.out.println("6.- Veure llistat de actors global");
      System.out.println("0.- Cancelar operació");
      String opcion = UI.retornarString("Introdueix què llistat dessitges veure: ");
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
            opcion = UI.retornarString("Vols veure detalls de un element en específic (s/n)?: ");

         } while (!opcion.equalsIgnoreCase("s") && !opcion.equalsIgnoreCase("n"));

         if (opcion.equalsIgnoreCase("s")) {
            retornarElementoEnLista(llista).mostrarDetalles();
         }
      }
   }

   private static void afegirElementLlistat(Usuari usuari) {
      System.out.println("\n--- ELIGEIX L'OPERACIÓ A FER ---");
      System.out.println("1.- Afegir element a películes personal");
      System.out.println("2.- Afegir element a directors personal");
      System.out.println("3.- Afegir element a actors personal");
      System.out.println("4.- Afegir element a películes global");
      System.out.println("5.- Afegir element a directors global");
      System.out.println("6.- Afegir element a actors global");
      System.out.println("0.- Cancelar operació");
      String opcion = UI.retornarString("Introdueix què llistat dessitges veure: ");
      switch (opcion) {
         case "1":
            try {
               usuari.afegirPeli(new Pelicula(UI.retornarString("Escribe el nombre de la película: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "2":
            try {
               usuari.afegirDire(new Director(UI.retornarString("Escribe el nombre del director: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "3":
            try {
               usuari.afegirAct(new Actor(UI.retornarString("Escribe el nombre del actor: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "4":
            try {
               GestorLlistes.afegirPeli(new Pelicula(UI.retornarString("Escribe el nombre de la pelicula: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "5":
            try {
               GestorLlistes.afegirDire(new Director(UI.retornarString("Escribe el nombre del director: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "6":
            try {
               GestorLlistes.afegirAct(new Actor(UI.retornarString("Escribe el nombre del actor: "), usuari.getLlistaPelis().size() + 1));
            } catch (Exception e) {
               System.out.println(e.getMessage());
            }
            break;
         case "0":
            System.out.println("Se cancelarà l'operació");
            break;
         default:
            System.out.println("ERROR: Valor introduit (" + opcion + ") no reconegut. Es cancelarà l'operació");
            break;
      }
      GestorFitxerUsuaris.actualitzarLlistatsLocals(usuari);
      GestorLlistes.actualitzarLlistatsLocal();
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
         opcion = UI.retornarInt("Introdueix un objecte a elegir: ");
         if (opcion < 1 || opcion > llista.size()) {
            System.out.println("ERROR: Elige un elemento de entre 1 y " + llista.size());
         }
      } while (opcion < 1 || opcion > llista.size());
      return llista.get(opcion);
   }
}