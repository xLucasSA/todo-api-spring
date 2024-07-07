package com.lucasabreu.simpletodo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.lucasabreu.simpletodo.models.Task;

import com.lucasabreu.simpletodo.services.UserService;

import com.lucasabreu.simpletodo.repositories.TaskRepository;

@Service
public class TaskService {
    
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id) {
        Optional<Task> task = this.taskRepository.findById(id);

        return task.orElseThrow(() -> new RuntimeException(
            "Tarefa não encontrada! Id: " + id + ", Tipo: " + Task.class.getName()
        ));
    }

    @Transactional
    public Task create(Task obj) {
        obj.setId(null);
        this.userService.findById(obj.getId());
        obj = this.taskRepository.save(obj);

        return obj;
    }

    @Transactional
    public Task update(Task obj) {
        Task newObj = findById(obj.getId());
        newObj.setDescription(obj.getDescription());
        newObj = this.taskRepository.save(newObj);

        return newObj;
    }

    public void delete(Long id) {
        findById(id);
        this.taskRepository.deleteById(id);
    }
}
