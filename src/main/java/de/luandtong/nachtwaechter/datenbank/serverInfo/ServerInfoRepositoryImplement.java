package de.luandtong.nachtwaechter.datenbank.serverInfo;

import de.luandtong.nachtwaechter.appl.repository.server.ServerInfoRepository;
import de.luandtong.nachtwaechter.domain.server.ServerInfo;

public class ServerInfoRepositoryImplement implements ServerInfoRepository {

    private ServerinfoDBRepository serverinfoDBRepository;

    @Override
    public void save(ServerInfo serverInfo) {
        serverinfoDBRepository.save(EntityToDTO(serverInfo));
    }

    @Override
    public ServerInfo findServerInfoByID(long id) {
        return DTOToEntity(serverinfoDBRepository.findServerInfoDTOById(id));
    }

    ServerInfoDTO EntityToDTO(ServerInfo serverInfo){
        return new ServerInfoDTO(serverInfo.server_eth(), serverInfo.server_ip(), serverInfo.server_port(), serverInfo.pkgMgr());
    }

    ServerInfo DTOToEntity(ServerInfoDTO serverInfoDTO){
        return new ServerInfo(serverInfoDTO.getServer_eth(), serverInfoDTO.getServer_ip(), serverInfoDTO.getServer_port(), serverInfoDTO.getPkgMgr());
    }
}
