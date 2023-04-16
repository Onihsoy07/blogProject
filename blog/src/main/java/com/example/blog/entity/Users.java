package com.example.blog.entity;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
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
//@Data
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

    @Column(nullable = false, length = 30, unique = true)
    @NotBlank(message="PASSWORD_IS_MANDATORY")
    @Size(max = 30)
    private String username;

    @Column(nullable = false, length = 100)
    @NotBlank(message="PASSWORD_IS_MANDATORY")
    @Size(min = 8, max = 100)
    private String password;

    @Column(nullable = false, length = 50, unique = true)
    @Email(message = "NOT_VALID_EMAIL")
    @Size(min = 3, max = 50)
    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
    private List<Reply> replyList = new ArrayList<>();

    @Override
    public String toString() {
        return String.format("id : %d, username : %s, password : %s, email : %s", id, username, password, email);
    }

}
