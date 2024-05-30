package com.example.beinus.controller;

import com.example.beinus.model.FollowState;
import com.example.beinus.service.FollowStateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:5173")
@RequestMapping("/api/follows")
public class FollowStateController {

    @Autowired
    private FollowStateService service;

    @GetMapping("/get/{userId}")
    public List<FollowState> getByUserId(@PathVariable String userId) {
        return service.getByUserId(userId);
    }

    @PostMapping("/save")
    public FollowState save(@RequestBody FollowState followState) {
        return service.save(followState);
    }

    @DeleteMapping("/delete")
    public void delete(@RequestBody FollowState followState) {
        service.delete(followState);
    }
}
