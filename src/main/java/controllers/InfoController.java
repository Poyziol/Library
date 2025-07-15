package controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import services.AdherantService;
import services.AbonnementService;
import services.PretService;
import services.PenaliteService;
import models.Adherant;
import models.Penalite;

@WebServlet("/info")
public class InfoController extends HttpServlet {
    private final ObjectMapper mapper = new ObjectMapper();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // 1) Récupérer l'idAdherant en paramètre
        String sId = "1";
        if (sId == null || sId.isEmpty()) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "idAdherant manquant");
            return;
        }
        Integer idAdherant = Integer.valueOf(sId);

        // 2) Récupérer le contexte Spring
        WebApplicationContext ctx = WebApplicationContextUtils
                .getRequiredWebApplicationContext(getServletContext());

        // 3) Récupérer les services
        AdherantService adherantService     = ctx.getBean(AdherantService.class);
        AbonnementService abonnementService = ctx.getBean(AbonnementService.class);
        PretService pretService             = ctx.getBean(PretService.class);
        PenaliteService penaliteService     = ctx.getBean(PenaliteService.class);

        // 4) Charger l’adhérent
        Adherant adh = adherantService.get(idAdherant);

        // 5) Construire les indicateurs
        boolean isAbonne      = abonnementService.isAbonnementValide(idAdherant);
        int     quotaActuel   = pretService.getQuotaActuel(idAdherant);

        Optional<Penalite> optPen = penaliteService.findActiveByAdherant(idAdherant);
        boolean isSanctionne     = optPen.isPresent();
        String  dateFinSanction  = optPen
            .map(p -> p.getDateDebutPenalite()
                       .plusDays(p.getDuree())
                       .toString())
            .orElse(null);

        // 6) Préparer le payload JSON
        Map<String, Object> payload = new HashMap<>();
        payload.put("idAdherant",      adh.getIdAdherant());
        payload.put("nom",             adh.getNom());
        payload.put("prenom",          adh.getPrenom());
        payload.put("isAbonne",        isAbonne);
        payload.put("quotaActuel",     quotaActuel);
        payload.put("isSanctionne",    isSanctionne);
        payload.put("dateFinSanction", dateFinSanction);

        // 7) Renvoyer en JSON
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getWriter(), payload);
    }
}
