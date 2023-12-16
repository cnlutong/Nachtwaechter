package de.luandtong.nachtwaechter.datenbank.serverInfo;

import de.luandtong.nachtwaechter.appl.repository.server.ServerInfoRepository;
import de.luandtong.nachtwaechter.domain.server.ServerInfo;

import java.util.UUID;

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

    ServerInfoDTO EntityToDTO(ServerInfo serverInfo) {
        return new ServerInfoDTO(serverInfo.uuid().toString(), serverInfo.WireGuardKeyUUID().toString(),
                serverInfo.server_eth(), serverInfo.server_ip(), serverInfo.server_port(), serverInfo.pkgMgr());
    }

    ServerInfo DTOToEntity(ServerInfoDTO serverInfoDTO) {
        return new ServerInfo(UUID.fromString(serverInfoDTO.getUuid()), UUID.fromString(serverInfoDTO.getWireGuardKeyUUID()),
                serverInfoDTO.getServerEth(), serverInfoDTO.getServerIp(), serverInfoDTO.getServerPort(), serverInfoDTO.getPkgMgr());
    }
}
