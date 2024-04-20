package com.example.beinus.story;

import com.example.beinus.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository storyRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Story> getAllStories() {
        return new ArrayList<>(storyRepository.findAll());
    }

    public Story saveStory(Story story) {
        return storyRepository.save(story);
    }
}
