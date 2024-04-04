package junit5Tests;

import org.example.Task;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("TaskManager Comprehensive Tests")
class TaskManagerTest {

    private Task taskManager;

    @BeforeEach
    void setUp() {
        taskManager = new Task();
    }

    @Nested
    @DisplayName("Add Task Tests")
    class AddTaskTests {

        @Test
        @DisplayName("Successfully add a task")
        void testAddTask() {
            Task task = new Task("Study JUnit", "Study JUnit for the project", LocalDate.now().plusDays(1), Task.TaskPriority.MEDIUM);
            taskManager.addTask(task);
            assertEquals(1, taskManager.getTasks().size(), "There should be one task after adding.");
        }

        @Test
        @DisplayName("Attempt to add a null task throws NullPointerException")
        void testAddNullTask() {
            assertThrows(NullPointerException.class, () -> taskManager.addTask(null), "Adding a null task should throw NullPointerException.");
        }
    }

    @Nested
    @DisplayName("Update Task Tests")
    class UpdateTaskTests {

        @Test
        @DisplayName("Successfully update a task")
        void testUpdateTask() {
            Task task = new Task("Study JUnit", "Initial description", LocalDate.now().plusDays(1), Task.TaskPriority.MEDIUM);
            taskManager.addTask(task);
            int taskId = task.getId();

            Task updatedTask = new Task("Study JUnit Updated", "Updated description", LocalDate.now().plusDays(2), Task.TaskPriority.HIGH);
            updatedTask.setId(taskId);
            taskManager.updateTask(updatedTask);

            Task result = taskManager.getTaskById(taskId);
            assertAll("task",
                    () -> assertEquals("Study JUnit Updated", result.getTitle()),
                    () -> assertEquals("Updated description", result.getDescription()),
                    () -> assertEquals(LocalDate.now().plusDays(2), result.getDueDate()),
                    () -> assertEquals(Task.TaskPriority.HIGH, result.getPriority())
            );
        }

        @Test
        @DisplayName("Attempt to update with a null task throws NullPointerException")
        void testUpdateWithNull() {
            assertThrows(NullPointerException.class, () -> taskManager.updateTask(null), "Updating with a null task should throw NullPointerException.");
        }

        @Test
        @DisplayName("Attempt to update a non-existent task throws IllegalArgumentException")
        void testUpdateNonExistentTask() {
            Task updatedTask = new Task("Non-existent task", "This task does not exist", LocalDate.now(), Task.TaskPriority.LOW);
            updatedTask.setId(999); // Assuming this ID does not exist.
            assertThrows(IllegalArgumentException.class, () -> taskManager.updateTask(updatedTask), "Updating a non-existent task should throw IllegalArgumentException.");
        }
    }

    @Nested
    @DisplayName("Delete Task Tests")
    class DeleteTaskTests {

        @Test
        @DisplayName("Successfully delete a task")
        void testDeleteTask() {
            Task task = new Task("Read book", "Read a book about TDD", LocalDate.now().plusDays(3), Task.TaskPriority.LOW);
            taskManager.addTask(task);
            int taskId = task.getId();

            taskManager.deleteTask(taskId);
            assertNull(taskManager.getTaskById(taskId), "The task should be null after deletion.");
        }

        @Test
        @DisplayName("Attempt to delete a non-existent task throws IllegalArgumentException")
        void testDeleteNonExistentTask() {
            assertThrows(IllegalArgumentException.class, () -> taskManager.deleteTask(999), "Attempting to delete a non-existent task should throw IllegalArgumentException.");
        }
    }

    @Nested
    @DisplayName("Tasks By Priority Tests")
    class TasksByPriorityTests {

        @BeforeEach
        void setUp() {
            taskManager.addTask(new Task("High Priority Task", "Description High", LocalDate.now().plusDays(1), Task.TaskPriority.HIGH));
            taskManager.addTask(new Task("Medium Priority Task", "Description Medium", LocalDate.now().plusDays(2), Task.TaskPriority.MEDIUM));
            taskManager.addTask(new Task("Low Priority Task", "Description Low", LocalDate.now().plusDays(3), Task.TaskPriority.LOW));
        }

        @Test
        @DisplayName("Filter tasks by HIGH priority")
        void testGetTasksByHighPriority() {
            List<Task> highPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.HIGH);
            assertEquals(1, highPriorityTasks.size(), "There should be one high priority task.");
            assertEquals("High Priority Task", highPriorityTasks.get(0).getTitle());
        }

        @Test
        @DisplayName("Filter tasks by MEDIUM priority")
        void testGetTasksByMediumPriority() {
            List<Task> mediumPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.MEDIUM);
            assertEquals(1, mediumPriorityTasks.size(), "There should be one medium priority task.");
            assertEquals("Medium Priority Task", mediumPriorityTasks.get(0).getTitle());
        }

        @Test
        @DisplayName("Filter tasks by LOW priority")
        void testGetTasksByLowPriority() {
            List<Task> lowPriorityTasks = taskManager.getTasksByPriority(Task.TaskPriority.LOW);
            assertEquals(1, lowPriorityTasks.size(), "There should be one low priority task.");
            assertEquals("Low Priority Task", lowPriorityTasks.get(0).getTitle());
        }
    }

    // Additional tests for Task class getters and setters if needed
}
