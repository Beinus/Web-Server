package com.example.beinus.story;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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

    private String userId;
}