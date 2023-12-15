package de.luandtong.nachtwaechter.appl.repository;

import de.luandtong.nachtwaechter.domain.WireGuardKey;

public interface WireGuardKeyRepository {

    public void save(WireGuardKey wireGuardKey);

    public WireGuardKey findWireGuardKeyByID(long id);
}
