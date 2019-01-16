package window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JTextField;

import ManagerDao.ManagerQuery;
import po.Manager;

import javax.swing.JPasswordField;
import javax.swing.JRootPane;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 管理员的登陆界面
 * @author liuyoubin
 *
 */
public class ManagerLoginWindow {

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
//					ManagerLoginWindow window = new ManagerLoginWindow();
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
	public ManagerLoginWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(600, 250, 689, 541);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true); // 去掉窗口的装饰 
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);//采用指定的窗口装饰风格 
		//禁止调整大小
		frame.setResizable(false); 
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
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出管理员登陆界面", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) { 
					frame.dispose();
				}
		}		
			          
		});
		
		JLabel label = new JLabel("管理员账号登陆");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("黑体", Font.PLAIN, 40));
		label.setBounds(213, 83, 293, 58);
		frame.getContentPane().add(label);
		
		JLabel lblNewLabel = new JLabel("账号:");
		lblNewLabel.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel.setBounds(128, 207, 72, 30);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("密码:");
		lblNewLabel_1.setFont(new Font("宋体", Font.PLAIN, 25));
		lblNewLabel_1.setBounds(128, 276, 72, 30);
		frame.getContentPane().add(lblNewLabel_1);
		
		textField = new JTextField();
		textField.setFont(new Font("黑体", Font.PLAIN, 25));
		textField.setBounds(203, 211, 293, 30);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(203, 280, 293, 30);
		frame.getContentPane().add(passwordField);
		/**
		 * 登陆按钮
		 */
		JButton button = new JButton("登陆");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.BLACK);
			}
			//验证账号密码
			@Override
			public void mouseClicked(MouseEvent e) {
				//从客户获取账号密码进行验证
				String account = textField.getText();
				String pwd = passwordField.getText();
				//获取验证模块
				ManagerQuery maqy = new ManagerQuery();
				//管理员对象
				Manager manager = null;
				if(maqy.checkAccount(account)) {//验证管理员账号是否存在
					if((manager=(Manager) maqy.checkVaild(account, pwd))!=null) {//验证密码是否正确
						JOptionPane.showMessageDialog(null, "登陆成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						/**
						 * 调用管理员操作界面
						 */
						new ManagerOperateWindow(manager);
						//销毁窗口
						frame.dispose();
					}else {//密码错误
						passwordField.setText("");
						JOptionPane.showMessageDialog(null, "输入密码不正确，请重新输入", "错误", JOptionPane.ERROR_MESSAGE); 
					}
				}else{//管理员账号不存在
					textField.setText("");
					passwordField.setText("");
					JOptionPane.showMessageDialog(null, "管理员账号不存在，请重新输入", "错误", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		
		button.setFont(new Font("黑体", Font.PLAIN, 20));
		button.setBounds(203, 384, 113, 45);
		frame.getContentPane().add(button);
		
		/**
		 * 
		 */
		JButton btnNewButton = new JButton("退出");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出管理员登陆界面", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					//退出时销毁窗口
					frame.dispose();
				}else {
					return;
				}
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton.setBounds(383, 384, 113, 45);
		frame.getContentPane().add(btnNewButton);
		
		frame.setTitle("管理员登陆");
		frame.setVisible(true);
	}
}
