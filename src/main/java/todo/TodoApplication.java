package todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * APIs模块的主应用程序类。
 * SpringBootApplication 该类作为Spring Boot应用程序的入口点。
 * EnableTransactionManagement 它启用自动配置和事务管理功能。
 */
@SpringBootApplication
@EnableTransactionManagement
public class TodoApplication {
    /**
     * 启动Spring Boot应用程序的主方法。
     * 此方法触发应用程序启动并初始化Spring上下文。
     *
     * @param args 传递给应用程序的命令行参数
     */
    public static void main(String[] args) {
        SpringApplication.run(TodoApplication.class, args);
    }

}
