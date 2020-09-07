import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.revature.models.LoginDTO;
import com.revature.services.LoginService;


public class LoginTest {
	
	public static LoginService ls;
	public static LoginDTO testAccount;
	
	@BeforeClass
	public static void setAccount() {
		ls = new LoginService();
		testAccount = new LoginDTO();
	}
	
	@Before
	public void setTestAccount() {
		testAccount.username = "mabelle33";
		testAccount.password = "root";
	}
	
	@Test
	public void testRealLogin() {
		System.out.println("Testing login with authorized credentials.");
		System.out.println("username: " + testAccount.username);
		System.out.println("password: " + testAccount.password);
		
		assertTrue(ls.login(testAccount));
		
	}
	
	@Test
	public void testFakeLogin() {
		testAccount.username = "hacker";
		System.out.println("Testing login with unauthorized credentials.");
		System.out.println("username: " + testAccount.username);
		System.out.println("password: " + testAccount.password);
		
		assertFalse(ls.login(testAccount));
		
		
		
	}

}
