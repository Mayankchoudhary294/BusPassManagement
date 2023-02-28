package com.amazon.buspassmanagement;

import org.junit.Test;
import org.junit.Assert;
import com.amazon.buspassmanagement.controller.AuthenticationService;
import com.amazon.buspassmanagement.model.User;

// Reference Link to Use JUnit as Testing Tool in your Project
// https://maven.apache.org/surefire/maven-surefire-plugin/examples/junit.html

public class AppTest {
    
	AuthenticationService authService = AuthenticationService.getInstance();
	
	// UNIT TESTS Both test cases passed.
	
	@Test
	public void testUserLogin() {
		
		User user = new User();
		user.email = "fionna@example.com";
//		user.password = "sia123";             This will fail the test case
		user.password = "fionna123";
		
		boolean result = authService.loginUser(user);
		
		// Assertion -> Either Test Cases Passes or It will Fail :)
		Assert.assertEquals(true, result);
		Assert.assertEquals(2, user.type);
		
	}
	
	@Test
	public void testAdminLogin() {
		
		User user = new User();
		user.email = "john@example.com";
		user.password = "admin123";
		
		boolean result = authService.loginUser(user);
		
		// Assertion -> Either Test Cases Passes or It will Fail :)
		Assert.assertEquals(true, result);
		Assert.assertEquals(1, user.type); // 1 should be equal to 1
		
	}
	
}
