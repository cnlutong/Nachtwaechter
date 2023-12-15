package de.luandtong.nachtwaechter.datenbank.wireGuardKey;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("WireGuardKey")
public class WireGuardKeyDTO {


    @Id
    private Long id;
    private final String publicKey;
    private final String privateKey;

    public WireGuardKeyDTO(String publicKey, String privateKey) {
        this.publicKey = publicKey;
        this.privateKey = privateKey;
    }

    public Long getId() {
        return id;
    }

    public String getPublicKey() {
        return publicKey;
    }

    public String getPrivateKey() {
        return privateKey;
    }
}
