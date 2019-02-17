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
	
	static BSTNode BSTroot = new BSTNode();
	
	static Node root = new Node(null, null);
	
	static void BSTInsert(String y, Node r) {
		BSTNode t = BSTroot;
		BSTNode tt = null;
		while(t != null) {
			tt=t;
			if(y.compareTo(t.name)<0) {
				t=t.lchild;
				//System.out.println("While in BInsert");
			}
			else {
				t=t.rchild;
				//System.out.println(t.name);
				//System.out.println("While in BInsert");
			}
		}
		if(y.compareTo(tt.name)<0) {
			BSTNode nnode = new BSTNode();
			nnode.name = y;
			nnode.ref = r;
			nnode.parent = tt;
			tt.lchild = nnode;
			//System.out.println("if in BInsert");
		}
		else {
			BSTNode nnode = new BSTNode();
			nnode.name = y;
			nnode.ref = r;
			nnode.parent = tt;
			tt.rchild = nnode;
			//System.out.println("else in BInsert");
		}
	}
	
	static String nextnum(BSTNode r) {
		String nextNum = r.name;
		while(r.lchild != null) {
			nextNum = r.lchild.name;
			r = r.lchild;
		}
		return nextNum;
	}
	
	static BSTNode BDelete(BSTNode X, String Y) {
		if (X==null) {
			return X;
		}
		if(Y.compareTo(X.name)<0) {
			X.lchild = BDelete(X.lchild, Y);
		}
		else if(Y.compareTo(X.name)>0) {
			X.rchild = BDelete(X.rchild, Y);
		}
		
		else {
			if(X.lchild == null) {
				return X.rchild;
			}
			else if(X.rchild == null) {
				return X.lchild;
			}
			
			X.name = nextnum(X.rchild);
			
			X.rchild = BDelete(X.rchild, X.name);
		}
		return X;
	}
	static void BSTDelete(String y) {
		BSTroot = BDelete(BSTroot, y);
	}
	
	static Node Search(String x) {
		BSTNode t = BSTroot;
		//System.out.println(t.name);
		//System.out.println(t.rchild.name);
		while(t!=null && x.compareTo(t.name)!=0) {
			//System.out.println(t.name);
			if(x.compareTo(t.name)<0) {
				//System.out.println("While in Searchl: " + t.name);
				t=t.lchild;
				
			}
			else if(x.compareTo(t.name)>0) {
				//System.out.println("While in Searchr: " + t.name);
				t=t.rchild;
			}
		}
		
		return t.ref;
	}
	
	static void AddEmployee(String Sp, String S) {
		//try {
		Node searched = Search(S);
		Node e = new Node(Sp, searched);
		searched.children.add(e);
		e.level = searched.level + 1;
		BSTInsert(Sp,e);
		//}
		//catch(NullPointerException e) {
			//System.out.println("The Parent doesn't exist");
		//}
	}
	
	static void DeleteEmployee(String S, String Sp) throws NullPointerException{
		Node searched1 = Search(S);
		Node searched2 = Search(Sp);
		
		if(searched1==null || searched2==null) {
			throw new NullPointerException("The specified node does not exist");
		}
		else {
			Node x = searched1.children.head;
			while(x!=null) {
				x.parent=searched2;
				x=x.next;
			}
			if(searched1.children.tail==null && searched1.children.head==null) {}
			else if(searched2.children.tail==null && searched2.children.head==null) {
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
		//try{
		Node searched1 = Search(S);
		
		Node searched2 = Search(Sp);
		Node x = searched1;
		Node y = searched2;
		if(searched1.level<searched2.level) {
			for(int i=1; i<=(searched2.level-searched1.level); i++) {
				y=y.parent;
			}
		}
		else if(searched1.level>searched2.level) {
			for(int i=1; i<=(searched1.level-searched2.level); i++) {
				x=x.parent;
			}
		}
		
		if(x==y) {
			System.out.println(x.name); 
		}
		else{
			while(x.parent!=y.parent) {
				x=x.parent;
				y=y.parent;
			}
			System.out.println(x.parent.name);
		}
		//}
		//catch(NullPointerException e) {System.out.println("The given node doesn't exist");}
	}
	
	static void Input(String x) throws FileNotFoundException, IOException, NullPointerException {
	  File file = new File(x); 
	  BufferedReader s = new BufferedReader(new FileReader(file)); 
	  String st, s1, s2;
	  int n = Integer.parseInt(s.readLine())-1;
	  
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
		  String array1[]= st.split(" ");
		  s1= array1[0];
		  s2= array1[1];
		  //catch(ArrayIndexOutOfBoundsException e) {System.out.println(st);}
		 
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
		}
	  }	  
	
		int m;
	
		n = Integer.parseInt(s.readLine());
		
		for(int i=1; i<=n; i++) {
			st = s.readLine();
			try {
				m = Integer.parseInt(st.split(" ")[0]);
			}
			catch(NullPointerException e) {
				m = Integer.parseInt(st);
			}
			String array2[]= st.split(" ");
			
			if(m==0) {
				try{
					AddEmployee(array2[1], array2[2]);
				}
				catch(NullPointerException e) {}
			}
			else if(m==1) {
				try{
					DeleteEmployee(array2[1], array2[2]);
				}
				catch(NullPointerException e) {}
			}
			else if(m==2) {
				try{
					LowestCommonBoss(array2[1], array2[2]);
				}
				catch(NullPointerException a) {}
			}
			else if(m==3) {
				PrintEmployees();
			}
		}
	
		  s.close();
	  }
	    
	

	public static void main(String[] args) throws FileNotFoundException, NullPointerException, IOException {
		Input(args[0]);
	}
}
