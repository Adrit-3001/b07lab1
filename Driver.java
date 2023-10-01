import java.io.File;
public class Driver {
	public static void main(String [] args) throws Exception {
		Polynomial p = new Polynomial();
		System.out.println(p.evaluate(3));
		double [] c1 = {6,5};
		int[] e1 = {0,3};
		Polynomial p1 = new Polynomial(c1,e1);
		double [] c2 = {-2,-9};
		int[] e2 = {1,4};
		Polynomial p2 = new Polynomial(c2,e2);
		Polynomial s = p1.add(p2);
		System.out.println("s(-1) = " + s.evaluate(-1));
		if(s.hasRoot(1))
		System.out.println("1 is a root of s");
		else
		System.out.println("1 is not a root of s");
		Polynomial m = p1.multiply(p2);
//		System.out.println("s(0.1) = " + m.evaluate(-1));
		if(m.evaluate(-1) == -7) {
			System.out.println("GGWP");
		}
		else {
			System.out.println("LOSER LMAO");
		}
//		System.out.println(m.exponents[2]);
		File file = new File("C:\\Users\\adrit\\OneDrive\\डेस्कटॉप\\many things\\5-3x2+7x8.txt");
//		System.out.println(file.isDirectory());
        Polynomial p3 = new Polynomial(file);
        p2.saveToFile("C:\\Users\\adrit\\OneDrive\\डेस्कटॉप\\many things\\test.txt");
//        System.out.println(p3.coefficients[1]);
	}
	
// new tests********
	/*check all exponents and coeff individually. DONE. */
	/* file tests done? YAASSSS*/

}