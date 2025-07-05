package controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Adherant;
import models.Penalite;
import models.Pret;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;
import services.AdherantService;
import services.PenaliteService;
import services.PretService;

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
        PretService pretService = ctx.getBean(PretService.class); // Ajouté
        
        String action = request.getParameter("action");
        if("new".equals(action) || "edit".equals(action)) 
        {
            // Charger les données nécessaires pour le formulaire
            List<Adherant> adherants = adherantService.listAll();
            List<Pret> prets = pretService.listAll(); // Charger tous les prêts

            request.setAttribute("adherants", adherants);
            request.setAttribute("prets", prets); // Ajouté
            request.setAttribute("today", LocalDate.now().toString());

            if ("edit".equals(action)) 
            {
                String id = request.getParameter("id");
                if(id != null && !id.isEmpty()) 
                {
                    Penalite penalite = penaliteService.get(Integer.parseInt(id));
                    request.setAttribute("penalite", penalite);
                }
            }

            request.getRequestDispatcher("/WEB-INF/views/penalite_form.jsp").forward(request, response);
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
        throws ServletException, IOException 
    {
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
    
        PenaliteService penaliteService = ctx.getBean(PenaliteService.class);
        AdherantService adherantService = ctx.getBean(AdherantService.class);
        PretService pretService = ctx.getBean(PretService.class);
    
        String action = request.getParameter("action");
        HttpSession session = request.getSession();
        String contextPath = request.getContextPath();
    
        try {
            if("create".equals(action) || "update".equals(action)) 
            {
                Penalite penalite;
            
                if ("update".equals(action)) 
                {
                    String id = request.getParameter("id");
                    if(id == null || id.isEmpty()) {
                        throw new IllegalArgumentException("ID pénalité manquant");
                    }
                    penalite = penaliteService.get(Integer.parseInt(id));
                }
                else 
                {
                    penalite = new Penalite();
                }
            
                // Récupération des données
                String motif = request.getParameter("motif");
                LocalDate dateDebut = LocalDate.parse(request.getParameter("dateDebut"));
                boolean estReglee = request.getParameter("estReglee") != null;
                int duree = Integer.parseInt(request.getParameter("duree"));
            
                // Association de l'adhérent et du prêt
                String idAdherant = request.getParameter("idAdherant");
                String idPret = request.getParameter("idPret");
                
                if(idAdherant == null || idAdherant.isEmpty()) {
                    throw new IllegalArgumentException("Adhérent manquant");
                }
                if(idPret == null || idPret.isEmpty()) {
                    throw new IllegalArgumentException("Prêt manquant");
                }

                Adherant adherant = adherantService.get(Integer.parseInt(idAdherant));
                Pret pret = pretService.get(Integer.parseInt(idPret));

                // Attribution des valeurs
                penalite.setMotif(motif);
                penalite.setDateDebutPenalite(dateDebut);
                penalite.setEstReglee(estReglee);
                penalite.setDuree(duree);
                penalite.setAdherant(adherant);
                penalite.setPret(pret);

                // Sauvegarde
                penaliteService.save(penalite);

                session.setAttribute("successMessage", "Pénalité " + 
                    ("create".equals(action) ? "créée" : "mise à jour") + " avec succès");

            } else if ("delete".equals(action)) 
            {
                String id = request.getParameter("id");
                if (id == null || id.isEmpty()) {
                    throw new IllegalArgumentException("ID pénalité manquant");
                }
                penaliteService.delete(Integer.parseInt(id));
                session.setAttribute("successMessage", "Pénalité supprimée avec succès");

            } else if ("toggle".equals(action)) 
            {
                String id = request.getParameter("id");
                if (id == null || id.isEmpty()) {
                    throw new IllegalArgumentException("ID pénalité manquant");
                }
                Penalite penalite = penaliteService.get(Integer.parseInt(id));
                penalite.setEstReglee(!penalite.getEstReglee());
                penaliteService.save(penalite);
                session.setAttribute("successMessage", "Statut de la pénalité mis à jour");
            }
        } catch (Exception ex) 
        {
            // Journalisation de l'erreur
            ex.printStackTrace();
            session.setAttribute("errorMessage", "Erreur: " + ex.getMessage());
        }

    response.sendRedirect(contextPath + "/penalite");
    }
}