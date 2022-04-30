package com.FlightsSystem.FlightsSystem.Login;

public enum AppUserPermission {
    ADMIN_READ("admin:read"),
    ADMIN_WRITE("admin:write"),
    AIRLINE_READ("airline:read"),
    AIRLINE_WRITE("airline:write");

    private final String permission;

    AppUserPermission(String permission) {
        this.permission = permission;
    }

    public String getPermission() {
        return permission;

    }


}
