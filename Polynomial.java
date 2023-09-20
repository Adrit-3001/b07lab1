public class Polynomial{
	double[] coefficients;
	
	public Polynomial() {
		
	}
	
	public Polynomial(double[] coefficients) {
		this.coefficients = coefficients;
	}
	
	public Polynomial add(Polynomial p) {
		// first to get max length, then add these using for loop is the idea. also i need to make new poly coef.
		int max_length = Math.max(this.coefficients.length, p.coefficients.length);
		double[] new_poly = new double[max_length];
		for(int i = 0; i < max_length; i++) {
			new_poly[i] = 0;
			if(i < this.coefficients.length && i < p.coefficients.length) {
				new_poly[i] = this.coefficients[i] + p.coefficients[i];
			}
			else if(i >= this.coefficients.length) {
				new_poly[i] += p.coefficients[i];
			}
			else {
				new_poly[i] += this.coefficients[i];
			}
		}
//		Polynomial ans = new Polynomial(new_poly);
		return new Polynomial(new_poly);
	}
	
	public double evaluate(double val) {
		// ezpz function
		if(this.coefficients == null) return 0;
		int len = coefficients.length;
		double f_val = 0.0;
		for(int i = 0; i < len; i++) {
			f_val += Math.pow(val, i)*coefficients[i];
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
}