package app.trading.investors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.env.Environment;

@SpringBootApplication
@ComponentScan(basePackages = "app.trading.investors")
public class InvestorsApplication {

    private   Environment environment;

    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public static void main(String[] args) {
        System.out.println("---------- app.trading.investors.InvestorsApplication is running");
        ConfigurableApplicationContext context = SpringApplication.run(InvestorsApplication.class, args);
        System.out.println(" my_name = " + context.getEnvironment().getProperty("my_name"));
    }

}
