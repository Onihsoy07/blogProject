package com.example.blog.entity;


import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "board", schema = "blog", uniqueConstraints = {@UniqueConstraint(columnNames = "id")})
public class Board extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, unique = true)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Lob
    private String content;


    private int count;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "usersId")
    private Users users;

    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Reply> replyList = new ArrayList<>();

}
