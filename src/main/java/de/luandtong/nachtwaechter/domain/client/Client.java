package de.luandtong.nachtwaechter.domain.client;

import de.luandtong.nachtwaechter.domain.WireGuardKey;

import java.io.IOException;

import static de.luandtong.nachtwaechter.domain.Command.run;

public class Client {

    private ClientInfo clientInfo;
    private WireGuardKey wireGuardKey;
    private ClientConfig clientConfig;

    private ServerPublicInfo serverPublicInfo;

    public Client(ClientInfo clientInfo, WireGuardKey wireGuardKey, ServerPublicInfo serverPublicInfo) {


    }


    private ClientInfo creativeClientInfo(int clientIndex) {
        return new ClientInfo(clientIndex, "client" + clientIndex, "10.10.0." + (clientIndex + 2) + "/24");
    }

    //创建ServerKey数据类
    private WireGuardKey creativeClientKey() throws IOException, InterruptedException {
        // 生成客户端私钥和公钥
        run("wg genkey | sudo tee /etc/wireguard/clients/" + this.clientInfo.clientName()
                + "_private.key | wg pubkey | sudo tee /etc/wireguard/clients/"
                + this.clientInfo.clientName() + "_public.key");
        // 读取客户端生成的私钥和公钥
        String client_private = run("sudo cat /etc/wireguard/clients/" + this.clientInfo.clientName() + "_private.key");
        String client_public = run("sudo cat /etc/wireguard/clients/" + this.clientInfo.clientName() + "_public.key");
        //返回这个客户端密钥
        return new WireGuardKey(client_public, client_private);
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public WireGuardKey getWireGuardKey() {
        return wireGuardKey;
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public ServerPublicInfo getServerPublicInfo() {
        return serverPublicInfo;
    }
}
