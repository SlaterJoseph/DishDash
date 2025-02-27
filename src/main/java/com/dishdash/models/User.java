package com.dishdash.models;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotNull;



@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(unique = true)
    private String username;

    @NotNull
    @Column(unique = true)
    private String email;

    @NotNull
    private String password;


    private String firstName;
    private String lastName;

    public User(String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    @Override
    public String toString() {
        return String.format(
                "Customer [id=%d, username='%s', email='%s'"
            ,id, username, email);
    }
}
