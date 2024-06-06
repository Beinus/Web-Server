package com.example.beinus.controller;

import com.example.beinus.model.Story;
import com.example.beinus.service.StoryService;
import com.example.beinus.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/api/stories")
public class StoryController {

    @Autowired
    private StoryService storyService;

    @Autowired
    private UserService userService;

    @GetMapping("/get-all")
    public List<Story> getAllStories() {
        return storyService.getAllStories();
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Story> getStory(@PathVariable Long id) {
        Story story = storyService.getStory(id);
        if (story != null) {
            return new ResponseEntity<>(story, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/save")
    public Story saveStory(@RequestBody Story story) {
        return storyService.saveStory(story);
    }

    // Maybe not needed
    @PostMapping("/{userId}/add")
    public Story addStory(@PathVariable String userId ,@RequestBody Story story) {
        return userService.addStory(story, userId);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteStory(@PathVariable Long id) {
        Story story = storyService.getStory(id);
        storyService.deleteStory(story);
    }

    @GetMapping("/{userId}/likedStories")
    public List<Story> getLikedStories(@PathVariable String userId) {
        return storyService.getLikedStories(userId);
    }
}
