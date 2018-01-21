package com.atrosys.platform.component;

import com.atrosys.platform.Constants;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

/**
 * Created by asgari on 12/26/17.
 */
@Component
public class RoleTracker implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if (roles.contains(Constants.ROLE_ADMIN)){
            response.sendRedirect("admin/index.html");
            return;
        }
        if (roles.contains(Constants.ROLE_CLIENT)){
            response.sendRedirect("client/index.html");
            return;
        }
        if (roles.contains(Constants.ROLE_MANAGER)){
            response.sendRedirect("manager/index.html");
            return;
        }
        if (roles.contains(Constants.ROLE_OPERATOR)){
            response.sendRedirect("operator/index.html");
        }

    }
}
