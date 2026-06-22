package br.ufscar.dc.dsw1.security;

import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import br.ufscar.dc.dsw1.dao.IUsuarioDAO;
import br.ufscar.dc.dsw1.domain.Usuario;
 
@Service
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    private final IUsuarioDAO dao;

    public UsuarioDetailsServiceImpl(IUsuarioDAO dao) {
        this.dao = dao;
    }
     
    @Override
    public UserDetails loadUserByUsername(String email)
            throws UsernameNotFoundException {
        Usuario usuario = dao.getUserByEmail(email);
         
        if (usuario == null) {
            throw new UsernameNotFoundException("Could not find user");
        }
         
        return new UsuarioDetails(usuario);
    }
}