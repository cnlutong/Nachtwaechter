package de.luandtong.nachtwaechter.appl.repository.client;

import de.luandtong.nachtwaechter.domain.client.ClientKey;

public interface ClientKeyRepository {
    ClientKey findClientKeyByID(int clientID);

    void save(ClientKey clientKey);
}
