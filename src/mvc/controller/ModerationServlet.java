package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.userDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "ModerationServlet", urlPatterns = {"/Modo"})
public class ModerationServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String VUE_NORMAL_ADMIN = "/vue/FileActuAdmin.jsp";
    private userDao ud;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String formName = request.getParameter("formName");
        if(formName.equals("bann")){
            String UserName = request.getParameter("usernameBan");
            this.ud.bannir(UserName);
            getServletContext().getRequestDispatcher(VUE_NORMAL_ADMIN).forward(request,response);

        }

        else if(formName.equals("catego")){
            String AdminName = request.getParameter("adminName");
            String Category = request.getParameter("category");
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
