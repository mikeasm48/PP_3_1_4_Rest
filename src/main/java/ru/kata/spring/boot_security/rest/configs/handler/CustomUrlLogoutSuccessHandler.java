package ru.kata.spring.boot_security.rest.configs.handler;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CustomUrlLogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {
    @Override
    public void onLogoutSuccess(HttpServletRequest request,
                                HttpServletResponse response,
                                Authentication authentication) throws IOException, ServletException {

        // Вывод имени пользователя при выходе
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            request.getSession().setAttribute("Authentication-Name", authentication.getName());
        }

        super.onLogoutSuccess(request, response, authentication);
    }
}
