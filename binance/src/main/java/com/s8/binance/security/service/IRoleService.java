package com.s8.binance.security.service;

import java.util.Optional;

import com.s8.binance.security.entity.Role;
import com.s8.binance.security.enums.RoleName;

public interface IRoleService {

    public Optional<Role> getByRoleName(RoleName roleName);

    public void save(Role role);
}
