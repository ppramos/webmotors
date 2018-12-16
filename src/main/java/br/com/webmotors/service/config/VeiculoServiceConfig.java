package br.com.webmotors.service.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("config.webmotors.veiculo")
public class VeiculoServiceConfig extends BaseServiceConfig {

}
