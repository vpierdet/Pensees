package mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "DeconexxionServlet", urlPatterns = {"/deco","/DeconexxionServlet"})
public class DeconexxionServlet extends HttpServlet {

    private static final String VUE_LOG = "/vue/log.html";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        getServletContext().getRequestDispatcher(VUE_LOG).forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.invalidate();
        getServletContext().getRequestDispatcher(VUE_LOG).forward(request,response);
    }
}
