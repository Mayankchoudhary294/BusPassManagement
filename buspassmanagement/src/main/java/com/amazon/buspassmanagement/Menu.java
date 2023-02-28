package com.amazon.buspassmanagement;

import java.util.Scanner;

import com.amazon.buspassmanagement.controller.AuthenticationService;
import com.amazon.buspassmanagement.controller.BusPassService;
import com.amazon.buspassmanagement.controller.FeedbackService;
import com.amazon.buspassmanagement.controller.RouteService;
import com.amazon.buspassmanagement.db.DB;

public class Menu {

	AuthenticationService auth = AuthenticationService.getInstance(); 
	RouteService routeService = RouteService.getInstance();
	BusPassService passService = BusPassService.getInstance();
	FeedbackService feedbackService = FeedbackService.getInstance();
	
	Scanner scanner = new Scanner(System.in);
	
	void showMainMenu() {
		
        while(true) {
        	try {
	        	System.out.println("1: Admin");
	        	System.out.println("2: User");
	        	System.out.println("3: Quit");
	        	
	        	System.out.println("Select an Option");
	        	int choice = Integer.parseInt(scanner.nextLine());
	        	
	        	if (choice == 3) {
	        		System.out.println("Thank You For using Bus Pass Management App");
	        		
	        		DB db = DB.getInstance();
	        		db.closeConnection();
	        		scanner.close();
	        		break;
	        	}
	        	
	        	try {
	        		MenuFactory.getMenu(choice).showMenu();
				} catch (Exception e) {
					System.err.println("[Menu] [Exception] Invalid Choice...");
				}
        	
        	}catch (Exception e) {
				System.err.println("Invalid Input...");
			}
        }
	}
	
	
	public void showMenu() {
		System.out.println("Showing the Menu...");
	}
	
}
