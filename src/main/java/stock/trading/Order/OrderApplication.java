package stock.trading.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import stock.trading.Order.repositories.OrderRepository;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@SpringBootApplication
public class OrderApplication   {

	private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);

	@Autowired
	private static OrderRepository orderRepository;

	public OrderApplication(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public static void main(String[] args) throws IOException {
		SpringApplication.run(OrderApplication.class, args);
		String profileActive = System.getProperty("spring.profiles.active", "unknown");
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		if("prod".equals(profileActive) || "dev".equals(profileActive)){
			final Properties properties = new Properties();
			InputStream input = classloader.getResourceAsStream("application-"+ profileActive + ".properties");
			properties.load(input);
			System.out.println("---------- profileActive: "+ profileActive + " and my_name: " + properties.getProperty("my_name"));
		}else{
			System.out.println("profileActive= " + profileActive );
			System.out.println("my_name= " + System.getProperty("my_name", "can not get 000") );
			System.out.println("spring-boot.run.profiles= " + System.getProperty("spring-boot.run.profiles", "can not get 001"));
		}
		System.out.println("---------- Test table in DB: size = "+ orderRepository.findAll().size());
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
