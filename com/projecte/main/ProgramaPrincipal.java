package com.projecte.main;

import com.projecte.andreu.Andreu;
import com.projecte.iker.Iker;
import com.projecte.ivan.Ivan;

public class ProgramaPrincipal {
   public static void main(String[] args) {
      inici();
   }

   private static void inici() {
      System.out.println("Membres de l'equip:");
      Ivan.mostrarNom();
      Andreu.mostrarNom();
      Iker.mostrarNom();
      System.out.println();
   }
}