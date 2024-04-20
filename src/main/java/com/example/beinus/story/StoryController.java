package com.example.beinus.story;

import com.example.beinus.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5174")
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

    @PostMapping("/save")
    public Story saveStory(@RequestBody Story story) {
        return storyService.saveStory(story);
    }

    @PostMapping("/{userId}/add")
    public Story addStory(@PathVariable String userId ,@RequestBody Story story) {
        return userService.addStory(story, userId);
    }
}
