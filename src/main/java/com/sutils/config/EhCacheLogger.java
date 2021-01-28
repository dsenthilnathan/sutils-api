package com.sutils.config;


/**
 * 
 * @author Senthilnathan
 * 
 * This is the EhCacheLogger and this logger will get notified for all the Cache Events. We can control this in ehache.xml
 * 
 */


import org.ehcache.event.CacheEvent;
import org.ehcache.event.CacheEventListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;



public class EhCacheLogger implements CacheEventListener<Object, Object> {

	  private final Logger logger = LoggerFactory.getLogger(EhCacheLogger.class);

	  @Override
	  public void onEvent(CacheEvent<?, ?> cacheEvent) {
	    logger.info("Key: {} | EventType: {} | Old value: {} | New value: {}",
	   cacheEvent.getKey() ,cacheEvent.getType() ,cacheEvent.getOldValue(),
	             cacheEvent.getNewValue());
	  }

	}