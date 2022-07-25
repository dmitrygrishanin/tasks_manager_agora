package task_manager.db;

import org.skife.jdbi.v2.StatementContext;
import task_manager.entity.Task;
import org.skife.jdbi.v2.tweak.ResultSetMapper;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TaskDAOMapper implements ResultSetMapper<Task> {

    @Override
    public Task map(int i, ResultSet resultSet, StatementContext statementContext) throws SQLException {
        return new Task(resultSet.getString("id"), resultSet.getString("task"),resultSet.getString("status"),resultSet.getInt("priority"));
    }
}
