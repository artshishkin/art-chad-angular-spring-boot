package net.shyshkin.study.fullstack.ecommerce.config;

import org.redisson.api.RedissonClient;
import org.redisson.spring.cache.RedissonSpringCacheManager;
import org.redisson.spring.starter.RedissonAutoConfiguration;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Profile;

@Configuration
@EnableCaching
@Profile("!no_cache")
@Import({RedissonAutoConfiguration.class})
public class RedissonCacheConfig {

    @Bean
    public CacheManager cacheManager(RedissonClient client) {
        return new RedissonSpringCacheManager(client);
    }
}
