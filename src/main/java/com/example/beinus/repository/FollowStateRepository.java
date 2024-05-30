package com.example.beinus.repository;

import com.example.beinus.model.FollowState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowStateRepository extends JpaRepository<FollowState, Long> {
    List<FollowState> findByUser1OrUser2(String user1, String user2);
}
