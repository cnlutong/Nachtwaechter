package de.luandtong.nachtwaechter.domain.server;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

import static de.luandtong.nachtwaechter.domain.Command.run;

//领域事务
public class Server {

    private final ServerInfo serverInfo;
    private final ServerKey serverKey;
    private final ServerConfig serverConfig;

    public Server(ServerInfo serverInfo, ServerKey serverKey) {
        this.serverInfo = serverInfo;
        this.serverKey = serverKey;
        this.serverConfig = new ServerConfig();
    }


    public void serverInit() throws IOException, InterruptedException {

        //创建路径
        Path path = Paths.get("/etc/wireguard/clients/");
        if (!Files.exists(path)) {
            // 如果不存在，则创建目录
            run("sudo mkdir /etc/wireguard/clients/");
        }

        // 启用 IP 转发
        // 读取 /etc/sysctl.conf 文件的内容
        List<String> lines = Files.readAllLines(Paths.get("/etc/sysctl.conf"));

        // 检查 "#net.ipv4.ip_forward=1" 是否存在
        boolean commentedIpForwardExists = lines.stream()
                .map(String::trim)
                .anyMatch(line -> line.equals("#net.ipv4.ip_forward=1"));

        // 检查 "net.ipv4.ip_forward=1" 是否存在并且没有被注释掉
        boolean ipForwardExists = lines.stream()
                .map(String::trim)
                .anyMatch(line -> line.equals("net.ipv4.ip_forward=1"));

        // 根据条件来决定是否添加 "net.ipv4.ip_forward=1"
        if (commentedIpForwardExists || !ipForwardExists) {
            Files.write(Paths.get("/etc/sysctl.conf"), "net.ipv4.ip_forward=1\n".getBytes(), StandardOpenOption.APPEND);
        }

        // 应用 IP 转发设置
        run("sudo sysctl -p");

        //安装应用
        serverInstall();

        serverConfig.serverInit(this.serverInfo, this.serverKey);
    }

    private void serverStart() throws IOException, InterruptedException {
        run("sudo systemctl enable wg-quick@wg0");
        run("sudo systemctl start wg-quick@wg0");
    }


    // 创建ServerInfo数据类
    private ServerInfo creativeServerInfo() throws IOException, InterruptedException {
        //String server_eth, String server_ip, String server_port, String pkgMgr
        String server_eth = run("ip -o -4 route show to default | awk '{print $5}'");
        String server_ip = run("curl -s ifconfig.me");
        String server_port = "6888";
        String pkgMgr = getPackageManagerCmd();
        return new ServerInfo(server_eth, server_ip, server_port, pkgMgr);
    }

    //创建ServerKey数据类
    private ServerKey creativeServerKey() throws IOException, InterruptedException {
        // 生成 WireGuard 私钥和公钥
        run("wg genkey | sudo tee /etc/wireguard/server_private.key | wg pubkey | sudo tee /etc/wireguard/server_public.key");

        // 读取生成的私钥和公钥
        String server_private = run("sudo cat /etc/wireguard/server_private.key");
        String server_public = run("sudo cat /etc/wireguard/server_public.key");

        return new ServerKey(server_public, server_private);
    }

    private void serverInstall() throws IOException, InterruptedException {
        // 安装 WireGuard
        run("sudo " + serverInfo.pkgMgr() + " update && sudo " + serverInfo.pkgMgr() + " install -y wireguard");
        // 安装 qrencode
        run("sudo " + serverInfo.pkgMgr() + " update && sudo " + serverInfo.pkgMgr() + " install -y qrencode");


    }


    // TODO: 2023/12/3   包管理器选择识别未完成
    private String getPackageManagerCmd() throws IOException, InterruptedException {
        String osInfo = run("cat /etc/os-release");
        if (osInfo.contains("Ubuntu")) {
            return "apt";
        } else if (osInfo.contains("CentOS")) {
            return "yum";
        } else if (osInfo.contains("Fedora")) {
            return "dnf";
        } else if (osInfo.contains("openSUSE")) {
            return "zypper";
        }
        return "apt";  // 默认返回 Ubuntu 的包管理工具
    }

    public ServerInfo getServerInfo() {
        return serverInfo;
    }

    public ServerKey getServerKey() {
        return serverKey;
    }

    public ServerConfig getServerConfig() {
        return serverConfig;
    }
}
