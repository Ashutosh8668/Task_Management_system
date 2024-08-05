package com.example.taskmanagement.TaskManager.Controller;

import com.example.taskmanagement.TaskManager.Entity.Task;
import com.example.taskmanagement.TaskManager.Services.TaskService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private static final Logger logger = LoggerFactory.getLogger(TaskController.class);

//    @Autowired
//    private TaskService taskService;
    private final TaskService taskService ;


    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/completed")
    public String getCompletedTasks(Model model) {
        List<Task> completedTasks = taskService.getCompletedTasks();
        model.addAttribute("completedTasks", completedTasks);
        return "completed";
    }
    @GetMapping
    public String home(Model model) {
        List<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        return "home";
    }



    @GetMapping("/new")
    public String newTask(Model model) {
        model.addAttribute("task", new Task());
        return "newTask";
    }

    @PostMapping
    public String createTask(@ModelAttribute Task task) {
        taskService.createTask(task);
        return "redirect:/tasks";
    }

//    @GetMapping("/edit/{id}")
//    public String editTask(@PathVariable Long id, Model model) {
//        Task task = taskService.getTaskById(id);
//        model.addAttribute("task", task);
//        return "editTask";
//    }
//
//    @PostMapping("/edit/{id}")
//    public String updateTask(@PathVariable Long id, @ModelAttribute Task taskDetails) {
////        Task task = taskService.updateTask(id, taskDetails);
//        taskService.updateTask(id,taskDetails);
//        return "redirect:/tasks";
//    }

    @GetMapping("/edit/{id}")
    public String editTask(@PathVariable Long id, Model model) {
        Task task = taskService.getTaskById(id);
        model.addAttribute("task", task);
        return "editTask";
    }

    @PostMapping("/edit/{id}")
    public String updateTask(@PathVariable Long id, @ModelAttribute Task taskDetails) {
        taskService.updateTask(id, taskDetails);
        return "redirect:/tasks";
    }

    @PostMapping("/{id}/delete")
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteTask(id);
        return "redirect:/tasks";
    }

    @PostMapping("/{taskId}/assign")
    public String assignUserToTask(@PathVariable Long taskId, @RequestParam Long userId, Model model) {
        Task updatedTask = taskService.assignTask(taskId, userId);

        // Log the timestamp of assignment
        LocalDateTime now = LocalDateTime.now();
        logger.info("Task {} assigned to user {} at {}", taskId, userId, now);

        model.addAttribute("task", updatedTask);
        return "redirect:/tasks";
    }
}
