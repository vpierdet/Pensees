package mvc.model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

public class message implements Serializable{

    /*---------------------------------- Attributs ----------------------------------*/

    private String text = "";
    private int idMessage = 0;
    private int idUser = 0;
    private int idReponse = 0;
    private int agree = 0;
    private int disagree = 0;

    private Set<user> destinataires = new HashSet<user>();
    private Set<categorie> categories = new HashSet<>();

    private boolean flagModeration = false;
    private boolean resolu = false;
    private boolean flagNotif = false;

    private Timestamp date;

    /*-------------------------------- Constructeurs  -------------------------------*/

    public message() {
    }

    public message(String text, int idUser, Set<user> destinataires, Set<categorie> categories, Timestamp date) {
        this.text = text;
        this.idUser = idUser;
        this.destinataires = destinataires;
        this.categories = categories;
        this.date = date;
    }

    public message(String text, int idMessage, int idUser, int idReponse, int agree, int disagree, Set<user> destinataires,
                   Set<categorie> categories, boolean flagModeration, boolean resolu, boolean flagNotif, Timestamp date) {
        this.text = text;
        this.idMessage = idMessage;
        this.idUser = idUser;
        this.idReponse = idReponse;
        this.agree = agree;
        this.disagree = disagree;
        this.destinataires = destinataires;
        this.categories = categories;
        this.flagModeration = flagModeration;
        this.resolu = resolu;
        this.flagNotif = flagNotif;
        this.date = date;
    }

    /*-------------------------- Getters and Setters  -------------------------------*/

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
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

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public int getAgree() {
        return agree;
    }

    public void setAgree(int agree) {
        this.agree = agree;
    }

    public int getDisagree() {
        return disagree;
    }

    public void setDisagree(int disagree) {
        this.disagree = disagree;
    }

    public Set<user> getDestinataires() {
        return destinataires;
    }

    public void setDestinataires(Set<user> destinataires) {
        this.destinataires = destinataires;
    }

    public Set<categorie> getCategories() {
        return categories;
    }

    public void setCategories(Set<categorie> categories) {
        this.categories = categories;
    }

    public boolean isFlagModeration() {
        return flagModeration;
    }

    public void setFlagModeration(boolean flagModeration) {
        this.flagModeration = flagModeration;
    }

    public boolean isResolu() {
        return resolu;
    }

    public void setResolu(boolean resolu) {
        this.resolu = resolu;
    }

    public boolean isFlagNotif() {
        return flagNotif;
    }

    public void setFlagNotif(boolean flagNotif) {
        this.flagNotif = flagNotif;
    }

    public Timestamp getTimestamp() {
        return date;
    }

    public void setTimestamp(Timestamp date) {
        this.date = date;
    }




}
