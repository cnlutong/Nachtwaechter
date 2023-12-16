package de.luandtong.nachtwaechter.datenbank.serverInfo;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("ServerInfo")
public class ServerInfoDTO {

    //String server_eth, String server_ip, String server_port, String pkgMgr

    private final String wireGuardKeyUUID;
    private final String uuid;
    private final String serverEth;
    private final String serverIp;
    private final String serverPort;
    private final String pkgMgr;
    @Id
    private long id;

    public ServerInfoDTO(String wireGuardKeyUUID, String uuid, String serverEth, String serverIp, String serverPort, String pkgMgr) {
        this.wireGuardKeyUUID = wireGuardKeyUUID;
        this.uuid = uuid;
        this.serverEth = serverEth;
        this.serverIp = serverIp;
        this.serverPort = serverPort;
        this.pkgMgr = pkgMgr;
    }

    public long getId() {
        return id;
    }

    public String getWireGuardKeyUUID() {
        return wireGuardKeyUUID;
    }

    public String getUuid() {
        return uuid;
    }

    public String getServerEth() {
        return serverEth;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getServerPort() {
        return serverPort;
    }

    public String getPkgMgr() {
        return pkgMgr;
    }
}
