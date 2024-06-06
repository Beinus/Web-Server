package com.example.beinus.model;

import jakarta.persistence.*;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
