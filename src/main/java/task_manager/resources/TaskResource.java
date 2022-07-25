package task_manager.resources;
import com.google.inject.Inject;
import task_manager.Interceptor.Cacheable;
import task_manager.db.TaskService;
import com.odesk.agora.Resource;
import task_manager.entity.Task;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/tasks")
public class TaskResource extends Resource{
    private final TaskService taskService;

    @Inject
    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Cacheable
    public List<Task> findAllTasks() {
        return taskService.findAllTasks();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @Cacheable
    public Task findTaskById(@PathParam("id") String id) {
        return taskService.findTaskById(id).orElseThrow(() -> new WebApplicationException("Task not found", 404));
    }

    @POST
    public Task insertTask(Task task) {
        return taskService.insertTask(task);
    }

    @PUT
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Task updateTaskById(@PathParam("id") String id, Task task) {
         return taskService.updateTask(task, id);
    }

    @DELETE
    @Path("/{id}")
    public boolean deleteTaskById(@PathParam("id") String id) {
       return taskService.deleteTask(id);
    }
}
