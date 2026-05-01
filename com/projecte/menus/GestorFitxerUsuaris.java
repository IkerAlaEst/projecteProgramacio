package com.projecte.menus;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import com.projecte.entitats.ListItem;
import com.projecte.entitats.Usuari;

public class GestorFitxerUsuaris {
   private static final File FITXER = new File("com\\projecte\\fitxers\\usuaris.txt");
   private static final File CARPETES_USUARIS = new File("com\\projecte\\fitxers\\usuaris\\");

   public static int contarUsuaris() {
      comprobarSiFileExisteix();
      if (FITXER.length() == 0) {
         return 0;
      }
      int cantidad = 0;
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FITXER))) {
         try {
            while (true) {
               ois.readObject();
               cantidad++;
            }
         } catch (EOFException e) {
            return cantidad;
         }
      } catch (ClassNotFoundException | IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut llegir el fitxer " + FITXER.getPath() + ": " + e.getMessage());
      }
   }

   public static void anyadirUsuari(Usuari user) {
      comprobarSiFileExisteix();

      String nomCarpetaUsuaria = String.format("%04d", user.getId());
      int correuPuntTrim = user.getCorreu().indexOf('@');
      if (correuPuntTrim == -1) {
         nomCarpetaUsuaria += user.getCorreu();
      } else {
         nomCarpetaUsuaria += user.getCorreu().substring(0, correuPuntTrim);
      }
      File usuari = new File(CARPETES_USUARIS.getPath() + "\\" + nomCarpetaUsuaria + "/");

      System.out.println(usuari);
      user.setCarpetaPersonal(usuari);

      try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FITXER, true))) {
         oos.writeObject(user);
      } catch (IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut escriure en el fitxer " + FITXER.getPath() + ": " + e.getMessage());
      }

      usuari.mkdirs();
      File actors = new File(usuari.getPath() + "\\actor.llista");
      File directors = new File(usuari.getPath() + "\\director.llista");
      File pelicules = new File(usuari.getPath() + "\\pelicula.llista");
      
      try (
         ObjectOutputStream oosActors = new ObjectOutputStream(new FileOutputStream(actors));
         ObjectOutputStream oosDirectors = new ObjectOutputStream(new FileOutputStream(directors));
         ObjectOutputStream oosPelicules = new ObjectOutputStream(new FileOutputStream(pelicules));
      ) {
         actors.createNewFile();
         oosActors.writeObject(new ArrayList<ListItem>());
         directors.createNewFile();
         oosDirectors.writeObject(new ArrayList<ListItem>());
         pelicules.createNewFile();
         oosPelicules.writeObject(new ArrayList<ListItem>());
      } catch (IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut escriure en el directori " + usuari.getPath() + ": " + e.getMessage());
      }
   }

   public static ArrayList<Usuari> getUsuaris() {
      comprobarSiFileExisteix();
      ArrayList<Usuari> usuaris = new ArrayList<>();
      
      if (FITXER.length() == 0) {
         return usuaris;
      }
      
      try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FITXER))) {
         try {
            while (true) {
               usuaris.add((Usuari) ois.readObject());
            }
         } catch (EOFException e) {
            return usuaris;
         }
      } catch (ClassNotFoundException | IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut llegir el fitxer " + FITXER.getPath() + ": " + e.getMessage());
      }
   }

   private static void comprobarSiFileExisteix() {
      try {
         File directoris = FITXER.getParentFile();

         if (directoris == null || !directoris.exists()) {
            directoris.mkdirs();
         }

         if (!FITXER.exists()) {
            FITXER.createNewFile();
         }
      } catch (IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut crear el fitxer " + FITXER.getPath() + ": " + e.getMessage());
      }
   }

   public static void actualitzarLlistatsLocals(Usuari usuari) {
      ArrayList<ListItem> novaLlistaActors;
      ArrayList<ListItem> novaLlistaDirectors;
      ArrayList<ListItem> novaLlistaPelicules;

      try (
         ObjectInputStream oisActors = new ObjectInputStream(new FileInputStream(usuari.getCarpetaPersonal().getPath() + "\\actor.llista"));
         ObjectInputStream oisDirectors = new ObjectInputStream(new FileInputStream(usuari.getCarpetaPersonal().getPath() + "\\director.llista"));
         ObjectInputStream oisPelicules = new ObjectInputStream(new FileInputStream(usuari.getCarpetaPersonal().getPath() + "\\pelicula.llista"));
      ) {
         novaLlistaActors = (ArrayList<ListItem>) oisActors.readObject();
         novaLlistaDirectors = (ArrayList<ListItem>) oisDirectors.readObject();
         novaLlistaPelicules = (ArrayList<ListItem>) oisPelicules.readObject();
      } catch (IOException | ClassNotFoundException e) {
         throw new RuntimeException("ERROR: No s'ha pogut llegir un fitxer en el directori " + FITXER.getPath() + ": " + e.getMessage());
      }

      usuari.setLlistaActor(novaLlistaActors);
      usuari.setLlistaDire(novaLlistaDirectors);
      usuari.setLlistaPelis(novaLlistaPelicules);
   }

   public static void actualitzarLlistatsFitxers(Usuari usuari) {
      try (
         ObjectOutputStream oosActors = new ObjectOutputStream(new FileOutputStream(usuari.getCarpetaPersonal().getPath() + "\\actor.llista"));
         ObjectOutputStream oosDirectors = new ObjectOutputStream(new FileOutputStream(usuari.getCarpetaPersonal().getPath() + "\\director.llista"));
         ObjectOutputStream oosPelicules = new ObjectOutputStream(new FileOutputStream(usuari.getCarpetaPersonal().getPath() + "\\pelicula.llista"));
      ) {
         oosActors.writeObject(usuari.getLlistaActor());
         oosDirectors.writeObject(usuari.getLlistaDire());
         oosPelicules.writeObject(usuari.getLlistaPelis());
      } catch (IOException e) {
         throw new RuntimeException("ERROR: No s'ha pogut escriure en un fitxer del directori " + usuari.getCarpetaPersonal().getPath() + ": " + e.getMessage());
      }
   }
}
