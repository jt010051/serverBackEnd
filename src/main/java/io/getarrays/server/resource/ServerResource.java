package io.getarrays.server.resource;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Response;
import io.getarrays.server.model.Server;
import io.getarrays.server.service.implementation.ServerServiceImplementation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerResource {
	
	
	
	private final ServerServiceImplementation serverService;
	
	
	@GetMapping("/list")

	public ResponseEntity<Response> getServer(){
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("servers", serverService.list(30)))
				.message("Servers retrieved")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
				);
		
	}
	@GetMapping("/ping/{ipAddress}")

	public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException{
		Server server = serverService.ping(ipAddress);
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", server))
				.message(server.getStatus()==Status.SERVER_UP ? "Ping Sucess": "Ping failed")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
				);
		
	}
	@PutMapping("/save")

	public ResponseEntity<Response> pingServer(@RequestBody @Valid Server server) {
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", serverService.create(server)))
				.message("ServerCreated")
				.status(HttpStatus.CREATED)
				.statusCode(HttpStatus.CREATED.value())
				.build()
				);
		
	}
	
	@GetMapping("/get/{id}")

	public ResponseEntity<Response> getServer(@PathVariable("id") Long id) {
		
		return ResponseEntity.ok(
				Response.builder()
				.timeStamp(LocalDateTime.now())
				.data(Map.of("server", serverService.get(id)))
				.message("Server retrieved")
				.status(HttpStatus.OK)
				.statusCode(HttpStatus.OK.value())
				.build()
				);
		
	}
}
