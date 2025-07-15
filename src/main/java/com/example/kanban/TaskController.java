package com.example.kanban;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/tasks")
@CrossOrigin(origins = "*")
public class TaskController {

    private final Map<Long, Task> taskStore = new HashMap<>();
    private final AtomicLong idCounter = new AtomicLong();

    @GetMapping
    public Collection<Task> getAllTasks() {
        return taskStore.values();
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        long id = idCounter.incrementAndGet();
        task.setId(id);
        task.setStatus("todo");
        taskStore.put(id, task);
        return task;
    }

    @PutMapping("/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskStore.get(id);
        if (task != null) {
            task.setTitle(updatedTask.getTitle());
            task.setStatus(updatedTask.getStatus());
        }
        return task;
    }
}
