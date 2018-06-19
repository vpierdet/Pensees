package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.userDao;
import mvc.dao.categorieDao;
import mvc.model.user;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ModerationServlet", urlPatterns = {"/Modo"})
public class ModerationServlet extends HttpServlet {
    public static final String CONF_DAO_FACTORY = "daofactory";
    private static final String VUE_NORMAL_ADMIN = "/vue/FileActuAdmin.jsp";
    private static final String VUE_MOD = "/vue/moderation.jsp";
    private userDao ud;
    private categorieDao cd;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();
        this.cd = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getCategorierDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String formName = request.getParameter("formName");
        System.out.println(formName);
        if(formName.equals("bann")){
            String UserName = request.getParameter("usernameBan");

            System.out.println(UserName);
            System.out.println(UserName);
            user userFound = ud.trouver(UserName);
            if (userFound != null) {
                this.ud.bannir(UserName);
                out.print(0);
            }
            else out.print(1);

        }

        else if(formName.equals("catego")){
            String AdminName = request.getParameter("adminName");
            String Category = request.getParameter("category");

            user userFound = ud.trouver(AdminName);
            if (userFound != null){
                cd.lier(userFound.getIdUser(), Category);
                out.print(0);
            }
            else out.print(1);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
