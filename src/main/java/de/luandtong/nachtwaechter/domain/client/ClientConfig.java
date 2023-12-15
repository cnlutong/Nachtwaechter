package de.luandtong.nachtwaechter.domain.client;

import de.luandtong.nachtwaechter.domain.WireGuardKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static de.luandtong.nachtwaechter.domain.Command.run;

public class ClientConfig {

    private String clientConf;


    public void clientInit(ClientInfo clientInfo, WireGuardKey wireGuardKey, ServerPublicInfo serverPublicInfo) throws IOException, InterruptedException {
        // 创建客户端 WireGuard 配置文件内容
        this.clientConf = "#" + clientInfo.clientName() + "\n" +
                "[Interface]\n" +
                "ClientPrivateKey = " + wireGuardKey.privateKey() + "\n" +
                "Address = " + clientInfo.clientIP() + "\n" +
                "DNS = 1.1.1.1, 1.0.0.1\n\n" +
                "[Peer]\n" +
                "ClientPublicKey = " + serverPublicInfo.ServerPublicKey() + "\n" +
                "Endpoint = " + serverPublicInfo.IP() + ":" + serverPublicInfo.ServerPort() + "\n" +
                "AllowedIPs = 0.0.0.0/0\n" +
                "PersistentKeepalive = 15" + "\n";

        // 写入客户端 WireGuard 配置文件
        Files.write(Paths.get("/etc/wireguard/clients/" + clientInfo.clientName() + "_wg0.conf"), clientConf.getBytes());

        serverConfigAddClient(clientInfo, wireGuardKey);

        // 重启 WireGuard 服务以应用更改
        run("sudo systemctl restart wg-quick@wg0.service");
    }

    public void serverConfigAddClient(ClientInfo clientInfo, WireGuardKey wireGuardKey) throws IOException {
        // 更新服务器 WireGuard 配置文件以添加客户端信息
        String peerInfo = "\n[Peer]\nPublicKey = " + wireGuardKey.publicKey() + "\nAllowedIPs = " + clientInfo.clientIP() + "\n";
        Files.write(Paths.get("/etc/wireguard/wg0.conf"), peerInfo.getBytes(), StandardOpenOption.APPEND);

    }
}
