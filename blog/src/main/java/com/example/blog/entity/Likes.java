package com.example.blog.entity;

import lombok.*;
import org.springframework.cache.annotation.EnableCaching;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@NoArgsConstructor//(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Data
@Builder
@Table(name = "likes", schema = "blog", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "users_id")
    private Users users;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


}
