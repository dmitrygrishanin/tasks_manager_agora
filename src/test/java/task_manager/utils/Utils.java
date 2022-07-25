package task_manager.utils;

import org.apache.commons.lang.RandomStringUtils;
import task_manager.entity.Task;

import java.util.Random;
import java.util.UUID;

public class Utils {
    static Random random = new Random();
    final static String[] status = {"new", "in progress", "closed"};

    public static Task getGeneratedTask() {
        Task task = new Task();
        task.setId(UUID.randomUUID().toString());
        task.setTask(RandomStringUtils.randomAlphanumeric(17));
        task.setPriority(random.nextInt(5 - 1) + 1);
        task.setStatus(status[random.nextInt(status.length)]);
        return task;
    }
}
