package de.luandtong.nachtwaechter.appl.service;

import de.luandtong.nachtwaechter.appl.repository.server.ServerInfoRepository;
import de.luandtong.nachtwaechter.appl.repository.server.ServerKeyRepository;
import de.luandtong.nachtwaechter.domain.server.Server;

public class ServerService {

    private ServerInfoRepository serverInfoRepository;
    private ServerKeyRepository serverKeyRepository;

    private Server server;



    public void init(int serverID){
        this.server = new Server(serverInfoRepository.findServerInfoByID(serverID), serverKeyRepository.findServerKeyByID(serverID));
    }

    public void saveServerInfo(){
        serverInfoRepository.save(this.server.getServerInfo());
    }

    public void saveServerKey(){
        serverKeyRepository.save(this.server.getServerKey());
    }



}
