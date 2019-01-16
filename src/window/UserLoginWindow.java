package window;
import java.awt.Color;
/**
 * 登陆界面
 */
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.swing.JTextField;

import UserDao.UserQuery;
import po.User;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;

public class UserLoginWindow {

	private JFrame frame;
	private JTextField textField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserLoginWindow window = new UserLoginWindow();
//					window.frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the application.
	 */
	public UserLoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("用户登陆界面");
		frame.setBounds(600, 250, 689, 541);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true); // 去掉窗口的装饰 
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);//采用指定的窗口装饰风格 
		//禁止调整大小
		frame.setResizable(false); 

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出用户登陆界面", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) { 
					frame.dispose();
				}
		}		
			          
		});
		
		
		/**
		 * 添加界面背景
		 */
		JPanel bj = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				Image image;
				try {
					image = ImageIO.read(new File("src/Image/6.jpg"));
					g.drawImage(image, 0, 0,getWidth(),getHeight(),null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		};
		frame.setContentPane(bj);
		frame.getContentPane().setLayout(null);
		
		JLabel label = new JLabel("用户账号登陆");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("黑体", Font.PLAIN, 40));
		label.setBounds(230, 66, 250, 58);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("账号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(131, 185, 72, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(131, 258, 72, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 20));
		textField.setBounds(217, 189, 282, 31);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(217, 262, 282, 31);
		frame.getContentPane().add(passwordField);
		
		JButton btnNewButton = new JButton("登陆");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);
			}
			/**
			 * 用户登陆验证
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				//从客户获取账号密码进行验证
				String account = textField.getText();
				String pwd = passwordField.getText();
				//获取验证模块
				UserQuery usqy = new UserQuery();
				//用户对象
				User user = null;
				if(usqy.checkAccount(account)) {//验证用户账号是否存在
					if((user = (User) usqy.checkVaild(account, pwd))!=null) {//验证密码是否正确
						JOptionPane.showMessageDialog(null, "登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						/**
						 * 转到用户操作界面
						 */
						new UserOperateWindow(user);
						//销毁窗口
						frame.dispose();
						
					}else {
						passwordField.setText("");
						JOptionPane.showMessageDialog(null, "输入密码不正确，请重新输入", "错误", JOptionPane.ERROR_MESSAGE); 
					}
				
				}else {//用户账号不存在
					textField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "您输入的账号不存在，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
					
				}
				
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton.setBounds(217, 356, 120, 51);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出用户登陆界面", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					//退出时销毁窗口
					frame.dispose();
				}else {
					return;
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(379, 356, 120, 51);
		frame.getContentPane().add(btnNewButton_1);
		

		
		frame.setVisible(true);
	}
}
