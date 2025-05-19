package io.github.iamewlls.v6blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "auth_permission", schema = "v6blog_auth", uniqueConstraints = {
        @UniqueConstraint(name = "uk_auth_permission_name", columnNames = {"name"})
})
public class AuthPermission {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_permission_id_gen")
    @SequenceGenerator(name = "auth_permission_id_gen", sequenceName = "seq_auth_permission", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 128)
    @NotNull
    @Column(name = "name", nullable = false, length = 128)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

}