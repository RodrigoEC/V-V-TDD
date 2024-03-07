package org.example;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Task {


    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;

    public enum TaskPriority {
        HIGH, MEDIUM, LOW
    }

    public Task(String title, String description, LocalDate dueDate, TaskPriority priority) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public TaskPriority getPriority() {
        return priority;
    }

    public Task() {

    }
    
        private List<Task> tasks = new ArrayList<>();

        public void addTask(Task task) {
            tasks.add(task);
        }

        public List<Task> getTasks() {
            return new ArrayList<>(tasks);
        }
    }



