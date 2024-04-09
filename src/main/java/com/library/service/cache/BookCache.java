package com.library.service.cache;

import com.library.model.Book;
import org.springframework.stereotype.Component;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class BookCache {
    private static final int sizeM=10;
    private final Map<String, Book> cache = new LinkedHashMap<>();
    public void put(String title,Book book)
    {
        cache.put(title,book);
        if(cache.size()>sizeM) {
            String oldestKey = cache.keySet().iterator().next();
            cache.remove(oldestKey);
        }
    }
    public Book get(String title)
    {
        return cache.get(title);
    }
    public void remove(String title)
    {
        cache.remove((title));
    }

}
