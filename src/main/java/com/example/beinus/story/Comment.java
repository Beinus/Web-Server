package com.example.beinus.story;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
public class Comment {
    @Id
    private Long id;
    private String comment;
    private String replies;

}
