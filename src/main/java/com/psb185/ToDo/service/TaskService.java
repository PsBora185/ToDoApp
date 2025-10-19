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

    public Task createTask(String title) {
        Task task = new Task();
        task.setTitle(title);

        return taskRepository.save(task);
    }

    public Task updateTitle(Long id, String title) {

        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setTitle(title);
            return taskRepository.save(task);
        }

        return null;
    }

    public Task completeTask(Long id) {
        Task task = taskRepository.findById(id).orElse(null);

        if (task != null) {
            task.setComplete(true);
            return taskRepository.save(task);
        }

        return null;
    }

    public boolean deleteTask(Long id) {
        if (!taskRepository.existsById(id)) return false;

        taskRepository.deleteById(id);
        return true;
    }
}
