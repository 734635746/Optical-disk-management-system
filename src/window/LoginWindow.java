package window;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.JFrame;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRootPane;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
/**
  * 主登陆界面
  *  包括【用户登陆】和【管理员登陆】两大功能模块
 * @author liuyoubin
 *
 */
public class LoginWindow {

	private JFrame frame;

	/**
	  * 主程序入口
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginWindow window = new LoginWindow();
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
	public LoginWindow() {
		initialize();
	}

	/**
	 * 界面初始化
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("光盘借阅系统");//界面标题
		frame.setBounds(600, 250, 689, 541);//设置界面大小
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);//窗口关闭时结束程序
		frame.getContentPane().setLayout(null);//空布局
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
		frame.getContentPane().setLayout(null);//空布局
		
		frame.addWindowListener(new WindowAdapter() {//设置关闭判断
			@Override
			public void windowClosing(WindowEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出光盘借阅系统", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) { 
					System.exit(0); 
				}
		}		
			          
		});
		
		JButton button = new JButton("管理员登陆");
		button.setFont(new Font("黑体", Font.PLAIN, 20));
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * 调用管理员登陆界面
				 */
				new ManagerLoginWindow();
			}
			/**
			 * 颜色转变效果
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.GREEN);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.BLACK);
			}
		});
		button.setBounds(265, 192, 145, 49);
		frame.getContentPane().add(button);
		
		/**
		 * 提示
		 */
		JLabel label = new JLabel("欢迎使用光盘借阅系统");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("黑体", Font.PLAIN, 42));
		label.setBounds(127, 69, 433, 44);
		frame.getContentPane().add(label);
		
		/**
		 * 用户按钮
		 */
		JButton btnNewButton = new JButton("用户登陆");
		btnNewButton.addMouseListener(new MouseAdapter() {
			/**
			 * 颜色转变效果
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton.setForeground(Color.GREEN);
			}
			public void mouseExited(MouseEvent e) {
				btnNewButton.setForeground(Color.BLACK);
			}
			@Override
			public void mouseClicked(MouseEvent e) {
				/**
				 * 调用用户登陆界面
				 */
				new UserLoginWindow();
			}
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton.setBounds(265, 279, 145, 49);
		frame.getContentPane().add(btnNewButton);
		
		/**
		 * 退出按钮
		 */
		JButton btnNewButton_1 = new JButton("退出");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			/**
			 * 颜色转变效果
			 */
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出光盘借阅系统", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					//退出时关闭程序
					System.exit(1);
				}else {
					return;
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(265, 365, 145, 49);
		frame.getContentPane().add(btnNewButton_1);
	}
}
