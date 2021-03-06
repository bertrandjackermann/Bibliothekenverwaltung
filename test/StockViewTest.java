package test;

import javax.swing.*;

import model.Medium;

import controller.MediaHandler;
import controller.MyAccount;
import controller.UserHandler;

import view.StockView;

public class StockViewTest {

	public static void main(String[] args) {
		new StockViewTest();
	}
	
	private StockViewTest(){
		JFrame frame = new JFrame("StockViewTest");
		
//		Rolle Student
//		String loginName = "aggi";
//		String pw = "1111";
		
//		String loginName = "schnatti";
//		String pw = "4321";
		
//		Rolle Bib
		String loginName = "admin";
		String pw = "1234";
		
		MyAccount.login(loginName, pw);
		System.out.println("logged in as "+UserHandler.getInstance().getUser(loginName).getRoleAsString());
		frame.setSize(800, 600);
		
		Medium[] media = new Medium[2];
		media[0] = MediaHandler.getInstance().getMedium(2);
		media[1] = MediaHandler.getInstance().getMedium(3);
		
		JPanel panel = new StockView(media);
		frame.add(panel);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

}
