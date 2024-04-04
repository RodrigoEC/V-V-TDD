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
        HIGH, MEDIUM, URGENT, LOW
    }

    public Task(String title, String description, LocalDate dueDate, TaskPriority priority) {
        this.id = nextId++;
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
        if (task == null) {
            throw new NullPointerException("Task cannot be null");
        }
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


    public void deleteTask(int id) {
        boolean found = false; // Flag para indicar se a tarefa foi encontrada
        Iterator<Task> iterator = tasks.iterator();

        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                found = true; // Marca que a tarefa foi encontrada e removida
                break; // Sai do loop pois a tarefa já foi removida
            }
        }

        // Se após iterar por todas as tarefas a flag 'found' ainda for false,
        // significa que não existe uma tarefa com o ID especificado.
        if (!found) {
            throw new IllegalArgumentException("Task with ID " + id + " does not exist");
        }
    }

    public List<Task> getTasksOrderedByDueDateAndPriority() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate)
                        .thenComparingInt((Task t) -> t.getPriority().ordinal()))
                .collect(Collectors.toList());
    }

    public List<Task> getTasksByPriority(TaskPriority priority) {
        return tasks.stream()
                .filter(task -> task.getPriority() == priority)
                .collect(Collectors.toList());
    }



    // Métodos setter para atualização de tarefas.
    public void setTitle(String title) {
        if (title == null || title.isEmpty()) {
            throw new IllegalArgumentException("O título não pode ser vazio.");
        }
        this.title = title;
    }

    public void setDescription(String description) {
        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        if (dueDate == null || dueDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("A data de vencimento deve ser hoje ou futura.");
        }
        this.dueDate = dueDate;
    }


    public void setPriority(TaskPriority priority) {
        if (priority == null) {
            throw new IllegalArgumentException("A prioridade deve ser especificada.");
        }
        this.priority = priority;
    }

    public void updateTask(Task updatedTask) {
        if (updatedTask == null) {
            throw new NullPointerException("Updated task cannot be null");
        }

        for (Task task : tasks) {
            if (task.getId() == updatedTask.getId()) {
                // Aqui você atualiza todos os campos da tarefa com os valores de updatedTask.
                task.setTitle(updatedTask.getTitle());
                task.setDescription(updatedTask.getDescription());
                task.setDueDate(updatedTask.getDueDate());
                task.setPriority(updatedTask.getPriority());
                return; // Encerra o método após a atualização ser concluída.
            }
        }

        throw new IllegalArgumentException("Task with ID " + updatedTask.getId() + " does not exist");
    }


}







