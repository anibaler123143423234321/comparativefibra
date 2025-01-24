package api.codesoft.com.comparativefibra.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;

@Component
public class APIKeyAuthFilter extends OncePerRequestFilter {

    @Value("${api.secret.key}")
    private String expectedApiKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {
        String apiKeyFromParam = request.getParameter("bearer");

        // Si coincide la clave
        if (apiKeyFromParam != null && apiKeyFromParam.equals(expectedApiKey)) {
            // Aquí “loggeamos” al usuario con un rol básico, por ejemplo ROLE_USER
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(
                            "apiKeyUser", // Principal
                            null,         // Credentials
                            AuthorityUtils.createAuthorityList("ROLE_USER")
                    );
            // Metemos la autenticación en el contexto de seguridad
            SecurityContextHolder.getContext().setAuthentication(auth);

            // Continuamos la cadena
            filterChain.doFilter(request, response);
            return;
        }

        // Si no coincide, 401
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        response.getWriter().write("Unauthorized - API Key is invalid or missing");
    }
}