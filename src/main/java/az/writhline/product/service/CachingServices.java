package az.writhline.product.service;

import az.writhline.product.model.Caching;
import az.writhline.product.repository.CachingRepository;
import az.writhline.product.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Slf4j
public class CachingServices {
    private final CachingRepository cachingRepository;
    private final CacheManager cacheManager; ;

    public Caching getCaching(Long id){
        Cache cache = cacheManager.getCache("caching");
        Caching caching = cache.get(id, Caching.class);
        if(caching == null){
            log.info("Get from DB {}", id);
            caching = cachingRepository.findById(id).orElseThrow(RuntimeException::new);
            cache.put(id, caching);
            return caching;
        } else {
            log.info("Get from Cache {}", id);
            log.info(caching.toString());
            return caching;
        }
    }
}
