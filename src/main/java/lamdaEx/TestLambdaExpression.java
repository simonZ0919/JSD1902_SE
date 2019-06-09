package lamdaEx;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * (parameters)->{body}
 * anonymous inner class: one method only
 * @author zxh
 *
 */
public class TestLambdaExpression {

	public static void main(String[] args) {
		Runnable r1=()->{
			System.out.println("running");
		};
		
		List<String> list=new ArrayList<String>();
		list.add("Hello");
		list.add("Wd");
		
		/**
		 * traverse list/Collection/Map with foreach, thread safe
		 */
//		Comparator<String> comparator=(o1,o2)->{
//			return o1.length()-o2.length();
//		};
//		Collections.sort(list, comparator);
		
		// one line: ignore {} and return
		Collections.sort(list, (o1,o2)->o1.length()-o2.length());
		
//		for (String str : list) {
//			System.out.println(str);
//		}
		list.forEach((str)->System.out.println(str));
		
		
		
	}

}
