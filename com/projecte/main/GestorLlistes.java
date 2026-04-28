package com.projecte.main;

import java.util.ArrayList;
import com.projecte.entitats.Pelicula;
import com.projecte.entitats.Actor;
import com.projecte.entitats.Director;
import com.projecte.entitats.ListItem;

public class GestorLlistes {
    private static ArrayList<ListItem> llistaPelisGeneral = new ArrayList<>();
    private static ArrayList<ListItem> llistaDireGeneral = new ArrayList<>();
    private static ArrayList<ListItem> llistaActorGeneral = new ArrayList<>();

    public static ArrayList<ListItem> getLlistaPelis() {
        return llistaPelisGeneral;
    }

    public static ArrayList<ListItem> getLlistaDire() {
        return llistaDireGeneral;
    }

    public static ArrayList<ListItem> getLlistaActor() {
        return llistaActorGeneral;
    }

    public static void afegirElement(String tipus, Object element) {
        try {
            switch (tipus.toUpperCase()) {
                case "PELICULA":
                    llistaPelisGeneral.add((Pelicula)element);
                    break;
            
                case "DIRECTOR":
                    llistaDireGeneral.add((Director)element);
                    break;

                case "ACTOR":
                    llistaActorGeneral.add((Actor)element);
                    break;
            }
        } catch (Exception e) {
            System.err.println("Error al afegir l'element: " + e.getMessage());
        }
    }
}
