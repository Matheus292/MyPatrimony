package br.com.matheus.mypatrimony.security.data;

import br.com.matheus.mypatrimony.auth.repository.Login;
import br.com.matheus.mypatrimony.general.enums.Status;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;

public class LoginData implements UserDetails {

    private final Optional<Login> login;

    public LoginData(Optional<Login> login) {
        this.login = login;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return new ArrayList<>();
    }

    @Override
    public String getPassword() {
        return login.orElse(new Login()).getPassword();
    }

    @Override
    public String getUsername() {
        return login.orElse(new Login()).getLogin();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        final Login object = login.orElse(new Login());
        return Objects.nonNull(object.getStatus()) && object.getStatus() == Status.ACTIVE;
    }
}
