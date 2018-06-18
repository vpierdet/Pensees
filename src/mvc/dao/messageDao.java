package mvc.dao;

import mvc.model.message;

import java.util.ArrayList;

public interface messageDao {

    /**
     * permet d'ajouter un message a la base de données
     * @param mes le message a jouter à la base de données
     * @throws DAOException
     */
    void ajouter(message mes)throws DAOException;

    /**
     * permet de supprimer un message de la base de données
     * @param   id du message à supprimer
     * @throws DAOException
     */
    void supprimer(int id)throws DAOException;

    /**
     * permet de modifier un message stocké sur la base de données
     * @param mes le message à modifier
     * @throws DAOException
     */
    void modifier(message mes)throws DAOException;

    void modifierAGDAG(int idMessage, boolean agree, boolean plus)throws DAOException;

    /**
     *
     * @return
     * @throws DAOException
     */
   ArrayList<message> trouverMessageFlagModération(int debut , int fin)throws DAOException;


    /**
     * permet de choisir la tranche de messages a récupérer lorque ceci sont récupérés par ordre de pertinence
     * @param debut debut de la tranche de messages
     * @param fin fin de la tranche de message
     * @return ArrayList<message> la liste de messages selectionnés
     * @throws DAOException
     */
    ArrayList<message> trouverMessagesPertinence(int debut, int fin)throws DAOException;

    /**
     * permet de choisir la tranche de messages a récupérer lorque ceci sont récupérés par date de publication
     * @param debut debut de la tranche de messages
     * @param fin fin de la tranche de message
     * @return ArrayList<message> la liste de messages selectionnés
     * @throws DAOException
     */
    ArrayList<message> trouverMessagesDate(int debut, int fin)throws DAOException;

    /**
     * permet de récupérer les messages publiés par un utilisateur en fonction de son id
     * @param idUser id de l'utilisateur
     * @return ArrayList<message> la liste des messages publiés par cet utilisateur
     * @throws DAOException
     */
    ArrayList<message> trouverMessagesAuteur(int idUser, int debut, int fin)throws DAOException;

    /**
     * per
     * @param idMessage
     * @return
     * @throws DAOException
     */
    message trouverMessage(int idMessage)throws DAOException;


    /**
     *
     * @param categorie
     * @param debut
     * @param fin
     * @return ref
     * @throws DAOException
     */
    ArrayList<message> trouverMessagesCategorie(String categorie, int debut, int fin)throws DAOException;

    int Count(String tri , boolean catego);


}
