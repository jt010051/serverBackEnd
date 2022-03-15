package io.getarrays.server;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;


@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
	@Bean
	CommandLineRunner run(ServerRepo serverRepo) {
		return args ->{
			
			serverRepo.save(new Server(null, "192.168.1.168", "Ubuntu Linux", "16GB", 
					"Personal PC", "http://localhost:8080/server/image/server1.png",
					io.getarrays.server.enumeration.Status.SERVER_UP));
			serverRepo.save(new Server(null, "192.168.1.58", "Fedora Linux", "16GB", 
					"Dell Tower", "http://localhost:8080/server/image/server2.png",
					io.getarrays.server.enumeration.Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.21", "MS 2008", "32GB", 
					"Macbook", "http://localhost:8080/server/image/server3.png",
					io.getarrays.server.enumeration.Status.SERVER_DOWN));
			serverRepo.save(new Server(null, "192.168.1.32", "Windows 95", "8MB", 
					"Vintage PC", "http://localhost:8080/server/image/server4.png",
					io.getarrays.server.enumeration.Status.SERVER_UP));
			
		};
		
		
	}

}
