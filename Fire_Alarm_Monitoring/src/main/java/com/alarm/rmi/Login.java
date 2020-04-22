package com.alarm.rmi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField textUsername;
	private JTextField textPassword;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Login() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 626, 406);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsername = new JLabel("Username: ");
		lblUsername.setForeground(Color.WHITE);
		lblUsername.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblUsername.setBounds(37, 110, 84, 16);
		contentPane.add(lblUsername);
		
		textUsername = new JTextField();
		textUsername.setBounds(158, 108, 189, 22);
		contentPane.add(textUsername);
		textUsername.setColumns(10);
		
		JLabel lblPassword = new JLabel("Password: ");
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblPassword.setBounds(37, 185, 84, 16);
		contentPane.add(lblPassword);
		
		textPassword = new JTextField();
		textPassword.setBounds(158, 183, 189, 22);
		contentPane.add(textPassword);
		textPassword.setColumns(10);
		
		JButton btnLogin = new JButton("LOGIN");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Registry reg = LocateRegistry.getRegistry("localhost", 1099);
					LoginFacade server = (LoginFacade) reg.lookup("rmi://localhost/service");
										
					// get user input
					String username = textUsername.getText();
					String password = textPassword.getText();
					
					String response = server.login(username, password);
					
					if (response.equals("LOGIN_SUCCESFUL")) {
						Dashboard ds = new Dashboard();
						ds.newWindow();
					}
				} catch (RemoteException | NotBoundException e) {
					System.out.println(e.getMessage());
				}
			}
		});
		btnLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 13));
		btnLogin.setForeground(new Color(0, 0, 0));
		btnLogin.setBackground(new Color(50, 205, 50));
		btnLogin.setBounds(213, 263, 97, 25);
		contentPane.add(btnLogin);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0, 204, 255));
		panel.setBounds(-3, -3, 613, 57);
		contentPane.add(panel);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 51));
		panel_1.setBounds(-3, 52, 615, 309);
		contentPane.add(panel_1);
	}
}
