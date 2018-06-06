package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

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
            System.out.println("utilisateur non trouvé");
        }
        else{
            PrintWriter out = response.getWriter();
            out.print(userFound.getUsername() +"     "+ userFound.getMail());
        }
        System.out.println("trouvé");
        getServletContext().getRequestDispatcher("/essai").forward(request,response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    private user FindUser(HttpServletRequest request) {
        String usernameForm = (String) request.getParameter("username");
        String passwordForm = (String) request.getParameter("password");
        user userFound = ud.trouver(usernameForm);
        return userFound;

    }

}
