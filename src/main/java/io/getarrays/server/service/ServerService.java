package io.getarrays.server.service;

import java.util.Collection;

import io.getarrays.server.model.Server;

public interface ServerService {
Server create(Server server);

Collection<Server> list(int limit);
}
