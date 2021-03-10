package com.mycompany.assigmentweb.configuration;

import com.mycompany.assigmentweb.util.TargetUrl;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Component
public class CustomSuccessHandler extends SimpleUrlAuthenticationSuccessHandler{
    
    static final Logger logger = LoggerFactory.getLogger(CustomSuccessHandler.class);

    private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
    
    @Autowired
    TargetUrl targetUrl;

    @Override
    protected void handle(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
         String targetUrl = this.targetUrl.determineTargetUrl(authentication);
 
        if (response.isCommitted()) {
            logger.info("Can't redirect...");
            return;
        }
 
        redirectStrategy.sendRedirect(request, response, targetUrl); 
    }
    
    
   
    
    public RedirectStrategy getRedirectStrategy() {
        return redirectStrategy;
    }

    public void setRedirectStrategy(RedirectStrategy redirectStrategy) {
        this.redirectStrategy = redirectStrategy;
    }
    
    
    
    
    
}
