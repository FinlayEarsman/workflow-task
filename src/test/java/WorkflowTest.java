import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.workflow.Status;
import org.workflow.Task;
import org.workflow.Workflow;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WorkflowTest {

    private Workflow workflow;

    @BeforeEach
    void setUp() {
        Task t1 = new Task("Fetch Data", List.of());
        Task t2 = new Task("Validate Data", List.of("Fetch Data"));
        Task t3 = new Task("Generate Report", List.of("Validate Data"));

        workflow = new Workflow();
        workflow.addTask(t1);
        workflow.addTask(t2);
        workflow.addTask(t3);
    }

    @Test
    void testAddTasks() {
        assertEquals(3, workflow.getAllTasks().size());
    }

    @Test
    void testReadyTasks() {
        List<Task> ready = workflow.getReadyTasks();
        assertEquals(1, ready.size());
        assertEquals("Fetch Data", ready.getFirst().getName());
    }

    @Test
    void testUpdateStatusWithDependenciesMet() {
        workflow.updateStatus("Fetch Data", Status.COMPLETED);

        List<Task> ready = workflow.getReadyTasks();
        assertEquals(1, ready.size());
        assertEquals("Validate Data", ready.getFirst().getName());
    }

    @Test
    void testUpdateStatusWithDependenciesNotMet() {
        assertThrows(IllegalStateException.class, () ->
                workflow.updateStatus("Validate Data", Status.IN_PROGRESS));
    }

    @Test
    void testUpdateInvalidTask() {
        assertThrows(IllegalArgumentException.class, () ->
                workflow.updateStatus("Nonexistent Task", Status.COMPLETED));
    }
}
