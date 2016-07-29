
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class FizzBuzzTest {

	int i = 1;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test	//3と5両方の倍数の場合には"FizzBuzz"を返す
	public void testCheck1() {

		int x = i * (3 * 5);
		String actual = FizzBuzz.check(x);
		String expected = "FizzBuzz";

		assertThat(actual, is(expected));
	}

	@Test	//3の倍数の場合には"fizz"を返す
	public void testCheck2() {

		int x = i * 3;
		String actual = FizzBuzz.check(x);
		String expected = "Fizz";

		assertThat(actual, is(expected));
	}

	@Test	//5の倍数の場合には"buzz"を返す
	public void testCheck3() {

		int x = i * 5;
		String actual = FizzBuzz.check(x);
		String expected = "Buzz";

		assertThat(actual, is(expected));
	}

	@Test	//5と3の倍数ではない場合にはnullを返す
	public void testCheck4(){
		assertNull(FizzBuzz.check(8));
	}
}
