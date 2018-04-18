package mvc.model;

import java.io.Serializable;

public class user implements Serializable {

    /*---------------------------------- Attributs ----------------------------------*/
    private int idUser = 0;
    private String password = "";
    private boolean flagBan = false;
    private int userType = 0;
    private String mail = "";
    private String username = "";
    /*-------------------------------- Constructeurs  -------------------------------*/

    public user() {
    }

    /*-------------------------- Getters and Setters  -------------------------------*/

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isFlagBan() {
        return flagBan;
    }

    public void setFlagBan(boolean flagBan) {
        this.flagBan = flagBan;
    }

    public int getUserType() {
        return userType;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}

