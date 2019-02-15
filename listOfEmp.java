import java.io.*;
import java.lang.String;
import java.util.*;

class Node{
	String name;
	int level;
	Node parent;
	Node next=null;
	Node prev=null;
	lList children = new lList();
	
	Node(String a, Node p){
		this.name=a;
		this.parent=p;
	}
}

class BSTNode{
	String name;
	BSTNode parent;
	BSTNode lchild=null;
	BSTNode rchild=null;
	Node ref;
}

class lList{
	Node head;
	Node tail;
	
	void add(Node X) {
		if(head==null && tail==null) {
			head = X;
			tail = X;
		}
		else {
			tail.next = X;
			X.prev = tail;
			tail = X;
		}
	}
	
	void remove(Node X) {
		if(head==X && tail==X) {
			head=null;
			tail=null;
		}
		else if(tail==X) {
			X.prev.next=null;
			tail = X.prev;
		}
		else if(head==X) {
			X.next.prev = null;
			head = X.next;
		}
		else {
			X.prev.next = X.next;
			X.next.prev = X.prev;
		}
		
	}
}

public class listOfEmp {
	
	static BSTNode BSTroot;
	
	static Node root;
	
	static void BSTInsert(String y, Node r) {
		BSTNode t = BSTroot;
		while(t.lchild != null && t.rchild != null) {
			if(y.compareTo(t.name)<0) {
				t=t.lchild;
			}
			else if(y.compareTo(t.name)>0) {
				t=t.rchild;
			}
		}
		if(y.compareTo(t.name)<0) {
			BSTNode nnode = new BSTNode();
			t.lchild = nnode;
			nnode.name = y;
			nnode.ref = r;
			nnode.parent = t;
		}
		else {
			BSTNode nnode = new BSTNode();
			t.rchild = nnode;
			nnode.name = y;
			nnode.ref = r;
			nnode.parent = t;
		}
	}
	
	static void BSTDelete(String y) {
		BSTNode t = BSTroot;
		int l=-1;
		while(t.name!=y && t!=null) {
			if(y.compareTo(t.name)<0) {
				t=t.lchild;
				l=1;
			}
			else if(y.compareTo(t.name)>0) {
				t=t.rchild;
				l=0;
			}
		}
		
		if(t.lchild==null && t.rchild==null) {
			if(l==1) {
				t.parent.lchild=null;
			}
			else if(l==0) {
				t.parent.rchild=null;
			}
		}
		else if(t.lchild==null) {
			t=t.rchild;
			t.rchild=null;
		}
		else if(t.rchild==null ) {
			t=t.lchild;
			t.lchild=null;
		}
		else {
			BSTNode min=t.rchild;
			while(min.lchild!=null) {
				min=min.lchild;
			}
			t.name=min.name;
			t.lchild=min.lchild;
			t.rchild=min.rchild;
			t.parent=min.parent;
			t.ref=min.ref;
			min.parent.lchild=null;
			
		}
	}
	
	static Node Search(String x) {
		BSTNode t = BSTroot;
		while(t.name!=x && t!=null) {
			if(x.compareTo(t.name)<0) {
				t=t.lchild;
			}
			else if(x.compareTo(t.name)>0) {
				t=t.rchild;
			}
		}
		return t.ref;
	}
	
	static void AddEmployee(String S, String Sp) throws NullPointerException{
		Node searched = Search(S);
		
		if(searched==null) {
			throw new NullPointerException("The parent doesn't exist");
		}
		else {
			Node e = new Node(Sp, searched);
			searched.children.add(e);
			e.level = searched.level + 1;
			BSTInsert(Sp,e);
		}
	}
	
	static void DeleteEmployee(String S, String Sp) throws NullPointerException{
		Node searched1 = Search(S);
		Node searched2 = Search(Sp);
		
		if(searched1==null || searched2==null) {
			throw new NullPointerException("The specified node does not exist");
		}
		else {
			if(searched2.children.tail==null && searched2.children.head==null) {
				searched2.children.head=searched1.children.head;
				searched2.children.tail=searched1.children.tail;
			}
			else {
				searched2.children.tail.next = searched1.children.head;
				searched1.children.head.prev = searched2.children.tail;
				searched2.children.tail = searched1.children.tail;
			}
			searched1.parent.children.remove(searched1);
			BSTDelete(S);
		}
	}
	
	static void PrintEmployees() {
		Queue<Node> q = new LinkedList<Node>();
		q.add(root);
		while(!q.isEmpty()) {
			Node t = q.poll();
			System.out.println(t.name + " ");
			Node x = t.children.head;
			while(x!=null) {
				q.add(x);
				x=x.next;
			}
		}
	}
	
	static void LowestCommonBoss(String S, String Sp) {
		Node searched1 = Search(S);
		Node searched2 = Search(Sp);
		
	}
	
	static void Input(String x) throws FileNotFoundException, IOException, NullPointerException {
	  File file = new File(x); 
	
	  
	  BufferedReader s = new BufferedReader(new FileReader(file)); 
	  String st, s1, s2;
	  int n = Integer.parseInt(s.readLine());
	  
	  st = s.readLine();
	  s1 = st.split(" ")[0];
	  s2 = st.split(" ")[1];
	  
	  Node q = new Node(s2,null);
	  Node p = new Node(s1,null);
	  q.level = 0;
	  q.children.add(p);
	  p.parent = q;
	  
	  BSTroot.name = s2;
	  BSTroot.ref = q;
	  root=q;
	  BSTroot.parent = null;
	  BSTInsert(s1, p);
	  
	  for(int i=2; i<=n; i++){
		  st=s.readLine();
		  s1= st.split(" ")[0];
		  s2= st.split(" ")[1];
		  Node r = new Node(s1,null);
		  
		 
		  Node searched = Search(s2);
		  if(searched==null) {
			  throw new NullPointerException("No employee with the given name exists");
		  }
		  
		  else {
			  searched.children.add(r);
			  r.parent = searched;
			  r.level = searched.level + 1;
			  BSTInsert(s1, r);
		};
		  
	//Space for the part to enter read and implement the queries
		  
	
		  
		  
		  
	//#########################################################
		  s.close();
	  }
	    
	}
}
