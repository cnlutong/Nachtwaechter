package de.luandtong.nachtwaechter.datenbank.wireGuardKey;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WireGuardKey")
public class WireGuardKeyDTO {


    private final String uuid;
    private final String publicKey;
    private final String privateKey;
    @Id
    private Long id;


    public WireGuardKeyDTO(String uuid, String publicKey, String privateKey) {
        this.uuid = uuid;
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public Long getId() {
        return id;
    }

    public String getUuid() {
        return uuid;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }

}
