package reflect;
/**
 * a reflect class for test
 * @author simon
 *
 */
public class Person {
	public void sayHello(String name) {
		System.out.println("hello "+name);
	}
	
	// ...: unknown number, data:String[]
	public void info(String name, String address) {
		System.out.println(name+" "+address);
	}
}
