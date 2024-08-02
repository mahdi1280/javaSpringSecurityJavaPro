package ir.javapro.springsecurityjavapro.config;

import ir.javapro.springsecurityjavapro.model.Token;
import ir.javapro.springsecurityjavapro.repository.TokenRepository;
import ir.javapro.springsecurityjavapro.service.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtService;
    private final TokenRepository tokenRepository;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request
            , HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String authHeader = request.getHeader("Authorization");
        if(Objects.isNull(authHeader)) {
            filterChain.doFilter(request,response);
            return;
        }
        String jwt = authHeader.substring(7);
        if(jwtService.isExpire(jwt)) {
            filterChain.doFilter(request,response);
            return;
        }
        String username = jwtService.extractUserName(jwt);
        if(Objects.nonNull(username) && SecurityContextHolder.getContext().getAuthentication() == null) {
            Token token1 = tokenRepository.findByToken(jwt)
                    .stream().filter(token -> !token.isExpired() && !token.isRevoked())
                    .collect(Collectors.toList())
                    .get(0);
            if(Objects.isNull(token1)) {
                filterChain.doFilter(request,response);
                return;
            }
            UserDetails userDetails = userService.loadUserByUsername(username);
            UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                    new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities());
            usernamePasswordAuthenticationToken.setDetails(
                    new WebAuthenticationDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
        }
        filterChain.doFilter(request,response);
    }
}
