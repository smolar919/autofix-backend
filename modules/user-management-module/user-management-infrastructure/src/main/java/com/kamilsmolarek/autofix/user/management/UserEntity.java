package com.kamilsmolarek.autofix.user.management;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Entity
@Table(name = "user", schema = "public")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserEntity {
    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "created_on")
    private Instant createdOn;

    @Column(name = "deleted_on")
    private Instant deletedOn;

    @Column(name = "deleted_by_id")
    private String deletedById;

    @Column(name = "is_blocked")
    private boolean isBlocked;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;

    public UserEntity(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.createdOn = user.getCreatedOn();
        this.deletedOn = user.getDeletedOn();
        this.deletedById = user.getDeletedById();
        this.isBlocked = user.isBlocked();
        this.role = user.getRole();
    }
}
