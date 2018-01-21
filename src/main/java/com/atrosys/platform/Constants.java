package com.atrosys.platform;

import com.atrosys.platform.util.Conf;

/**
 * Created by asgari on 12/30/17.
 */
public class Constants {
    // =============================
    // = ROLES
    // =============================
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_MANAGER = "MANAGER";
    public static final String ROLE_OPERATOR = "OPERATOR";
    public static final String ROLE_CLIENT = "CLIENT";

    public static final String ADMIN_HOME = Conf.url().admin().home();
}
