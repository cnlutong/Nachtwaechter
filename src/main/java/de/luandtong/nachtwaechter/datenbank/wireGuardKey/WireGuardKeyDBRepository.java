package de.luandtong.nachtwaechter.datenbank.wireGuardKey;

import org.springframework.data.repository.CrudRepository;

public interface WireGuardKeyDBRepository extends CrudRepository<WireGuardKeyDTO, Long> {


    WireGuardKeyDTO findWireGuardKeyDTOById(Long id);
}
