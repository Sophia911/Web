package com.se_backend.DB.DB_object;

import com.se_backend.DB.DB_Mapper.OfficialMailsMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class OfficialMails {
    String OfficialMail;
    String Label;
    String serverType;
    String SecretKey;
    public String getOfficialMail() {
        return OfficialMail;
    }

    public void setOfficialMail(String officialMail) {
        OfficialMail = officialMail;
    }

    public String getLabel() {
        return Label;
    }

    public void setLabel(String label) {
        Label = label;
    }

    public String getServerType() {
        return serverType;
    }

    public void setServerType(String serverType) {
        this.serverType = serverType;
    }

    public String getSecretKey() {
        return SecretKey;
    }

    public void setSecretKey(String secretKey) {
        SecretKey = secretKey;
    }
}
