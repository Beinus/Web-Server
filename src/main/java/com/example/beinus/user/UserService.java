package com.example.beinus.user;

import com.example.beinus.story.Story;
import com.example.beinus.story.StoryRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private StoryRepository storyRepository;

    public List<User> getAllUsers() {
        return new ArrayList<>(userRepository.findAll());
    }

    public User getUser(String userId) {
        // Option 1: Using findById (assuming User has an ID field)
        Optional<User> userOptional = userRepository.findById(userId);
        return userOptional.orElse(null); // Return null if user not found
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(User user) {
        Optional<User> savedUserOptional = userRepository.findById(user.getUserId());

        if (savedUserOptional.isPresent()) {
            User savedUser = savedUserOptional.get();

            savedUser.getStories().forEach(story -> {
                story.setUserName(user.getUsername());
                storyRepository.save(story);
            });
            return userRepository.save(user);
        } else {
            return null;
        }
    }

    public void deleteUser(User user) {
        userRepository.delete(user);
        System.out.println("{user} deleted");
    }

    @Transactional
    public Story addStory(Story story, String userId) {

        User user = userRepository.findById(userId).get();
        story.setUserId(userId); // Associate the story with the user
        user.getStories().add(story); // Add the story to the user's list

        return storyRepository.save(story); // Persist the story
    }
}
