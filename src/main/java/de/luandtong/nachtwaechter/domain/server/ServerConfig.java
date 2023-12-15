package de.luandtong.nachtwaechter.domain.server;

import de.luandtong.nachtwaechter.domain.WireGuardKey;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static de.luandtong.nachtwaechter.domain.Command.run;

public class ServerConfig {

    private String wg0Conf;


    public void serverInit(ServerInfo serverInfo, WireGuardKey wireGuardKey) throws IOException, InterruptedException {
        //服务端配置文件
        this.wg0Conf = "[Interface]\n" +
                "Address = 10.10.0.1/24\n" +
                "ListenPort = 51820\n" +
                "ClientPrivateKey = " + wireGuardKey.privateKey() + "\n" +
                "PostUp = iptables -A FORWARD -i wg0 -j ACCEPT; iptables -t nat -A POSTROUTING -o "
                + serverInfo.server_eth() + " -j MASQUERADE" + "\n" +
                "PostDown = iptables -D FORWARD -i wg0 -j ACCEPT; iptables -t nat -D POSTROUTING -o "
                + serverInfo.server_eth() + " -j MASQUERADE" + "\n";

        // 检查 /etc/wireguard/wg0.conf 文件是否存在
        Path wg0ConfPath = Paths.get("/etc/wireguard/wg0.conf");
        if (Files.exists(wg0ConfPath)) {
            // 如果存在，则删除文件
            run("sudo rm /etc/wireguard/wg0.conf");
        }

        // 写入 WireGuard 配置文件
        Files.write(Paths.get("/etc/wireguard/wg0.conf"), this.wg0Conf.getBytes());

        // 修改 WireGuard 配置文件和密钥的权限
        run("sudo chmod -R 600 /etc/wireguard/");

    }


}
