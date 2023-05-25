package com.example.blog.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "users", schema = "blog", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Users extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 70, unique = true)
    @NotBlank(message="USERNAME_IS_MANDATORY")
    @Size(max = 70)
    private String username;

    @Column(nullable = false, length = 100)
    @NotBlank(message="PASSWORD_IS_MANDATORY")
    @Size(min = 8, max = 100)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "NOT_VALID_EMAIL")
    @Size(min = 3, max = 50)
    private String email;

    @Column(nullable = true, unique = false)
    private String oauth;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    @JsonIgnoreProperties({"users", "reply"})
    private List<Board> boardList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonIgnoreProperties({"users", "board"})
    private List<Reply> replyList;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL, fetch = FetchType.LAZY, orphanRemoval = true)
    private List<Likes> likesList;

    @Override
    public String toString() {
        return String.format("id : %d, username : %s, password : %s, email : %s", id, username, password, email);
    }

}
