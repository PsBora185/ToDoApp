package com.psb185.ToDo.service;

import com.psb185.ToDo.entity.Task;
import com.psb185.ToDo.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;

    public List<Task> getTasks() {
        return taskRepository.findAll();
    }

    public void createTask(String title, String details) {
        Task task = new Task();
        task.setTitle(title);
        task.setDetails(details);

        taskRepository.save(task);
    }

    public void updateTitle(Long id, String title, String details) {

        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setTitle(title);
            task.setDetails(details);
            taskRepository.save(task);
        }
    }

    public void completeTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setCompleted(true);
            taskRepository.save(task);
        }
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) return false;

        taskRepository.deleteById(id);
        return true;
    }

    public List<Task> searchTasks(String title){

        return taskRepository.findByTitleContainingIgnoreCase(title);
    }
}
