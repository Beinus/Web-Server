package com.example.beinus.story;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StoryService {

    @Autowired
    private StoryRepository repository;

    public List<Story> getAllStories() {
        List<Story> stories = new ArrayList<>();
        Streamable.of(repository.findAll())
                .forEach(stories::add);
        return stories;
    }

    public Story saveStory(Story story) {
        return repository.save(story);
    }
}
