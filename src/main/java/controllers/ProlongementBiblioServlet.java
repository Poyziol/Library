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
import models.Prolongement;
import services.ProlongementService;

@WebServlet("/prolongement-biblio")
public class ProlongementBiblioServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProlongementService prolongementService = ctx.getBean(ProlongementService.class);
        
        List<Prolongement> demandes = prolongementService.getDemandesEnAttente();
        request.setAttribute("demandes", demandes);
        
        request.getRequestDispatcher("/WEB-INF/views/prolongementBibliothecaire.jsp").forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException {
        
        HttpSession session = request.getSession();
        String prolongementId = request.getParameter("prolongementId");
        String action = request.getParameter("action");
        
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        ProlongementService prolongementService = ctx.getBean(ProlongementService.class);
        
        try {
            boolean accepter = "accepter".equals(action);
            prolongementService.traiterProlongement(Integer.parseInt(prolongementId), accepter);
            
            String message = accepter ? 
                "Prolongement accepté avec succès!" : 
                "Prolongement refusé avec succès!";
                
            session.setAttribute("successMessage", message);
        } catch (Exception e) {
            session.setAttribute("errorMessage", "Erreur: " + e.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/prolongement-biblio");
    }
}