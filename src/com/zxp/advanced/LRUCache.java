package com.zxp.advanced;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//要基于LinkedHashMap来实现LRU缓存

public class LRUCache<K,V> {
	
	private static final float hashTableLoadFactor=0.75f;
	private LinkedHashMap<K,V> map;
	private int cacheSize;
	
	public LRUCache(int cacheSize){
		this.cacheSize=cacheSize;
		int hashTableCapacity=(int)Math.ceil(cacheSize/hashTableLoadFactor)+1;
		map=new LinkedHashMap<K, V>(hashTableCapacity,hashTableLoadFactor,true){
			private static final long seriaVersionUID=1;
			protected boolean removeEldestEntry(Map.Entry<K, V> eldest){
				return size()>LRUCache.this.cacheSize;
			}
		};		
	}
	public synchronized V get(K key){
		return map.get(key);
	}
	public synchronized void put(K key,V value){
		map.put(key, value);
	}
	public synchronized void clear(){
		map.clear();
	}
	public synchronized int usedEntries(){
		return map.size();
	}
	public synchronized Collection<Map.Entry<K, V>> getAll(){
		return new ArrayList<Map.Entry<K,V>>(map.entrySet());
	}
}
