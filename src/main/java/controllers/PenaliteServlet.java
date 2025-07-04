package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Penalite;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.AdherantService;
import services.PenaliteService;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/penalite")
public class PenaliteServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        
        PenaliteService penaliteService = ctx.getBean(PenaliteService.class);
        AdherantService adherantService = ctx.getBean(AdherantService.class);
        
        String action = request.getParameter("action");
        if("new".equals(action)) 
        {
            // Afficher le formulaire de création
            request.setAttribute("adherants", adherantService.listAll());
            request.setAttribute("today", LocalDate.now().toString());
            request.getRequestDispatcher("/WEB-INF/views/penaliteForm.jsp").forward(request, response);
        } 
        else if("edit".equals(action)) 
        {
            // Afficher le formulaire d'édition
            String id = request.getParameter("id");
            if(id != null && !id.isEmpty()) 
            {
                Penalite penalite = penaliteService.get(Integer.parseInt(id));
                request.setAttribute("penalite", penalite);
                request.setAttribute("adherants", adherantService.listAll());
                request.getRequestDispatcher("/WEB-INF/views/penaliteForm.jsp").forward(request, response);
            } 
            else 
            {
                response.sendRedirect(request.getContextPath() + "/penalite");
            }
        } 
        else 
        {
            // Lister toutes les pénalités
            List<Penalite> penalites = penaliteService.listAll();
            request.setAttribute("penalites", penalites);
            request.getRequestDispatcher("/WEB-INF/views/penalite.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        
        PenaliteService penaliteService = ctx.getBean(PenaliteService.class);
        AdherantService adherantService = ctx.getBean(AdherantService.class);
        
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        
        try {
            if("create".equals(action) || "update".equals(action)) 
            {
                Penalite penalite;
                
                if ("update".equals(action)) 
                {
                    String id = request.getParameter("id");
                    penalite = penaliteService.get(Integer.parseInt(id));
                }
                else 
                {
                    penalite = new Penalite();
                }
                
                // Récupérer les données du formulaire
                penalite.setMotif(request.getParameter("motif"));
                penalite.setDateDebutPenalite(LocalDate.parse(request.getParameter("dateDebut")));
                penalite.setEstReglee(Boolean.parseBoolean(request.getParameter("estReglee")));
                penalite.setDuree(Integer.parseInt(request.getParameter("duree")));
                
                // Associer l'adhérent
                String idAdherant = request.getParameter("idAdherant");
                penalite.setAdherant(adherantService.get(Integer.parseInt(idAdherant)));
                
                // Sauvegarder
                penaliteService.save(penalite);
                
                session.setAttribute("successMessage", "Pénalité " + 
                    ("create".equals(action) ? "créée" : "mise à jour") + " avec succès");
                
            } else if ("delete".equals(action)) 
            {
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) 
                {
                    penaliteService.delete(Integer.parseInt(id));
                    session.setAttribute("successMessage", "Pénalité supprimée avec succès");
                }
            } else if ("toggle".equals(action)) 
            {
                // Basculer l'état réglé/non réglé
                String id = request.getParameter("id");
                if (id != null && !id.isEmpty()) 
                {
                    Penalite penalite = penaliteService.get(Integer.parseInt(id));
                    penalite.setEstReglee(!penalite.getEstReglee());
                    penaliteService.save(penalite);
                    session.setAttribute("successMessage", "Statut de la pénalité mis à jour");
                }
            }
        } catch (Exception ex) 
        {
            session.setAttribute("errorMessage", "Erreur: " + ex.getMessage());
        }
        
        response.sendRedirect(request.getContextPath() + "/penalite");
    }
}