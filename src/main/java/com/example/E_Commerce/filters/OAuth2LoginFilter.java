package com.example.E_Commerce.filters;
import com.example.E_Commerce.service.AuthService;
import com.example.E_Commerce.util.JWTUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import java.io.IOException;


@Component
public class OAuth2LoginFilter implements AuthenticationSuccessHandler
{
    @Autowired
    JWTUtil jwtUtil;

    @Autowired
    AuthService authService;
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException{
        OAuth2User oAuth2User = (OAuth2User) authentication.getPrincipal();
        String email = oAuth2User.getAttribute("email").toString().toLowerCase();
        authService.processOAuth2Login(email);
        String token = jwtUtil.generateJwtToken(email);
        response.getWriter().write("{\n" +
                " \"message\" : \"Login Successful\",\n" +
                " \"token\" : \"" + token+ "\"\n"+
                "}");
    }
}
