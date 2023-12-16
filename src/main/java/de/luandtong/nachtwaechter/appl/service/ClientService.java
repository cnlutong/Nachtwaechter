package de.luandtong.nachtwaechter.appl.service;

import de.luandtong.nachtwaechter.appl.repository.WireGuardKeyRepository;
import de.luandtong.nachtwaechter.appl.repository.client.ClientInfoRepository;
import de.luandtong.nachtwaechter.appl.repository.client.ServerPublicInfoRepository;
import de.luandtong.nachtwaechter.domain.client.Client;

import java.util.UUID;

public class ClientService {


    private ClientInfoRepository clientInfoRepository;
    private WireGuardKeyRepository wireGuardKeyRepository;

    private ServerPublicInfoRepository serverPublicInfoRepository;
    private Client client;

    private int serverID;

    public void init(int clientID) {
        this.client = new Client(UUID.randomUUID(), clientInfoRepository.findClientInfoByID(clientID), wireGuardKeyRepository.findWireGuardKeyByID(clientID), serverPublicInfoRepository.findServerPublicInfoByID(serverID));
    }

    public void saveClientInfo() {
        clientInfoRepository.save(this.client.getClientInfo());
    }

    public void saveWireGuardKey() {
        wireGuardKeyRepository.save(this.client.getWireGuardKey());
    }
}
