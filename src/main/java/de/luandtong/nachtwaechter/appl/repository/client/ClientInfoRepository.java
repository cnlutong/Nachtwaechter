package de.luandtong.nachtwaechter.appl.repository.client;

import de.luandtong.nachtwaechter.domain.client.ClientInfo;

public interface ClientInfoRepository {
    ClientInfo findClientInfoByID(int clientID);

    void save(ClientInfo clientInfo);


}
