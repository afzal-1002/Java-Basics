package com.linkedlist.singly;


public class SinglyLinkedList {
	
	/**
	 * Noode
	 */
	static  class Node {
		
			Object data;
			Node next;

		public  Node(Object data)
		{
			this.data = data;
			this.next = null;
		}
		
	}

	static class LinkList {
		
		Node head;
		Node tail;

		public LinkList()
		{
			head = tail = null;
		}
		
		public  void addFirst(Object data)
		{
			Node newNode =  new Node(data);

			if(head == null)
			{
				head = tail = newNode;
				return ;
			}else
			{
				newNode.next = head;
				head=newNode;
			}

		}


		public  void addLast(Object data)// floorLast
		{
			Node newNode =  new Node(data);

			if(head == null)
			{
				head = tail = newNode;
				return ;
			}else
			{
				tail.next = newNode;
				tail=newNode;
			}

		}


		public  Node getFirst()
		{
			Node temp ;
			if(head == null)
			{
				return null;
			}else
			{
				temp = head;
				head = head.next;
				temp.next = null;
			}
			return (temp);
		}
	

		public  Node getLast()
		{
			Node temp ;
			Node lastNode;
			if(head == null)
			{
				return null;
			}else
			{
				temp = head;
				while (temp.next != tail ) {
					temp = temp.next;
				}
				temp.next = null;
				lastNode = tail;
				tail= temp;
			}
			return (lastNode);
		}


		public  void insertNode(Object data, int pos)
		{
			if(pos < 0)
			{
				return ;
			}

			if(pos == 0)
			{
				addFirst(data);
				return ;
			}else
			{
				Node temp = head;
				int i = 0;
				while (i < pos - 1 ) {
					if (temp == null) {
						return ;
					}
						temp = temp.next;
				}
				Node node = new Node(data);
				node.next = temp.next;
				temp.next = node;
			}
		}
}


	public static void main(String[] args) {
		System.out.println("Singly Link List:  ");

		LinkList linkList = new LinkList();
		linkList.addFirst(30);
		linkList.addFirst(20);
		linkList.addFirst(10);
		
		linkList.addLast(40);
		linkList.addLast(50);
		linkList.addLast(60);


		Node temp = linkList.head;
		int count = 0 ;


		while (temp != null) {
			
			System.out.println("Index position " + count + " => " +  temp.data);

			temp = temp.next;
			count++;
		}

		// Node first =  list.getFirst();

		System.out.println("getFirst Node => " + linkList.getFirst().data);
		System.out.println("getFirst Node => " + linkList.getFirst().data);
		System.out.println("getFirst Node next => " + linkList.getFirst().next);


		System.out.println("getLast Node data => " + linkList.getLast().data);


		System.out.println("Setting the Node to specific index: " );
		
		// LinkList linkListNew = new LinkList();
		// linkListNew.addFirst(30);
		// linkListNew.addFirst(20);
		// linkListNew.addFirst(10);
		
		// linkListNew.addLast(40);
		// linkListNew.addLast(50);
		// linkListNew.addLast(60);


		linkList.insertNode(100, 1);


		 temp = linkList.head;

		count = 0;

		while (temp != null) {
			
			System.out.println("After Setting node Index position " + count + " => " +  temp.data);

			temp = temp.next;
			count++;
		}





	}	
}
