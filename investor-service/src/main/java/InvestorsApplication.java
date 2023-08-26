import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class InvestorsApplication {

    public static void main(String[] args) {
        System.out.println("---------- InvestorsApplication is running ");
        SpringApplication.run(InvestorsApplication.class, args);
    }

}
