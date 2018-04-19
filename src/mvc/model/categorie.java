package mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class categorie implements Serializable {

    /*---------------------------------- Attributs ----------------------------------*/

    private String nom = "";
    private String color = "";
    private Set<user> users = new HashSet<>();
    private int idCategorie = 0;

    /*-------------------------------- Constructeurs  -------------------------------*/

    public categorie() {
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

    public Set<user> getUsers() {
        return users;
    }

    public void setUsers(Set<user> users) {
        this.users = users;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
}
