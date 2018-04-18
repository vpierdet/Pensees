package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import mvc.dao.userDao;
import mvc.dao.DAOFactory;
import mvc.model.user;


@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/checklog"})
public class CheckLoginServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String ATT_USER         = "utilisateur";
    public static final String ATT_FORM         = "form";
    public static final String VUE              = "/WEB-INF/inscription.jsp";
    private userDao ud;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user userFound = FindUser(request);

        if (userFound == null){

        }



    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private user FindUser(HttpServletRequest request) {
        String usernameForm = (String) request.getAttribute("username");
        String passwordForm = (String) request.getAttribute("password");
        user userFound = ud.trouver(usernameForm);
        return userFound;

    }

}