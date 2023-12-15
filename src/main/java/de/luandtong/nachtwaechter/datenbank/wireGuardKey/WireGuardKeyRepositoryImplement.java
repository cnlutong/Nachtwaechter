package de.luandtong.nachtwaechter.datenbank.wireGuardKey;

import de.luandtong.nachtwaechter.appl.repository.WireGuardKeyRepository;
import de.luandtong.nachtwaechter.domain.WireGuardKey;

public class WireGuardKeyRepositoryImplement implements WireGuardKeyRepository {

    private WireGuardKeyDBRepository wireGuardKeyDBRepository;

    @Override
    public void save(WireGuardKey wireGuardKey) {
        wireGuardKeyDBRepository.save(EntityToDTO(wireGuardKey));
    }

    @Override
    public WireGuardKey findWireGuardKeyByID(long id) {
        return DTOToEntity(wireGuardKeyDBRepository.findWireGuardKeyDTOById(id));
    }

    WireGuardKeyDTO EntityToDTO(WireGuardKey wireGuardKey){
        return new WireGuardKeyDTO(wireGuardKey.publicKey(), wireGuardKey.privateKey());
    }

    WireGuardKey DTOToEntity(WireGuardKeyDTO wireGuardKeyDTO){
        return new WireGuardKey(wireGuardKeyDTO.getPublicKey(), wireGuardKeyDTO.getPrivateKey());
    }
}
