package task_manager;

import com.odesk.agora.AgoraApplication;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import ru.vyarus.dropwizard.guice.GuiceBundle;
import task_manager.health.TaskManagerHealthCheck;
import task_manager.module.AppModule;
import task_manager.resources.TaskResource;

public class TaskManagerApplication extends AgoraApplication<TaskManagerConfiguration, AppModule> {

    public static void main(final String[] args) throws Exception {
        new TaskManagerApplication().run(args);
    }

    protected TaskManagerApplication() {
        super(serviceName, TaskManagerConfiguration.class, new TaskManagerHealthCheck(), new AppModule(), TaskResource.class);
    }

    @Override
    public String getName() {
        return "task_manager";
    }

    @Override
    public void initialize(final Bootstrap<TaskManagerConfiguration> bootstrap) {
        bootstrap.addBundle(GuiceBundle.builder()
                .enableAutoConfig(getClass().getPackage().getName())
                .modules(new AppModule())
                .build());
    }

    @Override
    public void run(final TaskManagerConfiguration configuration,
                    final Environment environment) throws Exception {
        super.run(configuration, environment);
    }

}
