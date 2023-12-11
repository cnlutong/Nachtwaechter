package de.luandtong.nachtwaechter.domain.client;

import de.luandtong.nachtwaechter.domain.server.ServerInfo;

import java.io.IOException;

import static de.luandtong.nachtwaechter.domain.Command.run;

public class Client {

    private ClientInfo clientInfo;
    private ClientKey clientKey;
    private ClientConfig clientConfig;

    private ServerPublicInfo serverPublicInfo;

    public Client(ClientInfo clientInfo, ClientKey clientKey, ServerPublicInfo serverPublicInfo) {


    }


    private ClientInfo creativeClientInfo(int clientIndex) {
        return new ClientInfo(clientIndex, "client" + clientIndex, "10.10.0." + (clientIndex + 2) + "/24");
    }

    //创建ServerKey数据类
    private ClientKey creativeClientKey() throws IOException, InterruptedException {
        // 生成客户端私钥和公钥
        run("wg genkey | sudo tee /etc/wireguard/clients/" + this.clientInfo.clientName()
                + "_private.key | wg pubkey | sudo tee /etc/wireguard/clients/"
                + this.clientInfo.clientName() + "_public.key");
        // 读取客户端生成的私钥和公钥
        String client_private = run("sudo cat /etc/wireguard/clients/" + this.clientInfo.clientName() + "_private.key");
        String client_public = run("sudo cat /etc/wireguard/clients/" + this.clientInfo.clientName() + "_public.key");
        //返回这个客户端密钥
        return new ClientKey(client_public, client_private);
    }

    public ClientInfo getClientInfo() {
        return clientInfo;
    }

    public ClientKey getClientKey() {
        return clientKey;
    }

    public ClientConfig getClientConfig() {
        return clientConfig;
    }

    public ServerPublicInfo getServerPublicInfo() {
        return serverPublicInfo;
    }
}
