package com.mcfadyen.challenge.config;

import com.mcfadyen.challenge.model.Product;
import lombok.extern.slf4j.Slf4j;
import com.mcfadyen.challenge.repository.ProductRepository;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;

@Configuration
@Slf4j
public class LoadDatabase {
    @Bean
    CommandLineRunner initDatabase(ProductRepository repository) {

        Product iphoneXS = new Product();
        iphoneXS.setName("Apple IPhone XS");
        iphoneXS.setPrice(new BigDecimal(999));
        iphoneXS.setImage("https://www.apple.com/v/iphone/compare/k/images/overview/all_models_iphoneXS_large.jpg");

        Product iphoneXSMax = new Product();
        iphoneXSMax.setName("Apple IPhone XS Max");
        iphoneXSMax.setPrice(new BigDecimal(1299));
        iphoneXSMax.setImage("https://www.apple.com/v/iphone/compare/k/images/overview/all_models_iphoneXSmax_large.jpg");

        Product iphoneXr = new Product();
        iphoneXr.setName("Apple IPhone XR");
        iphoneXr.setPrice(new BigDecimal(799));
        iphoneXr.setImage("https://www.apple.com/v/iphone/compare/k/images/overview/all_models_iphoneXR_large.jpg");

        repository.save(iphoneXS);
        repository.save(iphoneXSMax);
        repository.save(iphoneXr);

        return args -> {
            log.info("Preloading data ok");
        };
    }
}
