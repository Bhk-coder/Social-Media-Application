package com.bhavik.repository;

import com.bhavik.models.Post;
import com.bhavik.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Integer> {
    public User findByEmail(String email);

    @Query(value = "SELECT * FROM users u " +
            "WHERE u.first_name LIKE CONCAT('%', :query, '%') " +
            "   OR u.last_name LIKE CONCAT('%', :query, '%') " +
            "   OR u.email LIKE CONCAT('%', :query, '%')",
            nativeQuery = true)
    List<User> searchUser(@Param("query") String query);


    List<User> findAllBySavedPostsContaining(Post post);
}
