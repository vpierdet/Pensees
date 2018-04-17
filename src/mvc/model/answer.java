package mvc.model;

import java.io.Serializable;
import java.sql.Timestamp;

public class answer implements Serializable{

    /*---------------------------------- Attributs ----------------------------------*/

    private String txt = "";
    private int idMessage = 0;
    private int idUser = 0;
    private Timestamp date;
    /*-------------------------------- Constructeur  -------------------------------*/

    public answer() {
    }

    /*-------------------------- Getters and Setters  -------------------------------*/

    private int idReponse = 0;

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getTxt() {
        return txt;
    }

    public void setTxt(String txt) {
        this.txt = txt;
    }

    public int getIdMessage() {
        return idMessage;
    }

    public void setIdMessage(int idMessage) {
        this.idMessage = idMessage;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public Timestamp getDate() {
        return date;
    }

    public void setDate(Timestamp date) {
        this.date = date;
    }

}
