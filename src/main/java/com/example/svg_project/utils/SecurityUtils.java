package com.example.svg_project.utils;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class SecurityUtils {
    public static UserDetails getPrincipal() {
        UserDetails myUser = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return myUser;
    }
}
