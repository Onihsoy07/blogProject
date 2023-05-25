package com.example.blog.entity;

import javax.persistence.*;
import javax.validation.constraints.Size;

import lombok.*;

import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "reply", schema = "blog", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Reply extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 200)
    @Size(min = 3)
    private String content;

    @Column(nullable = false)
    private int depth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "replyId")
    private Reply reply;

    @OneToMany(mappedBy = "reply", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Reply> replySet;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "boardId")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usersId")
    private Users users;

}
