import java.util.Arrays; //******** CAN I IMPORT THIS? YUPS*********
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintStream;
import java.util.Scanner;
import java.io.BufferedWriter;
public class Polynomial{
	double[] coefficients;
	int[] exponents;
	
	public Polynomial() {
		
	}
	
	public Polynomial(double[] coefficients, int[] exponents) {
		this.coefficients = coefficients;
		this.exponents = exponents;
	}
	
	public Polynomial add(Polynomial p) {
		// im so ded. i just hope this works lmao
		// ***** All test cases done? YES...
	    int[] p1 = this.exponents;
	    int[] p2 = p.exponents;
	    Arrays.sort(p1);
	    Arrays.sort(p2);
	    
	    int count = 0;
	    int i = 0;
	    int j = 0;
	    while (i < p1.length && j < p2.length) {
	        if (p1[i] < p2[j]) {
	            count++;
	            i++;
	        } else if (p1[i] > p2[j]) {
	            count++;
	            j++;
	        } else {
	            count++;
	            i++;
	            j++;
	        }
	    }
	    count += p1.length - i;
	    count += p2.length - j;

	    int[] new_e = new int[count];
	    double[] new_c = new double[count];

	    i = 0;
	    j = 0;
	    int k = 0;

	    while (i < p1.length && j < p2.length) {
	        if (p1[i] < p2[j]) {
	            new_e[k] = p1[i];
	            new_c[k] = this.coefficients[i];
	            i++;
	        } else if (p1[i] > p2[j]) {
	            new_e[k] = p2[j];
	            new_c[k] = p.coefficients[j];
	            j++;
	        } else {
	            new_e[k] = p1[i];
	            new_c[k] = this.coefficients[i] + p.coefficients[j];
	            i++;
	            j++;
	        }
	        k++;
	    }

	    while (i < p1.length) {
	        new_e[k] = p1[i];
	        new_c[k] = this.coefficients[i];
	        i++;
	        k++;
	    }

	    while (j < p2.length) {
	        new_e[k] = p2[j];
	        new_c[k] = p.coefficients[j];
	        j++;
	        k++;
	    }
////		System.out.println(count);
//		int[] new_e = new int[count];
//		double[] new_c = new double[count];
//		int l = this.exponents.length*2;
//		int[] e = new int[l];
//		double[] c = new double[l];
//		for(int i = 0; i < l/2; i++) {
//			e[i] = p1[i];
//			c[i] = this.coefficients[i];
//			
//		}
//		for(int i = 0; i < l/2; i++) {
//			e[i + l/2]= p2[i];
//			c[i + l/2]= p.coefficients[i];
//		}
//		Arrays.sort(e);
//		Arrays.sort(c);
//		int w =0;
//		for(int i = 0; i < l; i++) {
////			System.out.println(i);
//			if(i != l - 1 && i != 0) {
//				if(e[i] != e[i+1] && e[i] != e[i-1]) {
//					new_e[i] = e[i];
//					new_c[i] = c[i];
//				}
//				else {
//					if(e[i] == e[i+1]) {
//						new_e[i] = e[i];
//						new_c[i] = c[i] + c[i + 1];
//					}
//				}
//					
//			}
//			
//		}
//		System.out.println(new_c[3]);
	    return new Polynomial(new_c, new_e);
	}
	
	public double evaluate(double val) {
		if(this.coefficients == null) return 0;
		int len = coefficients.length;
//		System.out.println(exponents.length);
		double f_val = 0.0;
		for(int i = 0; i < len; i++) {
			f_val += Math.pow(val, exponents[i])*coefficients[i];
		}
		return f_val;
	}
	public boolean hasRoot(double val) {
		if(evaluate(val) == 0.0) {
			return true;
		}
		else {
			return false;
		}
	}
	public Polynomial multiply(Polynomial p) {
		int new_e[] = new int[this.exponents.length + p.exponents.length];
		double new_c[] = new double[this.coefficients.length + p.coefficients.length];
		int count = 0;
		for(int i = 0; i < this.exponents.length; i++) {
			for(int j = 0; j < p.exponents.length; j++) {
				int temp_e = this.exponents[i];
				double temp_c = this.coefficients[i];
				temp_e += p.exponents[j];
//				new_e[count] = temp_e;
				temp_c = temp_c*p.coefficients[j];
//				new_c[count] = temp_c;
//				count++;
				boolean found = false;
	            for (int k = 0; k < count; k++) {
	                if (new_e[k] == temp_e) {
	                    new_c[k] += temp_c;
	                    found = true;
	                    break;
	                }
	            }
	            if (!found) {
	                new_e[count] = temp_e;
	                new_c[count] = temp_c;
	                count++;
	            }
//	            System.out.println(temp_c);
			}
		}
		// now we just remove all the junk empty spaces :-)
		int[] short_e = Arrays.copyOf(new_e, count);
	    double[] short_c = Arrays.copyOf(new_c, count);
//		Polynomial result = new Polynomial(short_c, short_e);
		return new Polynomial(short_c, short_e);
	}
	public Polynomial(File file) throws Exception{
//		java.util.Scanner scanner = new java.util.Scanner(file);
//        String line = scanner.nextLine();
//        scanner.close();
		BufferedReader b = new BufferedReader(new FileReader(file));
		String line = b.readLine();
//		while( line != null) {
//			System.out.println(line);
//		}
		b.close();
		line = line.replace("-", "+-");
		String[] str = line.split("[+]");
		int len = str.length;
//		System.out.println(line);
		coefficients = new double[len];
		exponents = new int[len];
		for (int i = 0; i < len; i++) {
            String smth = str[i].trim();
            String[] nums = smth.split("x");
            coefficients[i] = Double.parseDouble(nums[0]);
//            String coefficientStr = parts[0].trim();
//            if (coefficientStr.startsWith("-")) {
//            	System.out.println("*****");
//                // Remove the '-' sign and parse the rest as a negative number
//                coefficientStr = coefficientStr.substring(1);
//                coefficients[i] = -Double.parseDouble(coefficientStr);
//            } else {
//                coefficients[i] = Double.parseDouble(coefficientStr);
//            }
//            int l = smth.length();
//            System.out.println(l);
//            for(int j = 0; j<l; j++) {
//            	
//            }
            if (nums.length > 1) {
                exponents[i] = Integer.parseInt(nums[1]);
            }
            else {
                exponents[i] = 0;
            }
//            System.out.println("Term: " + smth);
//            System.out.println("Coefficient: " + coefficients[i]);
//            System.out.println("Exponent: " + exponents[i]);
        }
		return;
	}
	public void saveToFile(String Name) throws Exception{
		BufferedWriter type = new BufferedWriter(new FileWriter(Name));
		int l = coefficients.length;
        for (int i = 0; i < l; i++) {
            if (i > 0) {
                if (coefficients[i] >= 0) {
//                	System.out.println("******");
                    type.write("+");
                }
            }
            type.write(Double.toString(coefficients[i]));
//            System.out.println("^^^^^^^");
            if (exponents[i] != 0) {
//            	System.out.println("^^^^^^^");
                type.write("x");
                type.write(Integer.toString(exponents[i]));
            }
        }
        type.close();
        return;
	}
}