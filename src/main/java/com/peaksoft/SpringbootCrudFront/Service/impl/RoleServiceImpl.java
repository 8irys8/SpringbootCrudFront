package com.peaksoft.SpringbootCrudFront.Service.impl;

import com.peaksoft.SpringbootCrudFront.Model.Role;
import com.peaksoft.SpringbootCrudFront.Repository.RoleRepository;
import com.peaksoft.SpringbootCrudFront.Service.RoleService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    @Override
    public Role getRoleByID(long id) {
        return roleRepository.findById(id).get();
    }
}
