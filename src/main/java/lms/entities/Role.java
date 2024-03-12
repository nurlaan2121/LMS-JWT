package lms.entities;

import org.springframework.security.core.GrantedAuthority;

public enum Role implements GrantedAuthority{
    ADMIN,
    CLIENT,
    MENTOR,
    INSTRUCTOR;

    @Override
    public String getAuthority() {
        return name();
    }
}
