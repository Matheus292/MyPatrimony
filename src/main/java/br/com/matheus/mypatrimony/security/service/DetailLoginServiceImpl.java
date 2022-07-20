package br.com.matheus.mypatrimony.security.service;

import br.com.matheus.mypatrimony.auth.repository.ILoginRepository;
import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.security.data.LoginData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DetailLoginServiceImpl implements UserDetailsService {

    @Autowired
    private ILoginRepository repository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Login> object = repository.findByLogin(username);

        if(object.isEmpty())
            throw new UsernameNotFoundException("User [ " + username + " ] not found" );

        return new LoginData(object);
    }
}
