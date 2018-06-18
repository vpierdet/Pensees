package mvc.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class categorie implements Serializable {

    /*---------------------------------- Attributs ----------------------------------*/

    private String nom = "";
    private int idUser1 = 0;
    private int idUser2 = 0;



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

    public int getIdUser1() {
        return idUser1;
    }

    public void setIdUser1(int idUser1) {
        this.idUser1 = idUser1;
    }

    public int getIdUser2() {
        return idUser2;
    }

    public void setIdUser2(int idUser2) {
        this.idUser2 = idUser2;
    }

    public int getIdCategorie() {
        return idCategorie;
    }

    public void setIdCategorie(int idCategorie) {
        this.idCategorie = idCategorie;
    }
}
