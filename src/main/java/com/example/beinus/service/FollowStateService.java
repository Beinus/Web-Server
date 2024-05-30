package com.example.beinus.service;

import com.example.beinus.model.FollowState;
import com.example.beinus.repository.FollowStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FollowStateService {

    @Autowired
    private FollowStateRepository repository;

    public List<FollowState> getByUserId(String userId) {
        return repository.findByUser1OrUser2(userId, userId);
    }

    public FollowState save(FollowState followState) {
        return repository.save(followState);
    }

    public void delete(FollowState followState) {
        repository.delete(followState);
    }
}
