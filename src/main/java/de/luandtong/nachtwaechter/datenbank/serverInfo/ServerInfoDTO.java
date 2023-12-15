package de.luandtong.nachtwaechter.datenbank.serverInfo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ServerInfo")
public class ServerInfoDTO {

    //String server_eth, String server_ip, String server_port, String pkgMgr

    @Id
    private long id;
    private final String server_eth;
    private final String server_ip;
    private final String server_port;
    private final String pkgMgr;

    public ServerInfoDTO(String server_eth, String server_ip, String server_port, String pkgMgr) {
        this.server_eth = server_eth;
        this.server_ip = server_ip;
        this.server_port = server_port;
        this.pkgMgr = pkgMgr;
    }

    public long getId() {
        return id;
    }

    public String getServer_eth() {
        return server_eth;
    }

    public String getServer_ip() {
        return server_ip;
    }

    public String getServer_port() {
        return server_port;
    }

    public String getPkgMgr() {
        return pkgMgr;
    }
}
