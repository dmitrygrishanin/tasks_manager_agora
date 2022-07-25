package task_manager.db;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.BindBean;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.SqlUpdate;
import org.skife.jdbi.v2.sqlobject.customizers.RegisterMapper;
import org.skife.jdbi.v2.sqlobject.customizers.SingleValueResult;
import task_manager.entity.Task;

import java.util.List;
import java.util.Optional;

@RegisterMapper({TaskDAOMapper.class})
public interface TaskDAO {
    @SqlUpdate("delete from task_review where id = :id")
    int deleteTask(@Bind("id") String id);

    @SqlUpdate("update task_review set task = :task, status = :status, priority = :priority where id = :id")
    int updateTask(@BindBean Task task, @Bind("id") String id);

    @SqlUpdate("insert into task_review (id, task, status, priority) values (:id, :task, :status, :priority)")
    int insertTask(@BindBean Task task);

    @SqlQuery("select * from task_review where id = :id")
    @SingleValueResult
    Optional<Task> findTaskById(@Bind("id") String id);

    @SqlQuery("select * from task_review")
    @SingleValueResult
    List<Task> findAllTasks();
}
