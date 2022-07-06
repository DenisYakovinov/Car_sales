package ru.job4j.cars.filter;

import org.springframework.stereotype.Component;
import ru.job4j.cars.model.User;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AuthFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        String uri = req.getRequestURI();
        if (uri.endsWith("loginPage") || uri.endsWith("login") || uri.endsWith("registration")
                || uri.endsWith("register")) {
            chain.doFilter(req, res);
            return;
        }
        User user = (User) req.getSession().getAttribute("user");
        if (user == null || user.getEmail() == null) {
            res.sendRedirect(req.getContextPath() + "/loginPage");
            return;
        }
        chain.doFilter(req, res);
    }
}
