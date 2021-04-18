class HelloA {
public HelloA() {
System.out.println("HelloA");
}

{ System.out.println("Class A"); }

static { System.out.println("Static A"); }
}
 
public class HelloB extends HelloA {
public HelloB() {
System.out.println("HelloB");
}

{  
	System.out.println("Class B");
}

static { System.out.println("Static B"); }
   
public static void main(String[] args) {
//new HelloB();
	short v1 = 18;
	Long v2 = new Long("18");
	Long v3 = new Long(18);
	Short v4 = new Short(v1);
//	System.out.println(v1 == v4);
//	System.out.println(v4.equals(v1));
//	System.out.println(v1 == v2);
	System.out.println(v3.equals(v1));
	System.out.println(v3.equals(v4));
}
}
