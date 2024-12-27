package io.github.iamwells.v6blog.server.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "r_user_role", schema = "v6blog_auth")
public class RUserRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "r_user_role_id_gen")
    @SequenceGenerator(name = "r_user_role_id_gen", schema = "v6blog_auth", sequenceName = "seq_r_user_role", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "role_id", nullable = false)
    private Long roleId;

}