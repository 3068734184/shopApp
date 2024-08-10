package com.example.shopmodel.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "shop-app.jwt")
public class JwtProperties {

    private String secretKey;
    private long ttl;
    private String tokenName;

}
