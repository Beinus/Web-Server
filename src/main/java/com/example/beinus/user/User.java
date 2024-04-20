package com.example.beinus.user;

import com.example.beinus.story.Story;
import jakarta.persistence.*;
import jakarta.transaction.Transactional;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
@Entity
public class User {
    @Id
    @Column(name = "user_id")
    private String userId;
    private String userName;
    private String userPassword;
    private String userImage;
    private String userColor;
    private String userIntro;

    @OneToMany(mappedBy = "userId")
    private List<Story> stories;
}
