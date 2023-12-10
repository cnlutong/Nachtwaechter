package de.luandtong.nachtwaechter.appl.repository.server;

import de.luandtong.nachtwaechter.domain.server.ServerInfo;

public interface ServerInfoRepository {

    public void save(ServerInfo serverInfo);

    public ServerInfo findServerInfoByID(long id);
}
