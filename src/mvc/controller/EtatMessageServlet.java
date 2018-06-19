package mvc.controller;

import mvc.dao.DAOFactory;
import mvc.dao.etatMessageDao;
import mvc.dao.messageDao;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "EtatMessageServlet", urlPatterns = {"/ems"})
public class EtatMessageServlet extends HttpServlet {
    private static final String CONF_DAO_FACTORY = "daofactory";
    private messageDao md;
    private etatMessageDao emd;

    public void init() {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.md = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY ) ).getMessageDao();
        this.emd = ((DAOFactory) getServletContext().getAttribute( CONF_DAO_FACTORY )).getEtatMessageDAO();
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        int idmes = Integer.parseInt( request.getParameter("idMessage"));
        int iduser =(int) request.getSession().getAttribute("idUser");

        if (action.equals("a+1")){
            this.md.modifierAGDAG(idmes, true,true);
            this.emd.supprimer(iduser, idmes);
            this.emd.ajouter(iduser, idmes, 1);
        }
        else if (action.equals("a-1")){
            this.md.modifierAGDAG(idmes, true,false);
            this.emd.supprimer(iduser, idmes);
            this.emd.ajouter(iduser, idmes, 0);
        }
        else if (action.equals("d+1")){
            this.md.modifierAGDAG(idmes, false,true);
            this.emd.supprimer(iduser, idmes);
            this.emd.ajouter(iduser, idmes, -1);
        }
        else if (action.equals("d-1")){
            this.md.modifierAGDAG(idmes, false, false);
            this.emd.supprimer(iduser, idmes);
            this.emd.ajouter(iduser, idmes, 0);
        }
        else  if (action.equals("getCount")){

        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
