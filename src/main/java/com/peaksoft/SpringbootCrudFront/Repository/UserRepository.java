package com.peaksoft.SpringbootCrudFront.Repository;

import com.peaksoft.SpringbootCrudFront.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
}
