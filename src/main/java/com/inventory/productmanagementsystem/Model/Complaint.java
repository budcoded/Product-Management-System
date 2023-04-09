package com.inventory.productmanagementsystem.Model;

import jakarta.persistence.*;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;

import java.time.LocalDate;
import java.time.LocalDateTime;
@Entity(name = "complaint")
@Table(name = "complaint")
public class Complaint {
    @Id
    @SequenceGenerator(name = "complaint_sequence", sequenceName = "complaint_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "complaint_sequence")
    @Column(name = "id")
    private Long id;
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    @Column(name = "content")
    private String content;
    private User customer;

    public Complaint() {
    }

    public Complaint(LocalDateTime createdAt, String content, User customer) {
        this.createdAt = createdAt;
        this.content = content;
        this.customer = customer;
    }

    public Complaint(Long id, LocalDateTime createdAt, String content, User customer) {
        this.id = id;
        this.createdAt = createdAt;
        this.content = content;
        this.customer = customer;
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

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Complaint{" +
                "id=" + id +
                ", createdAt=" + createdAt +
                ", content='" + content + '\'' +
                ", customer=" + customer +
                '}';
    }
}
