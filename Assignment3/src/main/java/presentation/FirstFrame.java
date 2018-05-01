package presentation;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import businessLogic.WriterBusinessLogic;
import entity.Writer;

import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;

public class FirstFrame {

	private JFrame frame;
	private JTextField textField_username;
	private JTextField textField_password;
	private WriterBusinessLogic wlogic=new WriterBusinessLogic();
	private Writer sessionWriter = new Writer();
	private WriterView sessionView;
	public FirstFrame() {
		initialize();
		frame.setVisible(true);
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 152);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton btnWriter = new JButton("Writer");
		btnWriter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.setBounds(100, 100, 450, 290);
				
				JLabel lblUsername = new JLabel("Username:");
				lblUsername.setBounds(101, 125, 75, 14);
				frame.getContentPane().add(lblUsername);
				
				JLabel lblPassword = new JLabel("Password:");
				lblPassword.setBounds(101, 159, 75, 14);
				frame.getContentPane().add(lblPassword);
				
				textField_username = new JTextField();
				textField_username.setBounds(210, 122, 124, 20);
				frame.getContentPane().add(textField_username);
				textField_username.setColumns(10);
				
				textField_password = new JTextField();
				textField_password.setBounds(210, 156, 124, 20);
				frame.getContentPane().add(textField_password);
				textField_password.setColumns(10);
				
				JButton btnConnect = new JButton("Connect");
				btnConnect.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							if ((textField_username.getText().isEmpty())||(textField_password.getText().isEmpty())) {
								JOptionPane.showMessageDialog(null, "Please introduce both username and password");
							}
							else {
								List<String> usernames = wlogic.writerUsernames();
								List<String> passwords = wlogic.writerPasswords();
								boolean found=false;
								for (int i=0; i<usernames.size(); i++) {
									String auxEmail = (String) usernames.get(i);
									String auxPass = (String) passwords.get(i);
									if ((auxEmail.equals(textField_username.getText()))
											&& (auxPass.equals(textField_password.getText()))) {
										found = true;
									}
								}
								if (found) {
									Writer w = new Writer();
									w=wlogic.writerGivenUsername(textField_username.getText());
									sessionWriter=w;
									sessionView=new WriterView(sessionWriter);
									frame.setBounds(100, 100, 450, 152);
								}
								else {
									JOptionPane.showMessageDialog(null, "Username or password incorrect");
								}
							}
						}
						catch (Exception e1) {
							JOptionPane.showMessageDialog(null, "Log-in failed");
						}
					}
				});
				btnConnect.setBounds(163, 199, 89, 23);
				frame.getContentPane().add(btnConnect);
			}
		});
		btnWriter.setBounds(264, 54, 89, 23);
		frame.getContentPane().add(btnWriter);
		
		JButton btnReader = new JButton("Reader");
		btnReader.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ReaderView reader=new ReaderView();
				sessionView.addObserver(reader);
			}
		});
		btnReader.setBounds(55, 54, 89, 23);
		frame.getContentPane().add(btnReader);
	}
}
