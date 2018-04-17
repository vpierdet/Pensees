package mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class categorie implements Serializable {

    /*---------------------------------- Attributs ----------------------------------*/

    private String nom = "";
    private String color = "";
    private Set<user> users = new HashSet<>();

    /*-------------------------------- Constructeurs  -------------------------------*/

    public categorie() {
    }

    public categorie(String nom, String color, Set<user> users) {
        this.nom = nom;
        this.color = color;
        this.users = users;
    }



    /*-------------------------- Getters and Setters  -------------------------------*/

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Set<user> getCategories() {
        return users;
    }

    public void setCategories(Set<user> categories) {
        this.users = categories;
    }



}
