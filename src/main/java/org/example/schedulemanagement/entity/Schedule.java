package org.example.schedulemanagement.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "schedule")
public class Schedule extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;
    @Column(columnDefinition = "longtext")
    private String contents;


    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(mappedBy = "schedule" , cascade = CascadeType.REMOVE)
    private List<Comment> commentList = new ArrayList<>();


    public Schedule(){};

    public Schedule(String title, String contents, User user){
        this.title = title;
        this.contents = contents;
        this.user = user;
    }
    public void updateSchedule(String updateScheduleTitle, String updateScheduleContents){
        this.title = updateScheduleTitle;
        this.contents = updateScheduleContents;

    }

}
