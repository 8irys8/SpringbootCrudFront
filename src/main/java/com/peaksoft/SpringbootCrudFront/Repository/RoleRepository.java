package com.peaksoft.SpringbootCrudFront.Repository;


import com.peaksoft.SpringbootCrudFront.Model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
