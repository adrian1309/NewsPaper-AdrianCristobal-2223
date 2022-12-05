package jakarta.servlet;

import dao.DaoLogin;
import dao.impl.DaoLoginImpl;
import domain.model.Login;
import jakarta.inject.Inject;
import jakarta.security.enterprise.identitystore.Pbkdf2PasswordHash;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;
import utils.QueryConstants;


import java.io.IOException;
import java.util.List;

@WebServlet(name = Constants.NAME_SERVLET_HASHES, value = Constants.PATH_SERVLET_HASHES)
public class ServletHashes extends HttpServlet {

    private Pbkdf2PasswordHash passwordHash;

    private DaoLogin daoLogin;


    @Inject
    public ServletHashes(Pbkdf2PasswordHash passwordHash, DaoLoginImpl daoLoginImpl) {
        this.passwordHash = passwordHash;
        this.daoLogin = daoLoginImpl;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        TemplateEngine templateEngine = (TemplateEngine) getServletContext().getAttribute(
                Constants.TEMPLATE_ENGINE_ATTR);
        IWebExchange webExchange = JakartaServletWebApplication.buildApplication(getServletContext())
                .buildExchange(req, resp);
        WebContext context = new WebContext(webExchange);

        req.getSession().setAttribute("LOGIN",false);

        String username = req.getParameter(Constants.VARIABLE_USERNAME);
        String password = req.getParameter(Constants.VARIABLE_PASSWORD);

        if (username != null && password != null) {
            req.getSession().setAttribute(Constants.VARIABLE_USERNAME, username);
            req.getSession().setAttribute(Constants.VARIABLE_PASSWORD, password);
            String hash = passwordHash.generate(password.toCharArray());
            List<Login> users = daoLogin.findAll().get();
            for (Login user : users) {
                if (user.getUser().equals(username)) {
                    if (passwordHash.verify(user.getPassword().toCharArray(), hash)){
                        req.getSession().setAttribute("LOGIN",true);
                        resp.sendRedirect("/NewsPaper-AdrianCristobal-2223-1.0-SNAPSHOT/api/reader");
                    } else {
                        resp.sendRedirect("/NewsPaper-AdrianCristobal-2223-1.0-SNAPSHOT/login");
                    }
                } else {
                    resp.sendRedirect("/NewsPaper-AdrianCristobal-2223-1.0-SNAPSHOT/login");
                }
            }


            if (verificado) {
                req.getSession().setAttribute("LOGIN",true);
                resp.sendRedirect("/NewsPaper-AdrianCristobal-2223-1.0-SNAPSHOT/api/reader");
            } else {
                resp.sendRedirect("/NewsPaper-AdrianCristobal-2223-1.0-SNAPSHOT/login");
            }
        }


        templateEngine.process(Constants.HTML_LOGIN, context, resp.getWriter());
        resp.getWriter().println(password);


    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
