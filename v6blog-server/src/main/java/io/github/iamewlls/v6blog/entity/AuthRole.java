package io.github.iamewlls.v6blog.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.OffsetDateTime;

@Getter
@Setter
@Entity
@Table(name = "auth_role", schema = "v6blog_auth", uniqueConstraints = {
        @UniqueConstraint(name = "uk_ar_code", columnNames = {"code"})
})
public class AuthRole {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "auth_role_id_gen")
    @SequenceGenerator(name = "auth_role_id_gen", sequenceName = "seq_auth_role", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Long id;

    @Size(max = 64)
    @NotNull
    @Column(name = "code", nullable = false, length = 64)
    private String code;

    @Size(max = 128)
    @Column(name = "name", length = 128)
    private String name;

    @Size(max = 255)
    @Column(name = "description")
    private String description;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "create_time", nullable = false)
    private OffsetDateTime createTime;

    @NotNull
    @ColumnDefault("CURRENT_TIMESTAMP")
    @Column(name = "update_time", nullable = false)
    private OffsetDateTime updateTime;

}