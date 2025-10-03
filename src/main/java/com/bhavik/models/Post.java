package com.bhavik.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "Post")
public class Post {
   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   private Integer id ;

   private String caption;

   private String image;

   private String video;

   @ManyToOne
   private User user;

   @ManyToMany
   private List<User> liked = new ArrayList<>();

   private LocalDateTime timeAtCreation;

   public Integer getId() {
      return id;
   }

   public void setId(Integer id) {
      this.id = id;
   }

   public String getCaption() {
      return caption;
   }

   public void setCaption(String caption) {
      this.caption = caption;
   }

   public String getImage() {
      return image;
   }

   public void setImage(String image) {
      this.image = image;
   }

   public String getVideo() {
      return video;
   }

   public void setVideo(String video) {
      this.video = video;
   }

   public User getUser() {
      return user;
   }

   public void setUser(User user) {
      this.user = user;
   }

   public LocalDateTime getTimeAtCreation() {
      return timeAtCreation;
   }

   public void setTimeAtCreation(LocalDateTime timeAtCreation) {
      this.timeAtCreation = timeAtCreation;
   }

   public List<User> getLiked() {
      return liked;
   }

   public void setLiked(List<User> liked) {
      this.liked = liked;
   }
}


