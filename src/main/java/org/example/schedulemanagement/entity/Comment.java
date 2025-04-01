package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Table(name = "comment")
@Entity
public class Comment extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "schedule_id")
    @Setter
    @ManyToOne
    private Schedule schedule;

    @JoinColumn(name = "user_id")
    @Setter
    @ManyToOne
    private User user;

    @Column(nullable = false, columnDefinition = "longtext")
    private String contents;

    public Comment(){}

    public Comment(String contents){
        this.contents = contents;
    }
    public void updateContents(String contents){
        this.contents = contents;
    }
}
