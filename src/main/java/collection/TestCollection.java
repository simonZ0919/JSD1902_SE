package collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Collection: interface for arrange data
 * @author zxh
 */
public class TestCollection {
	public static void main(String[] args) {
		Collection c1=new ArrayList();
		/**
		 * user provide different types to class member
		 * default type: Object
		 */
		Point<Integer> p1=new Point<Integer>(1,1);
		Point<Double>p2=new Point<Double>(2.5, 3.6);
		// int x1=((Integer)p1.getX()).intValue(), type matched
		int x1=p1.getX();
		c1.add(p1);
		c1.add(p2);
		
		// specify String
		Collection<String>c2=new ArrayList();
		c2.add("zero");// must add String
		c2.add("point");
		/**
		 * boolean add(object): if add succeed, return true
		 * c.remove(new Point(2, 3))
		 * clear(), size(), isEmpty()
		 * contains(object): compare with equals()
		 * addAll(c2), removeAll(c2), containsAll(c2), c2->c1
		 */	
		c1.addAll(c2);
		System.out.println(c1);// print toString
		System.out.println(c1.size()+" "+c1.isEmpty());
		System.out.println(c1.contains(new Point(1, 1)));

		/**
		 * iterator(): get current iterator
		 * hasNext(): has element
		 * next(): increase iterator, return Object
		 * remove(): remove current element
		 */
		Iterator it=c1.iterator();
		Object object=null;
		boolean isIntPoint=false;
		while(it.hasNext()) {
			if(!((object=it.next()) instanceof Point)) 
				it.remove();	
		}
		
		Collection<Point> c3=c1;
		// foreach: traverse the collection with iterator
		for (Point p : c3) {
			System.out.println(p);
		}
		
	}
}

