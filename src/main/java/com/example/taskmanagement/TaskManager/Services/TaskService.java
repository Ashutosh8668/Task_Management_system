package com.example.taskmanagement.TaskManager.Services;

import com.example.taskmanagement.TaskManager.Entity.Task;
import com.example.taskmanagement.TaskManager.Entity.User;
import com.example.taskmanagement.TaskManager.Repository.TaskRepository;
import com.example.taskmanagement.TaskManager.Repository.UserRepository;
import com.example.taskmanagement.TaskManager.ResourceNotFound.ResourceNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;


    public List<Task> getCompletedTasks() {
        return taskRepository.findByStatus("completed");
}
    @Cacheable("tasks")
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    @CacheEvict(value = "tasks", allEntries = true)
    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    @Cacheable(value = "tasks" , key = "#id")
    public Task getTaskById(Long id) {
        return taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
    }

    @CacheEvict(value = "tasks", key = "#id")
    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));

        task.setTitle(taskDetails.getTitle());
        task.setDescription(taskDetails.getDescription());
        task.setPriority(taskDetails.getPriority());
        task.setDueDate(taskDetails.getDueDate());
        task.setStatus(taskDetails.getStatus());
        task.setAssignedUser(taskDetails.getAssignedUser());

        return taskRepository.save(task);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public void deleteTask(Long id) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        taskRepository.delete(task);
    }

    @CacheEvict(value = "tasks", key = "#id")
    public Task assignTask(Long id, Long userId) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task not found with id " + id));
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id " + userId));

        task.setAssignedUser(user);
        return taskRepository.save(task);
    }

}
