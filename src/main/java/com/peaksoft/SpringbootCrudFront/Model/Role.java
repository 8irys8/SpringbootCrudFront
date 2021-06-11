package com.peaksoft.SpringbootCrudFront.Model;

import com.peaksoft.SpringbootCrudFront.Repository.RoleRepository;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;

@Entity
@Table
@Setter
@Getter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Role implements GrantedAuthority {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    @Column
    String role;

    @Override
    public String getAuthority() {
        return role;
    }
}
