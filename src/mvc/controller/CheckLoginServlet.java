package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import mvc.dao.userDao;
import mvc.dao.DAOFactory;
import mvc.model.user;


@WebServlet(name = "CheckLoginServlet", urlPatterns = {"/checklog"})
public class CheckLoginServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private static final String VUE_LOG = "/vue/log.jsp";
    private static final String SERV_MESS = "/MessageServlet";
    private userDao ud;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.ud = ( (DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getUserDao();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String usernameForm =  request.getParameter("username");
        String passwordForm =  request.getParameter("password");
        user userFound = FindUser(request);
        if (userFound == null){
            System.out.println("utilisateur non trouvé");
            getServletContext().getRequestDispatcher("/log").forward(request,response);
        }
        else{
            if(passwordForm.equals(userFound.getPassword())){
                HttpSession session = request.getSession();
                int idUser = userFound.getIdUser();

                session.setAttribute("userType", userFound.getUserType());
                session.setAttribute("username", usernameForm);
                session.setAttribute("idUser", idUser);

                request.setAttribute("tri", "default");
                getServletContext().getRequestDispatcher(SERV_MESS).forward(request,response);
            }
            else{
                getServletContext().getRequestDispatcher(VUE_LOG).forward(request,response);
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
