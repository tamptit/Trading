package stock.trading.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.Environment;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class OrderApplication   {

	@Value( "${my_name}" )
	private String my_name;

	@Value("${spring.profiles.active}")
	private String activeProfile;

	@Autowired
	private Environment environment;

	private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OrderApplication.class, args);
		OrderApplication op = new OrderApplication();
		String profileActive = System.getProperty("spring.profiles.active", "unknown");
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream input = classloader.getResourceAsStream("application-"+ profileActive + ".properties");
		final Properties properties = new Properties();
		properties.load(input);
		System.out.println("---------- my_name ------ : "+ properties.getProperty("my_name"));
	}

	public String getMy_name() {
		return my_name;
	}

	public void setMy_name(String my_name) {
		this.my_name = my_name;
	}

	//	@Override
//	public void run1(String...args) throws Exception {
//	@Bean
//	public CommandLineRunner demo(@Autowired OrderRepository orRepo) {
//		return (args) -> {
//			// save a few customers
//			orRepo.save(new Order("BCE"));
//			orRepo.save(new Order("FPT"));
//			orRepo.save(new Order("HPG"));
//
//			// fetch an individual customer by ID
//			Optional<Order> customer = orRepo.findById(1);
//			log.info("Order found with findById(1L):");
//			log.info("--------------------------------");
//			log.info(customer.toString());
//			log.info("");
//		};
//	}

//	@Bean
//	List<String> getAllStockId(){
//	}

//	@Bean
//	NewTopic notification(){
//		return new NewTopic("notification", 2, (short)1);
//	}
//
//	@Bean
//	NewTopic statistic(){
//		return new NewTopic("statistic", 1, (short)1);
//	}


}
