package com.example.beinus.likedStories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/likes")
public class LikedStoriesController {

    @Autowired
    private LikedStoriesService service;

    @GetMapping("/get-all")
    public List<LikedStories> getLikedStories() {
        return service.getLikedStories();
    }

    @PostMapping("/add")
    public LikedStories addLike(@RequestBody LikedStories likedStories) {
        return service.addLike(likedStories);
    }

    @DeleteMapping("/remove")
    public void removeLike(@RequestBody LikedStories likedStories) {
        service.removeLike(likedStories.getUserId(), likedStories.getStoryId());
    }
}
