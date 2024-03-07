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
}