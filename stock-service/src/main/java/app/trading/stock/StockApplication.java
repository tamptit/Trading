package app.trading.stock;

import jakarta.annotation.Resources;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Timestamp;
import java.time.LocalDateTime;
import java.util.Timer;
//import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
//@EnableDiscoveryClient
@ComponentScan(basePackages = "app.trading.stock")
public class StockApplication {

	public static void main(String[] args) throws IOException, URISyntaxException {
		SpringApplication.run(StockApplication.class, args);
		System.out.println("Stock service is running...");

		LocalDateTime currentTime = LocalDateTime.now();
		String str = "OK. Test write file. \nIt's " + currentTime.toString() + " now.";
		URL resourcePath = StockApplication.class.getClassLoader().getResource("./");
		URL rsPath = StockApplication.class.getResource("/");
		String resourceStr = "";
		if (resourcePath != null) {
			System.out.println("resourcePath not null");
			resourceStr = resourcePath.getPath().substring(1);
		}else if (rsPath != null){
			resourceStr = rsPath.getPath().substring(1);
		}
		System.out.println("resourceStr = " + resourceStr);
//		Path makerDirectory = Files.createDirectories(Paths.get(resourceStr + "freemarker"));
//		FileOutputStream outputStream = new FileOutputStream(makerDirectory + "/newFile.txt");
//		byte[] strToBytes = str.getBytes();
//		outputStream.write(strToBytes);
//		outputStream.close();
	}
}
