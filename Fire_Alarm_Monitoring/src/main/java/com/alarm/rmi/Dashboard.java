package com.alarm.rmi;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.swing.JButton;
import javax.swing.UIManager;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableModel;

import org.json.simple.JSONObject;

import com.alarm.com.location.Location;
import com.google.gson.Gson;
import com.sun.jersey.api.client.WebResource;

import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;

public class Dashboard {

	private JFrame frame;
	private JTextField textFloorNo;
	private JTextField textRoomNo;
	private JButton btnNewLocation;
	private JTable table;
	private JButton btnNewButton_1;
	private int id = 0;

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
		Timer t = new Timer();

		t.scheduleAtFixedRate(
		    new TimerTask()
		    {
		        public void run()
		        {
		        	tableChange();
		        }
		    },
		    0,      
		    30000);
		
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
					JOptionPane.showMessageDialog(null, "Successfully Inserted \n(Following list will be changed in every 30 seconds)");
					
				} catch (RemoteException | NotBoundException e) {
					e.printStackTrace();
				}
			}
		});
		btnNewLocation.setBackground(new Color(0, 128, 0));
		btnNewLocation.setBounds(357, 86, 41, 25);
		frame.getContentPane().add(btnNewLocation);
	
		
		
		JButton btnNewButton = new JButton("UPDATE");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Registry reg;
				try {
					reg = LocateRegistry.getRegistry("localhost", 1099);
					AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
					
					
					// get use input
					int floor_no = Integer.parseInt(textFloorNo.getText());
					int room_no = Integer.parseInt(textRoomNo.getText());
					
					System.out.println(id);
					System.out.println(floor_no);
					
					System.out.println(room_no);
					
					server.updateSensor(id, floor_no, room_no);
					
					JOptionPane.showMessageDialog(null, "Successfully Updated \n(Following list will be changed in every 30 seconds)");
					
				
					
				} catch (RemoteException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NotBoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton.setBackground(Color.ORANGE);
		btnNewButton.setBounds(408, 87, 80, 23);
		frame.getContentPane().add(btnNewButton);
		
		btnNewButton_1 = new JButton("DELETE");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Registry reg;
				try {
					reg = LocateRegistry.getRegistry("localhost", 1099);
					AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
					
					
					server.deleteSensor(id);
					JOptionPane.showMessageDialog(null, "Successfully Deleted \n(Following list will be changed in every 30 seconds)");
					
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotBoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnNewButton_1.setBackground(Color.RED);
		btnNewButton_1.setBounds(493, 87, 79, 23);
		frame.getContentPane().add(btnNewButton_1);
		
		JTextPane txtpnid = new JTextPane();
		txtpnid.setText("#ID");
		txtpnid.setBounds(57, 155, 95, 20);
		frame.getContentPane().add(txtpnid);
		
		JTextPane txtpnFloorNo = new JTextPane();
		txtpnFloorNo.setText("Floor No");
		txtpnFloorNo.setBounds(152, 155, 95, 20);
		frame.getContentPane().add(txtpnFloorNo);
		
		JTextPane txtpnRoomNo = new JTextPane();
		txtpnRoomNo.setText("Room No");
		txtpnRoomNo.setBounds(246, 155, 95, 20);
		frame.getContentPane().add(txtpnRoomNo);
		
		JTextPane txtpnCoLevel = new JTextPane();
		txtpnCoLevel.setText("Co2 level");
		txtpnCoLevel.setBounds(339, 155, 95, 20);
		frame.getContentPane().add(txtpnCoLevel);
		
		JTextPane txtpnSmokeLevel = new JTextPane();
		txtpnSmokeLevel.setText("Smoke Level");
		txtpnSmokeLevel.setBounds(434, 155, 80, 20);
		frame.getContentPane().add(txtpnSmokeLevel);
		


		
		
//		DefaultTableModel model1 = (DefaultTableModel)table.getModel();
//		
//		int selectedRow = table.getSelectedRow();
//		textFloorNo.setText(model1.getValueAt(selectedRow, 1).toString());
	}
	
	public void tableChange() {
		String[] columnNames = {"#", "Floor No", "Room No", "CO2", "SmokeLvl"};
		DefaultTableModel model = new DefaultTableModel(columnNames,0);
		table = new JTable(model);
		JTableHeader header = table.getTableHeader();
		header.setBackground(Color.cyan);
		table.setBounds(58, 177, 457, 156);
		model.setColumnIdentifiers(columnNames);
		table.setModel(model);
//		JScrollPane pane = new JScrollPane(table);
	
		
		frame.getContentPane().add(table);
		
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
			
			JButton btnNewButton = new JButton("UPDATE");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
					Registry reg;
					try {
						reg = LocateRegistry.getRegistry("localhost", 1099);
						AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
						
						
						// get use input
						int floor_no = Integer.parseInt(textFloorNo.getText());
						int room_no = Integer.parseInt(textRoomNo.getText());
						
						System.out.println(id);
						System.out.println(floor_no);
						
						System.out.println(room_no);
						
						server.updateSensor(id, floor_no, room_no);
						
						JOptionPane.showMessageDialog(null, "Successfully Updated \n(Following list will be changed in every 30 seconds)");
						
					
						
					} catch (RemoteException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NotBoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
				}
			});
			btnNewButton.setBackground(Color.ORANGE);
			btnNewButton.setBounds(408, 87, 80, 23);
			frame.getContentPane().add(btnNewButton);
			
			btnNewButton_1 = new JButton("DELETE");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					Registry reg;
					try {
						reg = LocateRegistry.getRegistry("localhost", 1099);
						AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
						
						
						server.deleteSensor(id);
						JOptionPane.showMessageDialog(null, "Successfully Deleted \n(Following list will be changed in every 30 seconds)");
						
					} catch (RemoteException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (NotBoundException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					
					
				}
			});
			btnNewButton_1.setBackground(Color.RED);
			btnNewButton_1.setBounds(493, 87, 79, 23);
			frame.getContentPane().add(btnNewButton_1);
			
			JPanel panel = new JPanel();
			panel.setBackground(Color.GRAY);
			panel.setBounds(0, 0, 582, 130);
			frame.getContentPane().add(panel);
			
		} catch (RemoteException | NotBoundException  e) {
			e.printStackTrace();
		}
		ListSelectionModel listSelectionModel = table.getSelectionModel();
		listSelectionModel.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				// TODO Auto-generated method stub
				if (! listSelectionModel.isSelectionEmpty()) {
					int selectedRow = listSelectionModel.getMinSelectionIndex();
					textFloorNo.setText(model.getValueAt(selectedRow, 1).toString());
					textRoomNo.setText(model.getValueAt(selectedRow, 2).toString());
					id = (int) model.getValueAt(selectedRow, 0);
				}
			}
		});
	}
}
