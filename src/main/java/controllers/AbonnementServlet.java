package controllers;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import models.Abonnement;
import models.Adherant;
import services.AbonnementService;
import services.AdherantService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/abonnement")
public class AbonnementServlet extends HttpServlet 
{
    @SuppressWarnings("null")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());

        AbonnementService aboService = ctx.getBean(AbonnementService.class);

        HttpSession session = request.getSession(false);
        String typeUsers = (session != null) ? (String) session.getAttribute("type") : null;

        if ("Client".equals(typeUsers)) 
        {
            Integer idAdh = (Integer) session.getAttribute("idAdherant");
            Optional<Abonnement> abo = aboService.findByAdherantId(idAdh);
            request.setAttribute("abonnement", abo.orElse(null));
            request.getRequestDispatcher("/WEB-INF/views/abonnement_client.jsp").forward(request, response);

        } else if ("Bibliothecaire".equals(typeUsers)) 
        {

            List<Abonnement> abonnements = aboService.listAll();
            request.setAttribute("abonnements", abonnements);
            request.getRequestDispatcher("/WEB-INF/views/abonnement_admin.jsp").forward(request, response);

        } else 
        {
            // Si non connecté ou type inconnu, retour à l'accueil
            response.sendRedirect(request.getContextPath() + "/home");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        AbonnementService aboService = ctx.getBean(AbonnementService.class);
        AdherantService adherantService = ctx.getBean(AdherantService.class);
        
        HttpSession session = request.getSession(false);
        String typeUsers = (String) session.getAttribute("type");
        
        if ("Client".equals(typeUsers)) 
        {
            // Création d’un nouvel abonnement client
            Integer idAdh = (Integer) session.getAttribute("idAdherant");
            LocalDate debut = LocalDate.parse(request.getParameter("dateDebut"));
            LocalDate fin   = LocalDate.parse(request.getParameter("dateFin"));
        
            Adherant adh = adherantService.getByUserId(idAdh);
        
            Abonnement abo = new Abonnement();
            abo.setDateDebut(debut);
            abo.setDateFin(fin);
            abo.setAdherant(adh);
            aboService.save(abo);
        
            response.sendRedirect(request.getContextPath() + "/abonnement");
        
        } else if ("Bibliothecaire".equals(typeUsers)) 
        {
            String action = request.getParameter("action");
            Integer idAbo  = Integer.valueOf(request.getParameter("idAbo"));
        
            if ("delete".equals(action)) 
        {
                aboService.delete(idAbo);
            } else if ("renew".equals(action)) 
        {
                Abonnement abo = aboService.getById(idAbo);
                abo.setDateFin(abo.getDateFin().plusYears(1));
                aboService.save(abo);
            }
            response.sendRedirect(request.getContextPath() + "/abonnement");
        }
    }
}
