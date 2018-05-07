package mvc.controller;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import mvc.dao.userDao;
import mvc.dao.DAOFactory;
import mvc.model.user;


@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/checklog"})
public class CheckLoginServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    public static final String VUE = "/WEB-INF/testCreationMessage.jsp";
    private userDao ud;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user userFound = FindUser(request);
        if (checkUser(request)) {
            HttpSession session = request.getSession();
            session.setAttribute("idUser", userFound.getIdUser());
            session.setAttribute("username", userFound.getUsername());
            System.out.print("ok");
            getServletContext().getRequestDispatcher(VUE).forward(request,response);
        }
        else{
            getServletContext().getRequestDispatcher("WEB-INF/connexion.jsp").forward(request, response);
        }

    }


    private user FindUser(HttpServletRequest request){
        user userFound = new user();
        userFound = ud.trouver(request.getParameter("username"));
        return userFound;
    }

    private boolean checkUser(HttpServletRequest request){
        user userFound = FindUser(request);
        String pass = request.getParameter("password");
        if (pass.equals(userFound.getPassword())) return true;

     return false;
    }

}
