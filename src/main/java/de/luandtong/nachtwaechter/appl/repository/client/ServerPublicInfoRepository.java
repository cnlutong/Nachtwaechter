package de.luandtong.nachtwaechter.appl.repository.client;

import de.luandtong.nachtwaechter.domain.client.ServerPublicInfo;

public interface ServerPublicInfoRepository {
    ServerPublicInfo findServerPublicInfoByID(int serverID);
}

