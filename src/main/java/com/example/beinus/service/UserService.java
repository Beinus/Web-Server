package com.example.beinus.service;

import com.example.beinus.model.Story;
import com.example.beinus.model.User;
import com.example.beinus.repository.StoryRepository;
import com.example.beinus.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    public User updateUser(String userId, User user) {
        Optional<User> savedUserOptional = userRepository.findById(userId);

        if (savedUserOptional.isPresent()) {
            User savedUser = savedUserOptional.get();

            // Update the fields of the savedUser with the new user data
            if (user.getUsername() != null) {
                savedUser.setUserName(user.getUsername());
            }
            if (user.getUserPassword() != null) {
                savedUser.setUserPassword(user.getUserPassword());
            }
            if (user.getUserImage() != null) {
                savedUser.setUserImage(user.getUserImage());
            }
            if (user.getUserColor() != null) {
                savedUser.setUserColor(user.getUserColor());
            }
            if (user.getUserIntro() != null) {
                savedUser.setUserIntro(user.getUserIntro());
            }

            savedUser.getStories().forEach(story -> {
                story.setUserName(user.getUsername());
                storyRepository.save(story);
            });

            return userRepository.save(savedUser);
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
