package es.tiendamusica.tiendamusica.security;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import es.tiendamusica.tiendamusica.entity.Clientes;
import es.tiendamusica.tiendamusica.repository.ClientesRepository;



@Component
public class TiendaMusicaAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private ClientesRepository clientesRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(TiendaMusicaAuthenticationProvider.class);

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {
        String user = authentication.getName();
        String password = authentication.getCredentials().toString();

        Set<GrantedAuthority> grantedAuths = new HashSet<>();

        if ("admin".equals(user) && "admin".equals(password)) {

            grantedAuths.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

            UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(
                    user, password, grantedAuths);
            return authenticationContext;

        } else {
            Optional<Clientes> usuarioLogado = clientesRepository.findByNombreAndPassword(user,password);
            if (usuarioLogado.isPresent()) {                

                grantedAuths.add(new SimpleGrantedAuthority("ROLE_USER"));

                UsernamePasswordAuthenticationToken authenticationContext = new UsernamePasswordAuthenticationToken(
                        user, password, grantedAuths);
                return authenticationContext;
               
            } else {
               
                throw new BadCredentialsException("Usuario o contraseña erróneo");
            }
        }

    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }

}
