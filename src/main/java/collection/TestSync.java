package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.TimeUnit;

public class TestSync {

	public static void main(String[] args) {
		List<Integer> list=new ArrayList<Integer>();
		for (int i = 0; i < 6; i++) {
			list.add(i);
		}
		
		// convert to synchronized
		list=Collections.synchronizedList(list);
		Set<Integer> set=new HashSet<Integer>(list);//construct from list
		System.out.println(list);
		
		// BlockingQueue: thread safe, efficient
		BlockingDeque<String> queue=new LinkedBlockingDeque<String>();
		// set blocking time for offer
		try {
			queue.offer("one", 500, TimeUnit.MILLISECONDS);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}

}
