package de.luandtong.nachtwaechter.domain.server;

import org.springframework.data.relational.core.mapping.Table;

public record ServerInfo(String server_eth, String server_ip, String server_port, String pkgMgr) {
}
