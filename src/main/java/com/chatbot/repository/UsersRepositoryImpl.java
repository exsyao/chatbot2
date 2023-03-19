package com.chatbot.repository;

import io.quarkus.elytron.security.common.BcryptUtil;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.security.jpa.Password;
import io.quarkus.security.jpa.Roles;
import io.quarkus.security.jpa.UserDefinition;
import io.quarkus.security.jpa.Username;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
@UserDefinition
public class UsersRepositoryImpl extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;
    @Username
    public String username;
    @Password
    public String password;
    @Roles
    public String role;

    /**
     * Adds a new user to the database
     * @param username the username
     * @param password the unencrypted password (it will be encrypted with bcrypt)
     * @param role the comma-separated roles
     */
    public static void add(String username, String password, String role) {
        UsersRepositoryImpl usersRepositoryImpl = new UsersRepositoryImpl();
        usersRepositoryImpl.username = username;
        usersRepositoryImpl.password = BcryptUtil.bcryptHash(password);
        usersRepositoryImpl.role = role;
        usersRepositoryImpl.persist();
    }
}