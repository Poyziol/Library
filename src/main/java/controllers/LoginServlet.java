package controllers;

import java.io.IOException;
import java.util.Optional;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import models.Users;
import services.AdherantService;
import services.TypeUsersService;
import services.UsersService;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

@WebServlet("/login")
public class LoginServlet extends HttpServlet 
{
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
    {
        //PrintWriter out = response.getWriter();
        WebApplicationContext ctx = WebApplicationContextUtils.getRequiredWebApplicationContext(getServletContext());
        UsersService users_service = ctx.getBean(UsersService.class);
        TypeUsersService type_users_service = ctx.getBean(TypeUsersService.class);
        AdherantService adherantService = ctx.getBean(AdherantService.class);

        String name = request.getParameter("username");
        String password = request.getParameter("password");

        Optional<Users> user = users_service.login(name, password);
        if(user.isPresent())
        {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user.get());
            session.setAttribute("type", type_users_service.get(user.get().getIdTypeUsers()).getNom());

            Integer idAdherant = adherantService.getByUserId(user.get().getIdUsers()).getIdAdherant();

            if(idAdherant != null) 
            {
                session.setAttribute("idAdherant", idAdherant);
            } 
            else 
            {
                session.setAttribute("idAdherant", null); 
            }

            response.sendRedirect(request.getContextPath() + "/home");
        }
        else
        {
            request.setAttribute("error", "Nom ou mot de passe incorrect");
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        }
    }
}
