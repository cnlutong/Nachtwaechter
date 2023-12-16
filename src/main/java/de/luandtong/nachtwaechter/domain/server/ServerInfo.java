package de.luandtong.nachtwaechter.domain.server;

import java.util.UUID;

public record ServerInfo(UUID uuid, UUID WireGuardKeyUUID, String server_eth, String server_ip, String server_port,
                         String pkgMgr) {
}
