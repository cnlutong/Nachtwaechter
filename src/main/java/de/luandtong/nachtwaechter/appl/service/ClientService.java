package de.luandtong.nachtwaechter.appl.service;

import de.luandtong.nachtwaechter.appl.repository.client.ClientInfoRepository;
import de.luandtong.nachtwaechter.appl.repository.client.ClientKeyRepository;
import de.luandtong.nachtwaechter.appl.repository.client.ServerPublicInfoRepository;
import de.luandtong.nachtwaechter.domain.client.Client;
import de.luandtong.nachtwaechter.domain.client.ClientInfo;
import de.luandtong.nachtwaechter.domain.client.ServerPublicInfo;
import de.luandtong.nachtwaechter.domain.server.Server;

public class ClientService {


    private ClientInfoRepository clientInfoRepository;
    private ClientKeyRepository clientKeyRepository;

    private ServerPublicInfoRepository serverPublicInfoRepository;
    private Client client;

    private int serverID;

    public void init(int clientID) {
        this.client = new Client(clientInfoRepository.findClientInfoByID(clientID), clientKeyRepository.findClientKeyByID(clientID), serverPublicInfoRepository.findServerPublicInfoByID(serverID));
    }

    public void saveClientInfo() {
        clientInfoRepository.save(this.client.getClientInfo());
    }

    public void saveClientKey() {
        clientKeyRepository.save(this.client.getClientKey());
    }
}
