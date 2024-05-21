package com.example.beinus.story;

import com.example.beinus.likedStories.LikedStories;
import com.example.beinus.likedStories.LikedStoriesRepository;
import com.example.beinus.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private LikedStoriesRepository likedStoriesRepository;

    public List<Story> getAllStories() {
        return new ArrayList<>(storyRepository.findAll());
    }

    public Story getStory(Long id) {
        Optional<Story> optionalStory = storyRepository.findById(id);
        return optionalStory.orElse(null);
    }

    public Story saveStory(Story story) {
        return storyRepository.save(story);
    }

    public void deleteStory(Story story) {
        storyRepository.delete(story);
    }

    // Get all the stories that the user saved as Like.
    public List<Story> getLikedStories(String userId) {
        // Get the list of storyIds that the user has liked
        List<Long> likedStoryIds = likedStoriesRepository.findAll().stream()
                .filter(likedStory -> likedStory.getUserId().equals(userId))
                .map(LikedStories::getStoryId)
                .toList();

        // Use the StoryRepository to find the Story objects with these IDs
        List<Story> likedStories = new ArrayList<>();
        for (Long storyId : likedStoryIds) {
            Optional<Story> story = storyRepository.findById(storyId);
            story.ifPresent(likedStories::add);
        }
        return likedStories;
    }
}
