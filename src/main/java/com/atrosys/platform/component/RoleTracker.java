package com.atrosys.platform.component;

import com.atrosys.platform.Constants;
import com.atrosys.platform.model.bl.UserManager;
import com.atrosys.platform.model.service.DaysService;
import com.atrosys.platform.model.service.RoleService;
import com.atrosys.platform.model.service.UserService;
import com.atrosys.platform.model.service.UserServiceImpl;
import com.atrosys.platform.model.to.Role;
import com.atrosys.platform.model.to.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.*;

/**
 * Created by asgari on 12/26/17.
 */
@Component
public class RoleTracker implements AuthenticationSuccessHandler {
@Autowired
    DaysService daysService;
@Autowired
UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
      //  List <User> users = daysService.findAvailableUsers();

        User user = userService.findUserByEmail(authentication.getName());

    if (!roles.contains(Constants.ROLE_CLIENT) && !user.isAnytimeAccess() && !daysService.isWorkingTimeForUser(user)) {
        SecurityContextHolder.clearContext();

        response.sendRedirect("?status=1");
        return;
    }
    if (user.isRequiredToChangePassword()){


        SimpleGrantedAuthority authority = new SimpleGrantedAuthority("CHANGE");
        List<SimpleGrantedAuthority> updatedAuthorities = new ArrayList<SimpleGrantedAuthority>();
        updatedAuthorities.add(authority);


        SecurityContextHolder.getContext().setAuthentication(
                new UsernamePasswordAuthenticationToken(
                        SecurityContextHolder.getContext().getAuthentication().getPrincipal(),
                        SecurityContextHolder.getContext().getAuthentication().getCredentials(),
                        updatedAuthorities)
        );
        roles = AuthorityUtils.authorityListToSet(SecurityContextHolder.getContext().getAuthentication().getAuthorities());
    }





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
        if (roles.contains("CHANGE")){
            response.sendRedirect("change/password.html");
        }

    }

}
