public class Singleton {

	private static Singleton instance = new Singleton();
	
	public Singleton() {} //empty constructor

	public static Singleton getInstance() {
		return instance;
	}
}