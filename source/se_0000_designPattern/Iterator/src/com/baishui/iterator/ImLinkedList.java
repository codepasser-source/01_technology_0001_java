package com.baishui.iterator;

/**
 * Iterator设计模式
 * 可动态添加的 容器   链表
 */
 
class ImLinkedList implements Collection { 
	Node head = null; 
	Node tail = null;
    int  size=0; 
	public void add(Object o){ 
		 Node n = new Node(o,null);
		if(head==null){
		   head = n;
		   tail=n;
		} 
		tail.setNextNode(n);
		tail=n; 
		size++;
	}
	public int getSize(){
		return size;
	}
	@Override
	public Iterator iterator() {
		// TODO Auto-generated method stub
		return null;
	}
	
}

class Node{
  private 	Object data;
  private Node nextNode; 
  
public Node() {
	 
}

public Node(Object data,Node nextNode) {
	
	this.data = data;
     this.nextNode = nextNode;
}

public Object getData() {
	return data;
}
public void setData(Object data) {
	this.data = data;
}
public Node getNextNode() {
	return nextNode;
}
public void setNextNode(Node nextNode) {
	this.nextNode = nextNode;
}
   
}
