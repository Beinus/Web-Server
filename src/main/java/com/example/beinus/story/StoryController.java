package com.example.beinus.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping(path = "/api/stories")
public class StoryController {

    @Autowired
    private StoryService service;

    @GetMapping("/get-all")
    public List<Story> getAllStories() {
        return service.getAllStories();
    }

    @PostMapping("/save")
    public Story saveStory(@RequestBody Story story) {
        return service.saveStory(story);
    }
}
