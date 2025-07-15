package controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Exemplaire;
import models.Livre;
import services.ExemplaireService;
import services.LivreService;

@WebServlet("/details")
public class DetailsController extends HttpServlet 
{
    private ObjectMapper mapper = new ObjectMapper();

    @SuppressWarnings("unused")
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/plain");

        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        LivreService livreService = ctx.getBean(LivreService.class);
        ExemplaireService exemplaireService = ctx.getBean(ExemplaireService.class);

        String sId = "1";
        if (sId == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Paramètre id manquant");
            return;
        }

        Integer idLivre;
        try {
            idLivre = Integer.valueOf(sId);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "id invalide");
            return;
        }

        // 1) charger le livre en utilisant findById (Optional)
        Optional<Livre> optLivre = livreService.findById(idLivre);
        if (optLivre.isEmpty()) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Livre introuvable : " + idLivre);
            return;
        }
        Livre livre = optLivre.get();

        // 2) récupérer tous les exemplaires et filtrer
        List<Exemplaire> exemplaires = exemplaireService.listAll().stream()
            .filter(e -> e.getLivre().getIdLivre().equals(idLivre))
            .collect(Collectors.toList());

        // 3) construire le JSON
        ObjectNode root = mapper.createObjectNode();
        root.put("id", livre.getIdLivre());
        root.put("titre", livre.getTitre());
        root.put("auteur", livre.getAuteur());
        root.put("anneePublication", livre.getAnneePublication().toString());
        root.put("ageMin", livre.getAgeMin());

        ArrayNode arr = root.putArray("exemplaires");
        for (Exemplaire e : exemplaires) {
            ObjectNode no = mapper.createObjectNode();
            no.put("id", e.getIdExemplaire());
            no.put("disponible", e.getDisponible());
            no.put("etat", e.getEtat().getLibelle());
            arr.add(no);
        }

        response.setContentType("application/json; charset=UTF-8");
        response.setCharacterEncoding("UTF-8");
        mapper.writeValue(response.getWriter(), root);
    }
}
