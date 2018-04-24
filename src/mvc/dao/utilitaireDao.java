package mvc.dao;


import com.sun.deploy.util.ArrayUtil;
import mvc.model.categorie;

import java.sql.*;
import java.util.ArrayList;

public class utilitaireDao {
    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets) throws SQLException {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }
        return preparedStatement;
    }


    /* Fermeture silencieuse du resultset */
    public static void fermetureSilencieuse(ResultSet resultSet) {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void fermetureSilencieuse(Statement statement) {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void fermetureSilencieuse(Connection connexion) {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void fermeturesSilencieuses(Statement statement, Connection connexion) {
        fermetureSilencieuse(statement);
        fermetureSilencieuse(connexion);
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void fermeturesSilencieuses(ResultSet resultSet, Statement statement, Connection connexion) {
        fermetureSilencieuse(resultSet);
        fermetureSilencieuse(statement);
        fermetureSilencieuse(connexion);
    }

    public static String ListToString(ArrayList<String> list){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < list.size(); i++){
            sb.append(list.get(i));
            sb.append("\t");
        }
        return sb.toString();
    }


    public static ArrayList<String> StringToList(String s){
        ArrayList<String> list = new ArrayList<>();
        String [] listString = s.split("\t");
        for (String e:listString) list.add(e);


        return list;
    }


    public static ArrayList<categorie> StringToListCategorie(String s){
        ArrayList<categorie> listCat = new ArrayList<>();
        ArrayList<String> listString = new ArrayList<>();
        listString = StringToList(s);
        for (String e : listString){
            categorie cat = new categorie();
            cat.setNom(e);
            listCat.add(cat);
        }
        return listCat;
    }
}



