package com.atrosys.platform.component;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by asgari on 12/26/17.
 */
@Component
public class AccessDenied implements AccessDeniedHandler {


    @Override
    public void handle(
            HttpServletRequest request,
            HttpServletResponse response,
            AccessDeniedException exc) throws IOException, ServletException {

        Authentication auth
                = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            System.out.println("User: " + auth.getName()
                    + " attempted to access the protected URL: "
                    + request.getRequestURI());

        }

        response.sendRedirect("../access-denied");
    }
}
