package com.zxp.advanced;

import java.util.Map;

public class TestLruCache {
	public static void main(String[] args){
		LRUCache<String,String> c=new LRUCache<String, String>(3);
		c.put("1", "one");     //1
		c.put("2", "two");     //2  1
		c.put("3", "three");   //3  2   1
		c.put("4", "four");    //4  3   2
		if(c.get("2")==null) throw new Error();// 2  4 3
		c.put("5","five");    //5   2   4
		c.put("4", "second four");  //4  5  2
		if(c.usedEntries()!=3)   throw new Error();
		if(!c.get("4").equals("second four")) throw new Error();
		if(!c.get("5").equals("five")) throw new Error();
		if(!c.get("2").equals("two")) throw new Error();
		for(Map.Entry<String, String> e:c.getAll())
			System.out.println(e.getKey()+":"+e.getValue());
	}
}
