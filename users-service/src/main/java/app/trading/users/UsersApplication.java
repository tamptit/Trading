package app.trading.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = "app.trading.users")
public class UsersApplication {

    private   Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        System.out.println("---------- app.trading.users.UsersApplication is running");
        ConfigurableApplicationContext context = SpringApplication.run(UsersApplication.class, args);
        System.out.println(" my_name = " + context.getEnvironment().getProperty("my_name"));
    }

}
