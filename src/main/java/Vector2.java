

public class Vector2 {
	public double x, y;
	
	public Vector2(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public void set(Vector2 vec) {
		this.x = vec.x;
		this.y = vec.y;
	}
	
	public Vector2 add(double x, double y) {
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2 add(Vector2 vec) {
		this.x += vec.x;
		this.y += vec.y;
		return this;
	}
	
	public void sub(double x, double y) {
		this.x -= x;
		this.y -= y;
	}
	
	public void sub(Vector2 vec) {
		this.x -= vec.x;
		this.y -= vec.y;
	}
	
	public Vector2 multiply(double scale) {
		this.x *= scale;
		this.y *= scale;
		return this;
	}
	
	
	public static Vector2 add(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x + v2.x, v1.y + v2.y);
	}
	
	public static Vector2 sub(Vector2 v1, Vector2 v2) {
		return new Vector2(v1.x - v2.x, v1.y - v2.y);
	}
	
	public double dis() {
		return Math.sqrt(x*x + y*y);
	}
	
	public double disSq() { //distance with the square root, mroe efficient but u ahve to gix
		return x*x + y*y; //because math.sqrt is very expensive
	}
	
	public Vector2 normalize() {
		if ( dis() != 0) {
			double distance = dis();
			this.x/=distance;
			this.y/=distance;
		}
		return this;
	}
	
	public Vector2 clamp(double max) {
		if ( this.disSq()>=max ) {
			this.normalize().multiply(max);
		}
		return this;
	}
	public static double dis(Vector2 v1, Vector2 v2) {
		double diffX = v1.x - v2.x;
	    double diffY = v1.y - v2.y;
	    return Math.sqrt(diffX * diffX + diffY * diffY);
	}
	
	public static double disSq(Vector2 v1, Vector2 v2) {
		double diffX = v1.x - v2.x;
	    double diffY = v1.y - v2.y;
	    return diffX * diffX + diffY * diffY;
	}
	
	public static double angleLookAt(Vector2 v1, Vector2 v2) {
		//v1 is origin, v2 is look target
	    return -Math.atan2(v2.x-v1.x, v2.y-v1.y);
	}
}
