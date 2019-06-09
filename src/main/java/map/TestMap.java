package map;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;


/**
 * HashMap: fastest way of searching!
 * @author zxh
 *
 */
public class TestMap {
	
	public static void main(String[] args) {
		/**
		 * put(key,value): return null or replaced value
		 * get(key): return value
		 * size(), remove(key): delete key-value, return value
		 * LinkedHashMap: have order with put
		 */
		Map<String,Integer> map=new HashMap<String, Integer>(100);// initial the capacity of 100
		map.put("Math", 98);
		map.put("English", 96);
		map.put("Physics",99);
		System.out.println(map.put("English",90));
		System.out.println(map.remove("Physics"));
		
		/**
		 * keySet(): return set of keys
		 * entrySet(): key-value pair
		 * values(): value 
		 * containsKey(key), containsValue(value)
		 */
		Set<String> keyset=map.keySet();
		Set<Entry<String, Integer>> entryset=map.entrySet();
		Collection<Integer> value=map.values();
		System.out.println(map.containsKey("Math"));
		
	}
}
