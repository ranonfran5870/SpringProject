package com.FlightsSystem.FlightsSystem.Facades.SecurityContext;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;

public class SContext {
    public static String  getUserName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public static String  getUserAuthorities(){
        return SecurityContextHolder.getContext().getAuthentication().getAuthorities().stream().findFirst().toString();
    }
}
