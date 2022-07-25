package task_manager.module;

import com.google.inject.*;
import com.tasks.app.cache.CacheManager;
import io.dropwizard.jdbi.DBIFactory;
import io.dropwizard.setup.Environment;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import com.odesk.agora.guice.GuiceModule;
import task_manager.Interceptor.CacheTask;
import task_manager.Interceptor.Cacheable;
import task_manager.TaskManagerConfiguration;
import task_manager.db.TaskDAO;
import org.skife.jdbi.v2.DBI;
import task_manager.db.TaskService;

import static com.google.inject.matcher.Matchers.annotatedWith;
import static com.google.inject.matcher.Matchers.any;

public class AppModule extends GuiceModule  {

    @Override
    protected void configure() {
           bindInterceptor(any(),
                annotatedWith(Cacheable.class),
                new CacheTask(getProvider(CacheManager.class), getProvider(TaskDAO.class)));
    }

    @Provides
    @Singleton
    public DBI prepareJdbi(Environment environment,
                            TaskManagerConfiguration configuration) {
        DBIFactory factory = new DBIFactory();
        return factory.build(environment, configuration.getDataSourceFactory(), "postgresql");
    }

    @Provides
    @Singleton
    public Jedis prepareJedis() {
        JedisPool jedisPool = new JedisPool(new JedisPoolConfig(), "localhost", 6379);
        return jedisPool.getResource();
    }

    @Provides
    @Singleton
    public CacheManager prepareCacheManager(Jedis jedis) {
        return new CacheManager(jedis);
    }

    @Provides
    @Singleton
    public TaskDAO providesTaskDAO(DBI db) {
        return db.onDemand(TaskDAO.class);
    }

    @Provides
    @Singleton
    public TaskService providesTaskService(TaskDAO taskDAO, CacheManager cacheManager) {
        return new TaskService(taskDAO, cacheManager);
    }
    }