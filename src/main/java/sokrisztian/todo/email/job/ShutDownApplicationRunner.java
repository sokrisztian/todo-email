package sokrisztian.todo.email.job;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class ShutDownApplicationRunner implements CommandLineRunner {

    private final ConfigurableApplicationContext context;

    public ShutDownApplicationRunner(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @Override
    public void run(String... args) {
        System.exit(SpringApplication.exit(context));
    }

}
