package de.luandtong.nachtwaechter.appl.repository.client;

import de.luandtong.nachtwaechter.domain.client.ClientInfo;
import de.luandtong.nachtwaechter.domain.server.ServerInfo;
import de.luandtong.nachtwaechter.domain.server.ServerKey;

public interface ClientInfoRepository {
    ClientInfo findClientInfoByID(int clientID);

    void save(ClientInfo clientInfo);


}
