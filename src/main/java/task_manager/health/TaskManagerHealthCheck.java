package task_manager.health;


import com.codahale.metrics.health.HealthCheck;

public class TaskManagerHealthCheck extends HealthCheck {

    public TaskManagerHealthCheck() {
        super();
    }

    @Override
    protected Result check() throws Exception {
        // TODO Add database check
        return Result.healthy();
    }
}
