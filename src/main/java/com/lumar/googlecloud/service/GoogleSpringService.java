package com.lumar.googlecloud.service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.googlecode.spring.appengine.api.factory.AsyncMemcacheServiceFactoryBean;
import com.googlecode.spring.appengine.api.factory.MemcacheServiceFactoryBean;
import com.googlecode.spring.appengine.cache.memcache.MemcacheCache;
import com.lumar.googlecloud.service.memcache.MemcacheCacheManager;
import org.springframework.cache.CacheManager;

import org.springframework.cache.Cache;

import com.google.appengine.api.memcache.AsyncMemcacheService;
import com.google.appengine.api.memcache.MemcacheService;


@Configuration
@EnableAutoConfiguration
@EnableCaching
@SpringBootApplication
public class GoogleSpringService {
	
    public static void main( String[] args ) {
    	SpringApplication.run(GoogleSpringService.class, args);
    }
    
    @Bean(name = "cacheManager")
    public CacheManager cacheManager() {
        return new MemcacheCacheManager();
    }
    
    @Bean(name = "cache")
    public Cache cache() throws Exception {
        return new MemcacheCache(memcacheService());
    }

    @Bean(name = "memcacheService")
    public MemcacheService memcacheService() throws Exception {
        return memcacheServiceFactoryBean().getObject();
    }

    @Bean(name = "memcacheServiceFactory")
    public MemcacheServiceFactoryBean memcacheServiceFactoryBean() {
        return new MemcacheServiceFactoryBean();
    }

    @Bean(name = "asyncMemcacheService")
    public AsyncMemcacheService asyncMemcacheService() throws Exception {
        return asyncMemcacheServiceFactoryBean().getObject();
    }

    @Bean(name = "asyncMemcacheServiceFactoryBean")
    public AsyncMemcacheServiceFactoryBean asyncMemcacheServiceFactoryBean() {
        return new AsyncMemcacheServiceFactoryBean();
    }
}
