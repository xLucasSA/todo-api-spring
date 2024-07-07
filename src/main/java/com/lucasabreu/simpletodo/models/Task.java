package com.lucasabreu.simpletodo.models;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = Task.TABLE_NAME)
public class Task {
    public static final String TABLE_NAME = "Tasks";

    public interface CreatePassword {}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    private Long id;

    @Column(name = "description", nullable = false, length = 200)
    @NotNull(groups = CreatePassword.class)
    @NotEmpty(groups = CreatePassword.class)
    @Size(min = 1, max = 200)
    private String description;

    @ManyToOne
    @JoinColumn(name = "user_id", updatable = false, nullable = false)
    private User user;

    public Task(){
    }

    public Task(Long id, String description, User user) {
        this.id = id;
        this.description = description;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }

        if (obj == null) {
            return false;
        }
        
        if (!(obj instanceof Task)) {
            return false;
        }

        Task other = (Task) obj;

        if (this.id == null) {
            if (other.id != null) {
                return false;
            }
            else if (!this.id.equals(other.id)){
                return false;
            }
        }

        return Objects.equals(this.id, other.id) && Objects.equals(this.description, other.description) && Objects.equals(this.user, other.user);
    }   

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (this.id == null ? 0 : this.id.hashCode());

        return result;
    }
}
