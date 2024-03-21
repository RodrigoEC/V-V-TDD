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
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task task = iterator.next();
            if (task.getId() == id) {
                iterator.remove();
                break;
            }
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
        for (int i = 0; i < tasks.size(); i++) {
            if (tasks.get(i).getId() == updatedTask.getId()) {
                // Encontra a tarefa com o mesmo ID e atualiza-a com os novos valores.
                Task originalTask = tasks.get(i);

                // Assume-se que apenas a data de vencimento pode ser atualizada neste método,
                // mas você pode expandir para atualizar outros campos conforme necessário.
                originalTask.setDueDate(updatedTask.getDueDate());

                // Não é necessário adicionar a tarefa atualizada de volta à lista,
                // pois a modificação é feita diretamente no objeto encontrado na lista.
                break;
            }
        }
    }

}







