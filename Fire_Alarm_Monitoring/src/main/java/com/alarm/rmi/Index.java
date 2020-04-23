package com.alarm.rmi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import com.alarm.com.location.Location;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import aj.org.objectweb.asm.Type;

public class Index {

	private JFrame frame;
	private JTable table;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Index window = new Index();
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
	public Index() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 585, 476);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnSignIn = new JButton("");
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Login lg = new Login();
				lg.newLogin();
			}
		});
		btnSignIn.setBackground(SystemColor.menu);
		btnSignIn.setIcon(new ImageIcon(Index.class.getResource("/com/alarm/rmi/icons8-login-64.png")));
		btnSignIn.setBounds(468, 13, 75, 73);
		frame.getContentPane().add(btnSignIn);
		
		table = new JTable();
		table.setBounds(49, 190, 459, 191);
		frame.getContentPane().add(table);
		
		String[] columnNames = {"#", "Floor No", "Room No", "CO2", "SmokeLvl"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
			Gson gson = new Gson();
			

						
			Location[] location = gson.fromJson(server.getLocation(), Location[].class);
			
			for(int i=0; i < location.length; i++) {
				Vector<Integer> row = new Vector<Integer>();
				row.add(location[i].getId());
				row.add(location[i].getFloor_no());
				row.add(location[i].getRoom_no());
				row.add(location[i].getCo2());
				row.add(location[i].getSmokeLvl());
				model.addRow(row);
			}
			
			table.setModel(model);
			
		} catch (RemoteException | NotBoundException  e) {
			e.printStackTrace();
		}
	}
}
