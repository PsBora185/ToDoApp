package com.psb185.ToDo.controller;


import com.psb185.ToDo.entity.Task;
import com.psb185.ToDo.service.TaskService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @GetMapping
    public String getTasks(Model model){

        List<Task> tasks = taskService.getTasks();

        model.addAttribute("tasks", tasks);
        return "tasks";
    }

    @PostMapping("/add")
    public String createTask(@RequestParam("title") String title, @RequestParam("details") String details){

        taskService.createTask(title,details);

        return "redirect:/tasks";
    }

    @PostMapping("/update/{id}")
    public String updateTask(@PathVariable("id") Long id, @RequestParam("title") String title, @RequestParam("details") String details){

        taskService.updateTitle(id, title, details);

        return "redirect:/tasks";
    }

    @PostMapping("/complete/{id}")
    public String completeTask(@PathVariable("id") Long id){

        taskService.completeTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/delete/{id}")
    public String deleteTask(@PathVariable("id") Long id){

        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @GetMapping("/search")
    public String searchTasks(@RequestParam("title") String title, Model model){
        List<Task> tasks = taskService.searchTasks(title);

        model.addAttribute("tasks", tasks);
        return "tasks";
    }
}
