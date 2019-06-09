package wrapperClass;
/*
 * Object: super class of any type
 */
public class TestObjectWrapperClass {
	public static void main(String[] args) {
		Point point1=new Point(3, 9);
		System.out.println(point1.toString());
// println(Obj) print the result of Obj.toString
		System.out.println(point1);
		Point point2=new Point(3, 9);
// ==: the same object, equals: same content
		System.out.println(point1==point2);
		System.out.println(point1.equals(point2));
/*
 * Wrapper class, basic type to object
 * valueOf(int/double..): convert to wrapper class
 * intValue(), doubleValue(): get int, double
 */
		int d=6;
		Integer i1=Integer.valueOf(d);
		Integer i2=Integer.valueOf(d);
		System.out.println(i1==i2);// reuse the object
		System.out.println(i1.equals(i2));
		System.out.println(i1.doubleValue());
		System.out.println(Integer.MIN_VALUE);// Mininum value of int 
		/*
		 * Type.parseType(str): String to basic type, must match type
		 */
		String str="5.98";
		double ds=Double.parseDouble(str);
		System.out.println(ds);
		/*
		 * compiler will add coder, autoboxing/unboxing 
		 */
		int s=new Integer(1);// int s=new Integer(1).intValue
		Integer si=s;// Integer sInteger=Integer.valueOf(s)
	}
}

 class Point{
	private int x,y;
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Point(int x, int y) {
		super();
		this.x = x;
		this.y = y;
	}
	/*
	 * toString(): print handle(address), override method of Object 
	 */
	public String toString() {
		return "("+x+","+y+")";
	}
	/*
	 * equals():==, override to compare content
	 */
	public boolean equals(Object obj) {
		if (obj==null)
			return false;
		if (obj==this)
			return true;
		if (obj instanceof  Point) {
			Point p=(Point)obj;
			return this.x==p.x &&this.y==p.y;
		}
		return false;
	}
}