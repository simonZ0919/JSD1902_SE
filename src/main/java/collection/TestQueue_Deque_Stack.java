package collection;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Queue: first-in-first-out
 * @author simon
 *
 */
public class TestQueue_Deque_Stack {
	public static void main(String[] args) {
		Queue<String> queue=new LinkedList<String>();
		/**
		 * offer(): add()
		 * peek(): first element
		 * poll(): pop up 
		 */
		queue.offer("one");
		queue.offer("two");
		queue.offer("three");
		
		System.out.println(queue);
		System.out.println(queue.peek());

		for (String string : queue) {
			System.out.println(string);
		}
		
		// traverse the queue and pop up element
//		while (queue.size()>0) {
//			System.out.println(queue.poll());
//		}
//		System.out.println(queue);
		
		/**
		 * offer()/offerLast, offerFirst/push
		 * poll()/offerFirst, pollLast/pop
		 * peek/peekfirst(), peekLast
		 */		
		Deque<String> stack=new LinkedList<String>();
		stack.push("one");
		stack.push("two");
		stack.push("three");
		System.out.println(stack);
		System.out.println(stack.pop());
		

	}

}
