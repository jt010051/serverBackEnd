package io.getarrays.server.service.implementation;

import java.io.IOException;
import java.net.InetAddress;
import java.util.Collection;

import javax.transaction.Transactional;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import io.getarrays.server.enumeration.Status;
import io.getarrays.server.model.Server;
import io.getarrays.server.repo.ServerRepo;
import io.getarrays.server.service.ServerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class ServerServiceImplementation  implements ServerService{
private final ServerRepo serverRepo;

	@Override
	public Server create(Server server) {
	log.info("Saving new server{}:", server.getName());
	server.setImageUrl(setServerImageURL());
	return serverRepo.save(server);
	}
@Override
	public Server ping(String ipAddress) throws IOException {
		log.info("Pinging Server IP{}:", ipAddress);
		Server server = serverRepo.findByIpAddress(ipAddress);
		InetAddress address =InetAddress.getByName(ipAddress);
		server.setStatus(address.isReachable(10000) ? Status.SERVER_UP : Status.SERVER_DOWN);
		serverRepo.save(server);
		return server;
	}

	@Override
	public Collection<Server> list(int limit) {
		log.info("Fetching all servers");
		return serverRepo.findAll(PageRequest.of(0, limit)).toList();
	}

	@Override
	public Server get(Long id) {
		log.info("Fetching server by id{}", id);
		return serverRepo.findById(id).get();
	}

	@Override
	public Server update(Server server) {
			log.info("Updating  server{}:", server.getName());
			return serverRepo.save(server);
			
	}

	@Override
	public Boolean delete(Long id) {
		log.info("Deleting server by ID {}:", id);
		serverRepo.deleteById(id);
		return Boolean.TRUE;
	}


	private String setServerImageURL() {
String [] imageNames;		

return null;
	}

}
