package com.example.beinus.user;

import com.example.beinus.story.Story;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
