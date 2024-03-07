package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

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
}