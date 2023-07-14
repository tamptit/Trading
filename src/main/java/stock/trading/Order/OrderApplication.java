package stock.trading.Order;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class OrderApplication   {

	private static final Logger log = LoggerFactory.getLogger(OrderApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(OrderApplication.class, args);
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
