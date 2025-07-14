package controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import models.Pret;
import services.PretService;
import services.ProlongementService;

@WebServlet("/prolongement-client")
public class ProlongementClientServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        Integer adherantId = (Integer) session.getAttribute("idAdherant");
        
        // Ajoutez un log
        System.out.println("ID Adhérent récupéré de la session: " + adherantId);
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        PretService pretService = ctx.getBean(PretService.class);
        
        List<Pret> prets = pretService.getPretsEnCoursByAdherant(adherantId);
        
        // Ajoutez un log
        System.out.println("Prêts transmis à la JSP: " + prets.size());
        
        request.setAttribute("prets", prets);
        request.getRequestDispatcher("/WEB-INF/views/prolongementClient.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String pretId = request.getParameter("pretId");
        String jours = request.getParameter("jours");
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProlongementService prolongementService = ctx.getBean(ProlongementService.class);
        
        try {
            prolongementService.demanderProlongement(Integer.parseInt(pretId), Integer.parseInt(jours));
            session.setAttribute("successMessage", "Demande de prolongement envoyée!");
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Erreur: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/prolongement-client");
    }
}