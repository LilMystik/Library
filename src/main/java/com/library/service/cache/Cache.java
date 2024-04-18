package com.library.service.cache;

import java.util.LinkedHashMap;
import java.util.Map;
import org.springframework.stereotype.Component;

@Component
public class Cache {

  private final Map<String, Object> cacheNew = new LinkedHashMap<>();

  public void put(String title, Object object) {
    cacheNew.put(title, object);
    if (cacheNew.size() > 10) {
      String oldestKey = cacheNew.keySet().iterator().next();
      cacheNew.remove(oldestKey);
    }
  }

  public Object get(String title) {
    return cacheNew.get(title);
  }

  public void remove(String title) {
    cacheNew.remove(title);
  }

}
