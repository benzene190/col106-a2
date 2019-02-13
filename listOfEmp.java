import java.io.*;
import java.lang.String;
import java.util.*;

class Node{
	String name;
	int level;
	int key;
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
	BSTNode lchild;
	BSTNode rchild;
	Node ref;
}
public class listOfEmp {
	
	static boolean Compare(BSTNode q, BSTNode w) {
		
	}
	
	static BSTNode BSTroot;
	
	static void BSTInsert(BSTNode t) {
		
	}
	
	static Node Search(String x) {
		
	}
	
	static void Input(String x) throws FileNotFoundException, IOException, NullPointerException {
	  File file = new File(x); 
	
	  
	  BufferedReader s = new BufferedReader(new FileReader(file)); 
	  String st, s1, s2;
	  int n = Integer.parseInt(s.readLine());
	  
	  s1 = s.readLine().split(" ")[0];
	  s2 = s.readLine().split(" ")[1];
	  Node q = new Node(s2,null);
	  Node p = new Node(s1,null);
	  q.children.add(p);
	  p.parent = q;
	  BSTroot.name = s2;
	  BSTroot.ref = q;
	  BSTroot.parent = null;
	  BSTroot.lchild = null;
	  BSTroot.rchild = null;
	  
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
		};
		  
	//Space for the part to enter read and implement the queries
		  
	
		  
		  
		  
	//#########################################################
		  s.close();
	  }
	    
	}
}
