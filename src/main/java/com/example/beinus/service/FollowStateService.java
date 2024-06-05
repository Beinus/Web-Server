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
        Optional<FollowState> existingFollowState = repository.findByUser1AndUser2(followState.getUser1(), followState.getUser2());
        if (existingFollowState.isPresent()) {
            FollowState stateToUpdate = existingFollowState.get();
            stateToUpdate.setState(followState.getState());
            return repository.save(stateToUpdate);
        } else {
            return repository.save(followState);
        }
    }

    public void delete(String user1, String user2) {
        Optional<FollowState> optionalFollowState = repository.findByUser1AndUser2(user1, user2);
        optionalFollowState.ifPresent(repository::delete);
    }
}
