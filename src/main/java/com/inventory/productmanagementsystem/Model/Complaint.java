package com.inventory.productmanagementsystem.Model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity(name = "complaint")
@Table(name = "complaint")
public class Complaint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "content")
    private String content;
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    public Complaint() {
    }

    public Complaint(LocalDateTime createdAt, String content) {
        this.createdAt = createdAt;
        this.content = content;
    }

    public Complaint(LocalDateTime createdAt, String content, User user) {
        this.createdAt = createdAt;
        this.content = content;
        this.user = user;
    }

    public Complaint(Long id, LocalDateTime createdAt, String content, User user) {
        this.id = id;
        this.createdAt = createdAt;
        this.content = content;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Complaint {" +
                "id = " + id +
                ", createdAt = " + createdAt +
                ", content = '" + content + '\'' +
                ", user = " + user +
                '}';
    }
}
