package com.mycompany.assigmentweb.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

@Service("targetUrl")
public class TargetUrl {
    
      public String determineTargetUrl(Authentication authentication) {
        String url = "";
        
        Collection<? extends  GrantedAuthority> authirities = authentication.getAuthorities();
        
        List<String> roles = new ArrayList<String>();
        for (GrantedAuthority authirity : authirities) {
            roles.add(authirity.getAuthority());
            
        }
        if (isAdmin(roles)){
            url = "/admin";
        }else if(isEmploeMenager(roles)){
            url = "/listusers";
        }else if (isWarehouseMenager(roles)) {
            url = "/allproducts";
        }else if (isShopMenager(roles)) {
            url = "/allordersuser";
        }else if (isUser(roles)) {
            url = "/home";
        }else {
            url = "/accessDenied";
        }

        return url;
    }
    
        private boolean isAdmin(List<String> roles) {
        if (roles.contains("ROLE_ADMIN")) {
            return true;
        }
        return false;
    }
    
      private boolean isUser(List<String> roles) {

        if (roles.contains("ROLE_USER")) {
            return true;
        }
        return false;
    }
   
    private boolean isEmploeMenager(List<String> roles) {
        if (roles.contains("ROLE_EMPMENAGER")) {
            return true;
        }
        return false;
    }

    private boolean isShopMenager(List<String> roles) {
        if (roles.contains("ROLE_SHOPMENAGER")) {
            return true;
        }
        return false;
    }

    private boolean isWarehouseMenager(List<String> roles) {
        if (roles.contains("ROLE_WARMENAGER")) {
            return true;
        }
        return false;
    }
    
}
