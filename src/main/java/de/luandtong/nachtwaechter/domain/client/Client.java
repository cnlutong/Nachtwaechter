package de.luandtong.nachtwaechter.domain.client;

import de.luandtong.nachtwaechter.domain.server.ServerKey;

import java.io.IOException;

import static de.luandtong.nachtwaechter.domain.Command.run;

public class Client {

    private final String clientName;

    public Client(int clientIndex) {
        this.clientName = "client" + clientIndex;
    }


    //创建ServerKey数据类
    private ClientKey creativeClientKey() throws IOException, InterruptedException {
        // 生成客户端私钥和公钥
        run("wg genkey | sudo tee /etc/wireguard/clients/" + this.clientName + "_private.key | wg pubkey | sudo tee /etc/wireguard/clients/" + clientName + "_public.key");
        // 读取客户端生成的私钥和公钥
        String client_private = run("sudo cat /etc/wireguard/clients/" + this.clientName + "_private.key");
        String client_public = run("sudo cat /etc/wireguard/clients/" + this.clientName + "_public.key");
        //返回这个客户端密钥
        return new ClientKey(client_public, client_private);
    }
}
