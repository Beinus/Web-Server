package com.example.beinus.story;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class Story {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String userName;
    @Column(columnDefinition = "TEXT") // Use TEXT type
    private String title;
    @Column(columnDefinition = "TEXT") // Use TEXT type
    private String content;
    private Long likes;

}
