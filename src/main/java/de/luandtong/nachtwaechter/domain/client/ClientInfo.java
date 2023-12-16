package de.luandtong.nachtwaechter.domain.client;

import java.util.UUID;

public record ClientInfo(UUID uuid, UUID WireGuardKeyUUID, int clientIndex, String clientName, String clientIP) {
}
