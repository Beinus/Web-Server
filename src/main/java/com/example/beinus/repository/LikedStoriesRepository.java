package com.example.beinus.repository;

import com.example.beinus.model.LikedStories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LikedStoriesRepository extends JpaRepository<LikedStories, Long> {
    LikedStories findByUserIdAndStoryId(String userId, Long storyId);
}
