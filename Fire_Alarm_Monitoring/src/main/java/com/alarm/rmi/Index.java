package com.alarm.rmi;

import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JButton;
import javax.swing.ImageIcon;
import java.awt.SystemColor;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import javax.swing.table.DefaultTableModel;

import org.springframework.beans.factory.config.SetFactoryBean;

import com.alarm.com.location.Location;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.notifaction.sms.SMS;
import javax.swing.JTextPane;

public class Index {

	private JFrame frame;
	private JTable table;
	private JPanel contentPane;
	private SMS sms;
	
	

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
		
		Timer t = new Timer();

		t.scheduleAtFixedRate(
		    new TimerTask()
		    {
		        public void run()
		        {
		            TableChange();
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
			
			JTextPane txtpnid = new JTextPane();
			txtpnid.setText("#ID");
			txtpnid.setBounds(49, 166, 75, 20);
			frame.getContentPane().add(txtpnid);
			
			JTextPane txtpnFloorNo = new JTextPane();
			txtpnFloorNo.setText("Floor No");
			txtpnFloorNo.setBounds(125, 166, 75, 20);
			frame.getContentPane().add(txtpnFloorNo);
			
			JTextPane txtpnRoomNo = new JTextPane();
			txtpnRoomNo.setText("Room No");
			txtpnRoomNo.setBounds(202, 166, 75, 20);
			frame.getContentPane().add(txtpnRoomNo);
			
			JTextPane txtpnCoLevel = new JTextPane();
			txtpnCoLevel.setText("Co2 level");
			txtpnCoLevel.setBounds(280, 166, 75, 20);
			frame.getContentPane().add(txtpnCoLevel);
			
			JTextPane txtpnSmokeLevel = new JTextPane();
			txtpnSmokeLevel.setText("Smoke level");
			txtpnSmokeLevel.setBounds(358, 166, 75, 20);
			frame.getContentPane().add(txtpnSmokeLevel);
			
			JTextPane txtpnStatus = new JTextPane();
			txtpnStatus.setText("Status");
			txtpnStatus.setBounds(435, 166, 75, 20);
			frame.getContentPane().add(txtpnStatus);
			
//			JTextPane txtpnstatus = new JTextPane();
//			txtpnSmokeLevel.setText("Status");
//			txtpnSmokeLevel.setBounds(511, 167, 91, 20);
//			frame.getContentPane().add(txtpnstatus);
			
			
		}
	
	public void TableChange() {
		table = new JTable();
		table.setBounds(49, 190, 459, 191);
		frame.getContentPane().add(table);
		

		
		String[] columnNames = {"#", "Floor No", "Room No", "CO2", "SmokeLvl","status"};
		DefaultTableModel model = new DefaultTableModel(columnNames, 0);
		try {
			Registry reg = LocateRegistry.getRegistry("localhost", 1099);
			AlarmFacade server = (AlarmFacade) reg.lookup("rmi://localhost/service");
			Gson gson = new Gson();
			
			Location[] location = gson.fromJson(server.getLocation(), Location[].class);
			
			
			
			for(int i=0; i < location.length; i++) {
				Vector<String> row = new Vector<String>();
				row.add(Integer.toString(location[i].getId()));
				row.add(Integer.toString(location[i].getFloor_no()));
				row.add(Integer.toString(location[i].getRoom_no()));
				row.add(Integer.toString(location[i].getCo2()));
				row.add(Integer.toString(location[i].getSmokeLvl()));
				row.add(location[i].getStatus());
			
				model.addRow(row);
				
				
			}
			
			
			table.setModel(model);
			
		} catch (RemoteException | NotBoundException  e) {
			e.printStackTrace();
		}
	}
}