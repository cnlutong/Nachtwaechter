package de.luandtong.nachtwaechter.appl.service;

import de.luandtong.nachtwaechter.appl.repository.server.ServerInfoRepository;
import de.luandtong.nachtwaechter.appl.repository.WireGuardKeyRepository;
import de.luandtong.nachtwaechter.domain.server.Server;

public class ServerService {

    private ServerInfoRepository serverInfoRepository;
    private WireGuardKeyRepository wireGuardKeyRepository;

    private Server server;


    public void init(int serverID) {
        this.server = new Server(serverInfoRepository.findServerInfoByID(serverID), wireGuardKeyRepository.findWireGuardKeyByID(serverID));
    }

    public void saveServerInfo() {
        serverInfoRepository.save(this.server.getServerInfo());
    }

    public void saveWireGuardKey() {
        wireGuardKeyRepository.save(this.server.getServerKey());
    }


}
