package de.luandtong.nachtwaechter.domain;

import java.util.UUID;

public record WireGuardKey(UUID uuid, String publicKey, String privateKey) {
}
