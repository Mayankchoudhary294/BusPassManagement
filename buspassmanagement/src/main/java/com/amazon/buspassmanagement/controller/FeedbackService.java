package com.amazon.buspassmanagement.controller;

import java.util.List;
import java.util.Scanner;

import com.amazon.buspassmanagement.BusPassSession;
import com.amazon.buspassmanagement.db.FeedbackDAO;
import com.amazon.buspassmanagement.model.Feedback;

public class FeedbackService {

	FeedbackDAO feedbackDAO = new FeedbackDAO();

	private static FeedbackService feedbackService = new FeedbackService();
	Scanner scanner = new Scanner(System.in);
	
	public static FeedbackService getInstance() {
		return feedbackService;
	}
	
	public FeedbackService() {
	
	}
	
	public void createFeedback() {
		Feedback feedback = new Feedback();
		feedback.getDetails();
		
		feedback.userId = BusPassSession.user.id;
		feedback.raisedBy = BusPassSession.user.email;
		
		int result = feedbackDAO.insert(feedback);
		String message = (result > 0) ? "Feedback Created Successfully" : "Creating Feedback Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void deleteFeedback() {
		Feedback feedback = new Feedback();
		System.out.println("Enter Feedback ID to be deleted: ");
		feedback.id = Integer.parseInt(scanner.nextLine());
		int result = feedbackDAO.delete(feedback);
		String message = (result > 0) ? "Feedback Deleted Successfully" : "Deleting Feedback Failed. Try Again.."; 
		System.out.println(message);
	}
	
	public void viewFeedbacks() {
		List<Feedback> objects = feedbackDAO.retrieve();
		for(Feedback object : objects) {
			object.prettyPrint();
		}
	}
	
	public void viewFeedbacksByUser(int uid) {
		
		String sql = "SELECT * from Feedback where userId = "+uid;
		
		List<Feedback> objects = feedbackDAO.retrieve(sql);
		
		for(Feedback object : objects) {
			object.prettyPrint();
		}
	}
	
	public void passSuspensionRequest() {		
		String sql = "SELECT * FROM Feedback WHERE title='Pass Suspension'";
		
		List<Feedback> objects = feedbackDAO.retrieve(sql);
		
		for(Feedback object : objects) {
			object.prettyPrint();
		}
	}
	
	
}
