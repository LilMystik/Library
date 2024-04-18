package com.library.components;

import com.library.service.cache.Cache;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class CacheComponentTest {
  private Cache cacheOld;

  @BeforeEach
  void setUp(){
    cacheOld = new Cache();
  }

  @Test
  void testPutAndGet()
  {
    cacheOld.put("key1","object1");
    Object value = cacheOld.get("key1");
    assertEquals("value1",value);
  }

  @Test
  void testRemove()
  {
    cacheOld.put("key1","object1");
    cacheOld.remove("key1");
    Object value = cacheOld.get("key1");
    assertNull(value);
  }

  @Test
  void testRemoveEldestEntry()
  {
    for(int i =0;i<12;i++)
    {
      cacheOld.put("key"+i,"object"+i);
    }
    Object value = cacheOld.get("key0");
    assertNull(value);
  }

}
