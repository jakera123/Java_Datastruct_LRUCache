package com.zxp.advanced;

import java.util.Hashtable;

public class LRUCacheHashtable {
	
	private int cacheSize;
	private Hashtable<Object,Entry> nodes;  //缓存容器
	private int currentSize;
	private Entry first;
	private Entry last;
	
	public LRUCacheHashtable(int i){
		currentSize=0;
		cacheSize=i;
		nodes=new Hashtable<Object,Entry>(i);
	}
	public Entry get(Object key){
		Entry node=nodes.get(key);
		if(node!=null){
			moveToHead(node);
			return node;
		}else{
			return null;
		}	
	}
	public void put(Object key,Object value){
		Entry node=nodes.get(key);
		
		if(node==null){
			if(currentSize>=cacheSize){
				nodes.remove(last.key);
				removeLast();
			}else{
				currentSize++;
			}
			node=new Entry();
		}
		node.value=value;
		moveToHead(node);
		nodes.put(key, node);	
	}
	
	public void remove(Object key){
		Entry node=nodes.get(key);
		if(node!=null){
			if(node.prev!=null){
				node.prev.next=node.next;
			}
			if(node.next!=null){
				node.next.prev=node.prev;
			}
			if(last==node)
				last=node.prev;
			if(first==node)
				first=node.next;
		}
		nodes.remove(key);
	}
	
	private void removeLast(){
		if(last!=null){
			if(last.prev!=null)
				last.prev.next=null;
			else
				first=null;
			last=last.prev;
		}
	}
	private void moveToHead(Entry node){
		if(node==first)
			return;
		if(node.prev!=null)
			node.prev.next=node.next;
		if(node.next!=null)
			node.next.prev=node.prev;
		if(last==node)
			last=node.prev;
		if(first!=null){
			node.next=first;
			first.prev=node;
		}
		first=node;
		node.prev=null;
		if(last==null)
			last=first;
	}
	
	public void clear(){
		first=null;
		last=null;
		currentSize=0;
	}
}
class Entry{
	Entry prev;     //前一节点
	Entry next; 	//后一节点 
	Object value;	//值
	Object key;		//键
}


