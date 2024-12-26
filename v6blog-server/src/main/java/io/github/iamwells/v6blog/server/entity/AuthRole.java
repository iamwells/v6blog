package io.github.iamwells.v6blog.server.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "auth_role", schema = "v6blog_auth")
public class AuthRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_role_id_gen")
    @SequenceGenerator(name = "auth_role_id_gen", schema = "v6blog_auth", sequenceName = "seq_auth_role", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 32)
    @NotNull
    @Column(name = "code", nullable = false, length = 32)
    private String code;

    @Size(max = 64)
    @Column(name = "name", length = 64)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

}