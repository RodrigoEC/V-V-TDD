package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    private Task taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new Task();
    }

    @Test
    void testAddTask() {
        Task task = new Task("Estudar JUnit", "Estudar JUnit para o projeto", LocalDate.now().plusDays(1), Task.TaskPriority.MEDIUM);
        taskManager.addTask(task);
        assertEquals(1, taskManager.getTasks().size(), "Deve haver uma tarefa após adicionar.");
    }

    @Test
    void testUpdateTask() {
        Task task = new Task("Estudar JUnit", "Descrição inicial", LocalDate.now().plusDays(1), Task.TaskPriority.MEDIUM);
        taskManager.addTask(task);
        int taskId = task.getId();

        Task updatedTask = new Task("Estudar JUnit Atualizado", "Descrição atualizada", LocalDate.now().plusDays(2), Task.TaskPriority.HIGH);
        updatedTask.setId(taskId);
        taskManager.updateTask(updatedTask);

        // Busca a tarefa atualizada para verificação
        Task result = taskManager.getTaskById(taskId);
        assertEquals("Estudar JUnit Atualizado", result.getTitle());
        assertEquals("Descrição atualizada", result.getDescription());
        assertEquals(LocalDate.now().plusDays(2), result.getDueDate());
        assertEquals(Task.TaskPriority.HIGH, result.getPriority());
    }

    @Test
    void testDeleteTask() {
        // Adiciona uma tarefa
        Task task = new Task("Ler livro", "Ler um livro sobre TDD", LocalDate.now().plusDays(3), Task.TaskPriority.LOW);
        taskManager.addTask(task);
        int taskId = task.getId();

        // Verifica se a tarefa foi adicionada
        assertNotNull(taskManager.getTaskById(taskId));

        // Exclui a tarefa
        taskManager.deleteTask(taskId);

        // Verifica se a tarefa foi excluída
        assertNull(taskManager.getTaskById(taskId), "A tarefa deve ser null após ser excluída.");
    }

    @Test
    void testGetTasksByPriority() {
        Task task1 = new Task("Task Alta", "Desc Alta", LocalDate.now().plusDays(1), Task.TaskPriority.HIGH);
        Task task2 = new Task("Task Média", "Desc Média", LocalDate.now().plusDays(2), Task.TaskPriority.MEDIUM);
        Task task3 = new Task("Task Baixa", "Desc Baixa", LocalDate.now().plusDays(3), Task.TaskPriority.LOW);
        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        List<Task> highPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.HIGH);
        List<Task> mediumPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.MEDIUM);
        List<Task> lowPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.LOW);

        assertEquals(1, highPriorityTasks.size(), "Deve haver uma tarefa de alta prioridade.");
        assertEquals(task1, highPriorityTasks.get(0), "A tarefa de alta prioridade deve ser 'Task Alta'.");

        assertEquals(1, mediumPriorityTasks.size(), "Deve haver uma tarefa de média prioridade.");
        assertEquals(task2, mediumPriorityTasks.get(0), "A tarefa de média prioridade deve ser 'Task Média'.");

        assertEquals(1, lowPriorityTasks.size(), "Deve haver uma tarefa de baixa prioridade.");
        assertEquals(task3, lowPriorityTasks.get(0), "A tarefa de baixa prioridade deve ser 'Task Baixa'.");
    }

}