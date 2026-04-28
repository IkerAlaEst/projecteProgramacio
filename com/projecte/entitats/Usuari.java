package com.projecte.entitats;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;

public class Usuari implements Serializable {
    private ArrayList<Pelicula> peliculesPersonal;
    private ArrayList<Actor> actorsPersonal;
    private ArrayList<Director> directorsPersonal;

    public enum Rol {
        NORMAL, ADMIN
    }

    private String nom;
    private String cognoms;
    private String correu;
    private String poblacio;
    private LocalDate dataNaixement;
    private String contrasenya;
    private Rol rol;

    public Usuari(String nom, String cognoms, String correu, String poblacio, LocalDate dataNaixement,
            String contrasenya, Rol rol) {
        this.nom = nom;
        this.cognoms = cognoms;
        this.correu = correu;
        this.poblacio = poblacio;
        this.dataNaixement = dataNaixement;
        this.contrasenya = contrasenya;
        this.rol = rol;
        this.actorsPersonal = new ArrayList<>();
        this.directorsPersonal = new ArrayList<>();
        this.peliculesPersonal = new ArrayList<>();
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognoms() {
        return cognoms;
    }

    public void setCognoms(String cognoms) {
        this.cognoms = cognoms;
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        this.correu = correu;
    }

    public String getPoblacio() {
        return poblacio;
    }

    public void setPoblacio(String poblacio) {
        this.poblacio = poblacio;
    }

    public LocalDate getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(LocalDate dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    public String getContrasenya() {
        return contrasenya;
    }

    public void setContrasenya(String contrasenya) {
        this.contrasenya = contrasenya;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(String rol) {
        try {
            this.rol = Rol.valueOf(rol.toUpperCase());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}