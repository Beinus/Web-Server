package com.example.beinus.service;

import com.example.beinus.model.LikedStories;
import com.example.beinus.repository.LikedStoriesRepository;
import com.example.beinus.model.Story;
import com.example.beinus.repository.StoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LikedStoriesService {

    @Autowired
    private LikedStoriesRepository likedStoriesRepository;

    @Autowired
    private StoryRepository storyRepository;

    public List<LikedStories> getLikedStories() {
        return likedStoriesRepository.findAll();
    }

    // Save a story to Like and add 1 to the value of the like of the story.
    public LikedStories addLike(LikedStories likedStories) {
        Story story = storyRepository.findById(likedStories.getStoryId()).get();
        story.setLikes(story.getLikes() + 1);

        return likedStoriesRepository.save(likedStories);
    }

    // Remove a story from Like and subtract 1 to the value of the like of the story.
    public void removeLike(String userId, Long storyId) {
        // Find the story with the matching userId and storyId
        LikedStories likedStory = likedStoriesRepository.findByUserIdAndStoryId(userId, storyId);
        if (likedStory != null) {
            // If the story exists, delete it
            likedStoriesRepository.deleteById(likedStory.getId());
            Story story = storyRepository.findById(storyId).get();
            story.setLikes(story.getLikes() - 1);
            storyRepository.save(story);
        } else {
            // If the story doesn't exist, handle the error
            System.out.println("Match not found");
        }
    }
}
