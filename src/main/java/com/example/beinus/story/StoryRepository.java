package com.example.beinus.story;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoryRepository extends JpaRepository<Story, Long> {
    /*
    You can add more specific method functions here.

    RULES
    1. The method name must start with find, read, get, or query.
    2. The next part of the method name is By which indicates that it’s a condition.
    3. After By, you specify the property you want to use as a condition. In this case, it’s Status.
    */
}
