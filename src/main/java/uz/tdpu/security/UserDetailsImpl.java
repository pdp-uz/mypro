package uz.tdpu.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import java.util.Collection;

public class UserDetailsImpl implements UserDetails {

    private String username;

    private String password;

    private String  email;

    private String fullName;

    private Collection<GrantedAuthority> grantedAuthorities;


    public UserDetailsImpl(String username, String password, String email, String fullName, Collection<GrantedAuthority> grantedAuthorities) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.fullName = fullName;
        this.grantedAuthorities = grantedAuthorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return grantedAuthorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
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
        return true;
    }
}
