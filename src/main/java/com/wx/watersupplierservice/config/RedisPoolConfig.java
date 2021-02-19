package com.wx.watersupplierservice.config;

import com.alibaba.fastjson.support.spring.GenericFastJsonRedisSerializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisPoolConfig;


@Configuration
@Slf4j
public class RedisPoolConfig {

    @Autowired
    private RedisConfigPoolProperties redisConfigPoolProperties;
    @Autowired
    private RedisStandaloneConfigProperties redisStandaloneConfigProperties;

//    @Autowired
//    private RedisSentinelConfigProperties redisSentinelConfigProperties;

    @Bean(name="redispoolConfig")
    public JedisPoolConfig getJedisPool(){
        log.info("getJedisPool。。。。");
        JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxIdle(redisConfigPoolProperties.getMaxIdle());
        config.setMaxTotal(redisConfigPoolProperties.getMaxTotal());
        config.setMaxWaitMillis(redisConfigPoolProperties.getMaxWaitMillis());
        config.setTestOnBorrow(redisConfigPoolProperties.getTestObBorrow());
        return config;
    }

    @Bean(name = "connectionFactory")
    public JedisConnectionFactory getJedisConnectionFactory(@Qualifier("redispoolConfig") JedisPoolConfig redisPoolConfig) {
        log.info("connectionFactory配置加载。。。。");
        RedisStandaloneConfiguration configuration = new RedisStandaloneConfiguration();
        configuration.setDatabase(redisStandaloneConfigProperties.getDatabase());
        configuration.setHostName(redisStandaloneConfigProperties.getHost());
        configuration.setPort(redisStandaloneConfigProperties.getPort());
        configuration.setPassword(RedisPassword.of(redisStandaloneConfigProperties.getPassword()));
        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder builder = (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        builder.poolConfig(redisPoolConfig);
        return new JedisConnectionFactory(configuration, builder.build());
    }



    @Bean
    public RedisTemplate<String, Object> redisTemplate(@Qualifier("connectionFactory") RedisConnectionFactory connectionFactory) {
        log.info("redisTemplate。。。。");
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<String, Object>();
        redisTemplate.setConnectionFactory(connectionFactory);
        redisTemplate.setValueSerializer(new GenericFastJsonRedisSerializer());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }
    @Bean("stringRedisTemplate")
    public StringRedisTemplate stringRedisTemplate(@Qualifier("connectionFactory") RedisConnectionFactory connectionFactory) {
        log.info("stringRedisTemplate。。。。");
        StringRedisTemplate stringRedisTemplate = new StringRedisTemplate();
        stringRedisTemplate.setConnectionFactory(connectionFactory);
        return stringRedisTemplate;
    }



    private void setSerializer(StringRedisTemplate template) {
        Jackson2JsonRedisSerializer jackson2JsonRedisSerializer = new Jackson2JsonRedisSerializer(Object.class);
        ObjectMapper om = new ObjectMapper();
        om.setVisibility(PropertyAccessor.ALL, JsonAutoDetect.Visibility.ANY);
        om.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
        jackson2JsonRedisSerializer.setObjectMapper(om);
        template.setValueSerializer(jackson2JsonRedisSerializer);
    }



    @Component
    @ConfigurationProperties(prefix = "spring.redis")
    @Data
    public class RedisStandaloneConfigProperties{

        private String password;
        private String host;
        private Integer port;
        private Integer database;
    }

    @Component
    @ConfigurationProperties(prefix = "spring.redis.jedis.pool")
    @Data
    public class RedisConfigPoolProperties{

        private Integer maxIdle;
        private Integer maxTotal;
        private Integer maxWaitMillis;
        private Boolean testObBorrow;

    }

}
