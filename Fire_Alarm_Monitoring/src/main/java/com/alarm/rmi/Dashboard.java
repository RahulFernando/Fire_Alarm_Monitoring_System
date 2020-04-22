package com.alarm.rmi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.swing.JButton;
import javax.swing.UIManager;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.awt.event.ActionEvent;

public class Dashboard {

	private JFrame frame;
	private JTextField textFloorNo;
	private JTextField textRoomNo;
	private JButton btnNewLocation;
	private JPanel panel;

	/**
	 * Launch the application.
	 */
	public static void newWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Dashboard window = new Dashboard();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Dashboard() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setForeground(new Color(255, 255, 255));
		frame.setBounds(100, 100, 598, 429);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblFloorNumber = new JLabel("Floor number: ");
		lblFloorNumber.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblFloorNumber.setBounds(32, 28, 86, 23);
		frame.getContentPane().add(lblFloorNumber);
		
		textFloorNo = new JTextField();
		textFloorNo.setBounds(158, 28, 189, 22);
		frame.getContentPane().add(textFloorNo);
		textFloorNo.setColumns(10);
		
		JLabel lblNewLabel = new JLabel("Room number: ");
		lblNewLabel.setFont(new Font("Tw Cen MT Condensed Extra Bold", Font.PLAIN, 15));
		lblNewLabel.setBounds(32, 89, 86, 16);
		frame.getContentPane().add(lblNewLabel);
		
		textRoomNo = new JTextField();
		textRoomNo.setBounds(158, 86, 189, 22);
		frame.getContentPane().add(textRoomNo);
		textRoomNo.setColumns(10);
		
		btnNewLocation = new JButton("+");
		btnNewLocation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					Registry reg = LocateRegistry.getRegistry("localhost", 1099);
					AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
					
					// get use input
					int floor_no = Integer.parseInt(textFloorNo.getText());
					int room_no = Integer.parseInt(textRoomNo.getText());
					
					server.setSensor(floor_no, room_no);
					
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewLocation.setBackground(new Color(0, 128, 0));
		btnNewLocation.setBounds(452, 85, 67, 25);
		frame.getContentPane().add(btnNewLocation);
		
		panel = new JPanel();
		panel.setBackground(UIManager.getColor("Button.shadow"));
		panel.setBounds(0, 0, 580, 140);
		frame.getContentPane().add(panel);
	}
}
