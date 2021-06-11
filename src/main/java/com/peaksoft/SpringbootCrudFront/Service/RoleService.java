package com.peaksoft.SpringbootCrudFront.Service;

import com.peaksoft.SpringbootCrudFront.Model.Role;

import java.util.List;

public interface RoleService {
    List<Role> getAllRoles();
    Role getRoleByID(long id);
}
