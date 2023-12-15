package de.luandtong.nachtwaechter.datenbank.serverInfo;

import org.springframework.data.repository.CrudRepository;

public interface ServerinfoDBRepository extends CrudRepository<ServerInfoDTO, Long> {

    ServerInfoDTO findServerInfoDTOById(Long id);
}
