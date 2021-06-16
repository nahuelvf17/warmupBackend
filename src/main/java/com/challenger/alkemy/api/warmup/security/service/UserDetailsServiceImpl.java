package com.challenger.alkemy.api.warmup.security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.challenger.alkemy.api.warmup.models.entity.Usuario;
import com.challenger.alkemy.api.warmup.security.dto.UsuarioMain;
import com.challenger.alkemy.api.warmup.services.UsuarioService;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    UsuarioService service;

    @Override
    public UserDetails loadUserByUsername(String nombreUsuario) throws UsernameNotFoundException {
        Usuario usuario = service.findUsuarioByEmail(nombreUsuario).get();
        return UsuarioMain.build(usuario);
    }
}
