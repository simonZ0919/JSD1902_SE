package collection;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class TestList {
	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		// add to list
		for (int i = 0; i <10; i++) {
			list.add(i);
		}
		/**
		 * get(index): access element
		 * set(index,e):replace with e, return old element
		 * add(index,e), remove(index)(return old)
		 * subList(start,end): list[start, end)
		 */
		List<Integer> sub=list.subList(3, 8);
		// multiply element by 10
		for (int i = 0; i < sub.size(); i++) {
			sub.set(i, sub.get(i)*10);
		}
		// exchange first and last element
		
		// alternative code
//		for (Integer i : subList) {
//			i*=10;
//		}
		sub.add(0, 3);
		System.out.println(sub.remove(5)+" "+sub);
		//change sublist affect old list
		
		/**
		 *  toArray: list convert to array, provide a type
		 *  Arrays.asList(arr): array to list
		 */
		Integer[] arr=list.toArray(new Integer[list.size()]);
		list=Arrays.asList(arr);
		
		// change list will affect array
		// exchange first and last element
		list.set(0,list.set(list.size()-1, list.get(0)));	
		//list.subList(3, 8).clear(); // change size will cause error
		// create new list instead
		List<Integer> list1=new ArrayList<Integer>(list);// add list1 to list2
		System.out.println(Arrays.toString(arr));
		
		// Collection.sort(list): sort 
		List<Point<Number>> pList=new ArrayList<Point<Number>>();
		pList.add(new Point<Number>(3, 9));
		pList.add(new Point<Number>(2.5, 3.6));
		pList.add(new Point<Number>(2, 1));
		//Collections.sort(pList);
		
		//preferred way: sort(list, new comparator(){};)
		Collections.sort(pList, new Comparator<Point<Number>>() {
			@Override
			public int compare(Point<Number> o1, Point<Number> o2) {
				double x1=o1.getX().doubleValue();
				double y1=o1.getY().doubleValue();
				double x2=o2.getX().doubleValue();
				double y2=o2.getY().doubleValue();
				return ((int)((x1*x1+y1*y1)-(x2*x2+y2*y2)));
			}
		});
		
		System.out.println(pList);
	}
}
