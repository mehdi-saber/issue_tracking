package com.atrosys.platform.model.bl;

import com.atrosys.platform.util.Conf;

/**
 * Created by asgari on 12/30/17.
 */
public class Test {
    public static void main(String[] args) {
        System.out.println(Conf.url().admin().home());
        System.out.println(Conf.url().manager().home());
        System.out.println(Conf.url().client().home());
        System.out.println(Conf.url().operator().home());


    }
}
