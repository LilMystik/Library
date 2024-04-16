package com.library.service.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Cache {

  private final Map<String, Object> cache = new LinkedHashMap<>();

  public void put(String title, Object object) {
    cache.put(title, object);
    if (cache.size() > 10) {
      String oldestKey = cache.keySet().iterator().next();
      cache.remove(oldestKey);
    }
  }

  public Object get(String title) {
    return cache.get(title);
  }

  public void remove(String title) {
    cache.remove((title));
  }

}
