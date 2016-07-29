
public class FizzBuzz {

	public static void main(String[] args) {
		fizzBuzz();
	}

	public static void fizzBuzz() {

		for(int i = 1 ; i < 100; i++){
			if(check(i) == null){
				System.out.println(i);
			} else {
				System.out.println(check(i));
			}
		}
	}

	static String check(int i) {

		if(i % 3 == 0 && i % 5 == 0) {
//			System.out.println("FizzBuzz");
			return "FizzBuzz";
		} else if(i % 3 == 0) {
//			System.out.println("Fizz");
			return "Fizz";
		} else if(i % 5 == 0 ){
//			System.out.println("Buzz");
			return "Buzz";
		}
		return null;
	}
}