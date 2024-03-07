package org.example;


import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Task {
    private static int nextId = 1; 

    private int id;

    private String title;
    private String description;
    private LocalDate dueDate;
    private TaskPriority priority;

    public Task() {

    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    private List<Task> tasks = new ArrayList<>();

    public void addTask(Task task) {
        tasks.add(task);
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks);
    }

    public Task getTaskById(int id) {
        for (Task task : tasks) {
            if (task.getId() == id) {
                return task;
            }
        }
        return null;
    }

    public void updateTask(Task updatedTask) {
        Task taskToBeUpdated = getTaskById(updatedTask.getId());
        if (taskToBeUpdated != null) {
            tasks.remove(taskToBeUpdated);
            tasks.add(updatedTask);
        }
    }

    public void deleteTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                break;
            }
        }
    }

    public List<Task> getTasksByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }


}





