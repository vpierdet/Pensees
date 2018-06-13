package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.userDao;
import mvc.model.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModerationServlet")
public class ModerationServlet extends HttpServlet {

    private static final String VUE_USERFOUND = "";
    private static final String VUE_SEARCH_USER = "";
    private static final String CONF_DAO_FACTORY = "daofactory";
    private userDao ud;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sujet = request.getParameter("sujet");
        if (sujet.equals("RechercheUser")){
            String un = request.getParameter("username");
            user userFound = ud.trouver(un);
            if (userFound != null){
                request.setAttribute("userFound", userFound);
                getServletContext().getRequestDispatcher(VUE_USERFOUND).forward(request,response);
            }
            else getServletContext().getRequestDispatcher(VUE_SEARCH_USER).forward(request,response);
        }
        else if (sujet.equals( "BanUser")){
            String un = request.getParameter("userFound");
            ud.bannir(un);
            getServletContext().getRequestDispatcher(VUE_SEARCH_USER).forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }
}
