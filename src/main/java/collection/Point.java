package collection;

/**
 * @author zxh
 * @param <E>: generics, type undefined, E must inherit number
 * implement Comparable for sort
 */
public class Point<E extends Number> 
		implements Comparable<Point<Number>>{
	private E x,y;
	
	public Point(E x, E y) {
		super();
		this.x = x;
		this.y = y;
	}

	public E getX() {
		return x;
	}

	public void setX(E x) {
		this.x = x;
	}

	public E getY() {
		return y;
	}

	public void setY(E y) {
		this.y = y;
	}

	@Override
	public String toString() {
		return "("+x+","+y+")";
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj==null)
			return false;
		if (obj==this)
			return true;
		if (obj instanceof Point) {
			Point point=(Point)obj;
			return this.x==point.x && this.y==point.y;
		}
		return false;
	}
	
	@Override
	// return>0, this>o; return <0, this<o, 0:this=o
	// compare the distance to zero 
	public int compareTo(Point<Number> o) {
		double x1=this.x.doubleValue();
		double y1=this.y.doubleValue();
		double x2=o.x.doubleValue();
		double y2=o.y.doubleValue();
		return ((int)((x1*x1+y1*y1)-(x2*x2+y2*y2)));
	}
}
