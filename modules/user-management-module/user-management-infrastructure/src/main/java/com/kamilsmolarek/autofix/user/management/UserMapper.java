package com.kamilsmolarek.autofix.user.management;

public class UserMapper {

    public static User toUser(UserEntity userEntity) {
        return User.builder()
                .id(userEntity.getId())
                .firstName(userEntity.getFirstName())
                .lastName(userEntity.getLastName())
                .email(userEntity.getEmail())
                .createdOn(userEntity.getCreatedOn())
                .deletedOn(userEntity.getDeletedOn())
                .deletedById(userEntity.getDeletedById())
                .isBlocked(userEntity.isBlocked())
                .role(userEntity.getRole())
                .build();
    }
}
