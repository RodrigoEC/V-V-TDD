package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FunctionalTests {

    private Task taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new Task();
    }

    @Test()
    public void testCreateTaskWithInvalidDescription() {
        Task newTask = new Task("Título Válido", "", LocalDate.of(2024, 4, 1), Task.TaskPriority.MEDIUM);
        taskManager.addTask(newTask);
    }

    @Test()
    public void testCreateTaskWithInvalidPriority() {
        Task newTask = new Task("Título Válido", "Descrição válida", LocalDate.of(2024, 4, 1), null); // null para simular prioridade inválida
        taskManager.addTask(newTask);
    }

    @Test
    public void testUpdateDescriptionOnly() {
        Task taskToUpdate = new Task("Tarefa", "Descrição Antiga", LocalDate.now(), Task.TaskPriority.MEDIUM);
        taskToUpdate.setId(1);
        taskManager.addTask(taskToUpdate);

        Task updatedTask = new Task("Tarefa", "Descrição Nova", LocalDate.now(), Task.TaskPriority.MEDIUM);
        updatedTask.setId(1);
        taskManager.updateTask(updatedTask);

        Task retrievedTask = taskManager.getTaskById(1);
        assertEquals("Descrição Nova", retrievedTask.getDescription());
    }

    @Test
    public void testUpdateDateOnly() {
        Task taskToUpdate = new Task("Tarefa", "Descrição", LocalDate.of(2024, 1, 1), Task.TaskPriority.MEDIUM);
        taskToUpdate.setId(1);
        taskManager.addTask(taskToUpdate);

        Task updatedTask = new Task("Tarefa", "Descrição", LocalDate.of(2024, 2, 1), Task.TaskPriority.MEDIUM);
        updatedTask.setId(1);
        taskManager.updateTask(updatedTask);

        Task retrievedTask = taskManager.getTaskById(1);
        assertEquals(LocalDate.of(2024, 2, 1), retrievedTask.getDueDate());
    }

    @Test
    public void testUpdatePriorityOnly() {
        Task taskToUpdate = new Task("Tarefa", "Descrição", LocalDate.now(), Task.TaskPriority.LOW);
        taskToUpdate.setId(1);
        taskManager.addTask(taskToUpdate);

        Task updatedTask = new Task("Tarefa", "Descrição", LocalDate.now(), Task.TaskPriority.HIGH);
        updatedTask.setId(1);
        taskManager.updateTask(updatedTask);

        Task retrievedTask = taskManager.getTaskById(1);
        assertEquals(Task.TaskPriority.HIGH, retrievedTask.getPriority());
    }

    @Test()
    public void testDeleteNonexistentTask() {
        taskManager.deleteTask(999); // Supondo que 999 não exista
    }

    @Test
    public void testGetNoTasksFoundMessage() {
        assertTrue(taskManager.getTasks().isEmpty());

    }

    @Test
    public void testUpdateTaskTitleOnly() {
        Task task = new Task("Título Original", "Descrição", LocalDate.now(), Task.TaskPriority.MEDIUM);
        taskManager.addTask(task);
        task.setTitle("Título Atualizado");
        taskManager.updateTask(task);
        assertEquals("Título Atualizado", taskManager.getTaskById(task.getId()).getTitle());
    }

    @Test
    public void testUpdateTaskDescriptionOnly() {
        Task task = new Task("Título", "Descrição Original", LocalDate.now(), Task.TaskPriority.MEDIUM);
        taskManager.addTask(task);
        task.setDescription("Descrição Atualizada");
        taskManager.updateTask(task);
        assertEquals("Descrição Atualizada", taskManager.getTaskById(task.getId()).getDescription());
    }

    @Test
    public void testUpdateTaskDueDateOnly() {
        Task task = new Task("Título", "Descrição", LocalDate.of(2024, 1, 1), Task.TaskPriority.MEDIUM);
        taskManager.addTask(task);
        task.setDueDate(LocalDate.of(2024, 9, 22));
        taskManager.updateTask(task);
        assertEquals(LocalDate.of(2024, 9, 22), taskManager.getTaskById(task.getId()).getDueDate());
    }

    @Test
    public void testUpdateTaskPriorityOnly() {
        Task task = new Task("Título", "Descrição", LocalDate.now(), Task.TaskPriority.LOW);
        taskManager.addTask(task);
        task.setPriority(Task.TaskPriority.HIGH);
        taskManager.updateTask(task);
        assertEquals(Task.TaskPriority.HIGH, taskManager.getTaskById(task.getId()).getPriority());
    }

    @Test
    public void testDeleteTaskWithValidId() {
        Task taskToDelete = new Task("Tarefa para Excluir", "Descrição", LocalDate.now(), Task.TaskPriority.LOW);
        taskManager.addTask(taskToDelete);
        int taskId = taskToDelete.getId();
        taskManager.deleteTask(taskId);
        assertNull(taskManager.getTaskById(taskId));
    }

    @Test()
    public void testDeleteTaskWithInvalidId() {
        taskManager.deleteTask(999);
    }

    @Test
    public void testGetTasksOrderedByDueDate() {
        Task task1 = new Task("Tarefa 1", "Descrição 1", LocalDate.of(2024, 3, 1), Task.TaskPriority.MEDIUM);
        Task task2 = new Task("Tarefa 2", "Descrição 2", LocalDate.of(2024, 1, 1), Task.TaskPriority.HIGH);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> orderedTasks = taskManager.getTasksOrderedByDueDateAndPriority();
        assertEquals("Tarefa 2", orderedTasks.get(0).getTitle()); // A tarefa 2 deve vir primeiro pois tem a data de vencimento mais próxima.
    }

    @Test
    public void testGetTasksOrderedByPriority() {
        Task task1 = new Task("Tarefa 1", "Descrição 1", LocalDate.of(2024, 3, 1), Task.TaskPriority.LOW);
        Task task2 = new Task("Tarefa 2", "Descrição 2", LocalDate.of(2024, 1, 1), Task.TaskPriority.HIGH);
        taskManager.addTask(task1);
        taskManager.addTask(task2);

        List<Task> orderedTasks = taskManager.getTasksOrderedByDueDateAndPriority();
        assertEquals("Tarefa 2", orderedTasks.get(0).getTitle()); // A tarefa 2 deve vir primeiro devido à prioridade mais alta, mesmo com a data sendo igualmente considerada.
    }

    @Test
    public void testNoTasksFound() {
        assertTrue(taskManager.getTasks().isEmpty());
    }
}
