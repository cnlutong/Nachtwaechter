package de.luandtong.nachtwaechter.appl.repository.server;

import de.luandtong.nachtwaechter.domain.server.ServerKey;

public interface ServerKeyRepository {

    public void save(ServerKey serverKey);

    public ServerKey findServerKeyByID(long id);
}
