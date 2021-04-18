package com.example.demo.models;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "marks")
@Data
@NoArgsConstructor
public class Mark {

    @Id
    @SequenceGenerator(name = "mark_seq", sequenceName = "marks_id_seq", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO,generator = "mark_seq")
    private long id;

    @Column(name = "count")
    private int count;

    @CreatedDate
    @Column(name = "receipt")
    private Date receipt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
