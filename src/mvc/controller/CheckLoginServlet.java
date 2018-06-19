package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

import mvc.dao.userDao;
import mvc.dao.DAOFactory;
import mvc.model.user;


@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/checklog"})
public class CheckLoginServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private userDao ud;


    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String usernameForm =  request.getParameter("username");
        String passwordForm =  request.getParameter("password");
        user userFound = FindUser(request);
        if (userFound == null){
            System.out.println("utilisateur non trouvé");
            out.println(2);
        }
        else{
            if(passwordForm.equals(userFound.getPassword())){
                HttpSession session = request.getSession();
                int idUser = userFound.getIdUser();
                session.setAttribute("userType", userFound.getUserType());
                session.setAttribute("username", usernameForm);
                session.setAttribute("idUser", idUser);
                request.setAttribute("tri", "default");
                out.println(0);
            }
            else{
                out.println(1);
            }
        }


    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) {

    }

    private user FindUser(HttpServletRequest request) {
        String usernameForm = request.getParameter("username");
        return ud.trouver(usernameForm);

    }

}
