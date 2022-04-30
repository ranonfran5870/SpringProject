package com.FlightsSystem.FlightsSystem.Login;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import com.google.common.collect.Sets;
import java.util.Set;
import java.util.stream.Collectors;

import static com.FlightsSystem.FlightsSystem.Login.AppUserPermission.*;

public enum Permissions {
    ANONYMOUS(Sets.newHashSet(), 0),
    CUSTOMER(Sets.newHashSet(AIRLINE_READ,ADMIN_READ,AIRLINE_READ,ADMIN_WRITE), 1),
    ADMIN(Sets.newHashSet(AIRLINE_READ,ADMIN_READ,AIRLINE_READ,ADMIN_WRITE), 2),
    AIRLINE(Sets.newHashSet(AIRLINE_READ,ADMIN_READ,AIRLINE_READ,ADMIN_WRITE), 3);

    private final Set<AppUserPermission> permissions;
    final int levels;
    Permissions(Set<AppUserPermission> permissions, int levels) {
        this.permissions = permissions;
        this.levels = levels;
    }


    public Set<SimpleGrantedAuthority> getGrantedAuthority(){
        Set<SimpleGrantedAuthority> prem = permissions.stream().map(e-> new SimpleGrantedAuthority(e.getPermission()))
                .collect(Collectors.toSet());
        prem.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return prem;
    }
}
