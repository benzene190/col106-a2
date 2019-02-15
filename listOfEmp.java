import java.io.*;
import java.lang.String;
import java.util.*;

class Node{
	String name;
	int level;
	Node parent;
	LinkedList<Node> children = new LinkedList<Node>();
	
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
public class listOfEmp {
	
	static BSTNode BSTroot;
	
	
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
	  q.children.add(p);
	  p.parent = q;
	  
	  BSTroot.name = s2;
	  BSTroot.ref = q;
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
			  r.parent=searched;
			  r.level = searched.level + 1;
			  BSTInsert(s1, r);
		};
		  
	//Space for the part to enter read and implement the queries
		  
	
		  
		  
		  
	//#########################################################
		  s.close();
	  }
	    
	}
}
