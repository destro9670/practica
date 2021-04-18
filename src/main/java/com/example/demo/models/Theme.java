package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "themes")
@Data
@NoArgsConstructor
public class Theme {
    @Id
    @SequenceGenerator(name = "theme_seq", sequenceName = "themes_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "theme_seq")
    private long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "id")
    private List<Question> questionrs;
}
