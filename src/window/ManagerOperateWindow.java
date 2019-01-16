package window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JRootPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import po.CD;
import po.Manager;
import po.Record;
import po.Root;
import po.User;
import util.ConversionUtil;
import util.StringUtil;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JRadioButton;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

import com.ibm.icu.text.DecimalFormat;

import ManagerDao.ManagerOperation;
import ManagerDao.managerOperateInt;
import UserDao.UserOperate;
import UserDao.UserQuery;

import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
/**
 * 管理员操作界面
 * @author liuyoubin
 *
 */
public class ManagerOperateWindow {

	private JFrame frame;
	private Manager manager;//管理员对象，一个操作界面一个对象，标识唯一管理员
	private JTextField textField;
	private JTextField textField_1;
	private JPasswordField passwordField;
	/**
	 * 创建管理员操作模块
	 */
	ManagerOperation manaop = new ManagerOperation();
	private JTextField textField_4;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ManagerOperateWindow window = new ManagerOperateWindow();
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
	public ManagerOperateWindow() {
		initialize();
	}
	/**
	 * Create the application.
	 */
	public ManagerOperateWindow(Manager manager) {
		this.manager = manager;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("欢迎【"+manager.getMaccount()+"】使用光盘借阅系统");
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
					image = ImageIO.read(new File("src/Image/7.jpg"));
					g.drawImage(image, 0, 0,getWidth(),getHeight(),null);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
		};
		frame.setContentPane(bj);
		frame.getContentPane().setLayout(null);
		
		
		Font font = new Font("黑体", Font.PLAIN, 20);
		
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 689, 38);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("用户管理");
		mnNewMenu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu.setForeground(Color.BLACK);
			}
		});
		mnNewMenu.setFont(font);
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("新建用户");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			/**
			 * 新建用户
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				showAddUserPanel(menuBar);
			}
		});
		mntmNewMenuItem.setFont(font);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改用户信息");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			/**
			 * 修改用户信息
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String uaccount = JOptionPane.showInputDialog("请输入要修改的用户账号");	
				User user = manaop.queryUser(uaccount);
				if(user==null) {
					JOptionPane.showMessageDialog(null, "不存在此用户", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
				
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showUpdateUserPanel(menuBar,user);
				}
			}
		});
		mntmNewMenuItem_1.setFont(font);
		mnNewMenu.add(mntmNewMenuItem_1);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("查看用户信息");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			/**
			 * 查看用户信息
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String uaccount = JOptionPane.showInputDialog("请输入要查看信息的用户账号");	
				User user = manaop.queryUser(uaccount);
				if(user==null) {
					JOptionPane.showMessageDialog(null, "不存在此用户", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showUserInfo(menuBar,user);
					
				}
			}
		});
		mntmNewMenuItem_2.setFont(font);
		mnNewMenu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("查看所有用户");
		mntmNewMenuItem_10.addMouseListener(new MouseAdapter() {
			/**
			 * 查看所有用户
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(false);
				List<User> userList = manaop.queryAllUser();
				/**
				 * 调用面板
				 */
				showAllUserPanel(menuBar,userList);
			}
		});
		mntmNewMenuItem_10.setFont(font);
		mnNewMenu.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("删除用户");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			/**
			 * 删除用户
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String uaccount = JOptionPane.showInputDialog("请输入要删除的用户账号");	
				User user = manaop.queryUser(uaccount);
				if(user==null) {
					JOptionPane.showMessageDialog(null, "不存在此用户", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showDeleteUserPanel(menuBar,user);
					
				}
				
			}
		});
		mntmNewMenuItem_3.setFont(font);
		mnNewMenu.add(mntmNewMenuItem_3);
		
		JMenu mnNewMenu_1 = new JMenu("光盘管理");
		mnNewMenu_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu_1.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu_1.setForeground(Color.BLACK);
			}
		});
		mnNewMenu_1.setFont(font);
		menuBar.add(mnNewMenu_1);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("新建光盘");
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			/**
			 * 新建光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				showAddCDPanel(menuBar);
				
			}
		});
		mntmNewMenuItem_4.setFont(font);
		mnNewMenu_1.add(mntmNewMenuItem_4);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("修改光盘");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			/**
			 * 修改光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String cid = JOptionPane.showInputDialog("请输入所要修改的光盘编号");
				CD cd = manaop.queryCDByCid(cid);
				if(cd==null) {//不存在指定光盘
					JOptionPane.showMessageDialog(null, "不存在所指定光盘", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showUpdateCDPanel(menuBar, cd);
				}
			}
		});
		mntmNewMenuItem_6.setFont(font);
		mnNewMenu_1.add(mntmNewMenuItem_6);
		
		JMenu mnNewMenu_3 = new JMenu("查看光盘");
		mnNewMenu_3.setFont(font);
		mnNewMenu_1.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("查看所有光盘");
		mnNewMenu_3.add(mntmNewMenuItem_5);
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			/**
			 * 查看所有光盘
			 */
			public void mousePressed(MouseEvent e) {
				//查询所有光盘
				List<CD> cdList= (List<CD>) manaop.queryAllCD();
				if(cdList.size()==0||cdList==null) {
					JOptionPane.showMessageDialog(null, "当前无光盘", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					queryCDPanel(cdList,menuBar);
				}
				}
		});
		mntmNewMenuItem_5.setFont(font);
		
		JMenuItem menuItem_2 = new JMenuItem("查看指定用户的光盘");
		menuItem_2.setFont(font);
		menuItem_2.addMouseListener(new MouseAdapter() {
			/**
			 *查看指定用户的光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String userAccount = JOptionPane.showInputDialog("请输入所要查询的用户账号");
				//判断用户是否存在
				if(new UserQuery().checkAccount(userAccount)) {
					//查询光盘
					List<CD> cdList= (List<CD>) manaop.queryCDByUserAccount(userAccount);
					menuBar.setVisible(false);
					queryCDPanel(cdList,menuBar);
					
				}else{//判断用户不存在
					JOptionPane.showMessageDialog(null, "此用户不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		mnNewMenu_3.add(menuItem_2);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("删除光盘");
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			/**
			 * 删除光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String cid = JOptionPane.showInputDialog("请输入所要删除的光盘编号");
				CD cd = manaop.queryCDByCid(cid);
				if(cd==null) {//不存在指定光盘
					JOptionPane.showMessageDialog(null, "不存在所指定光盘", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showDelectCDPanel(menuBar, cd);
				}
			}
		});
		mntmNewMenuItem_7.setFont(font);
		mnNewMenu_1.add(mntmNewMenuItem_7);
		
		JMenu mnNewMenu_4 = new JMenu("光盘借阅统计");
		mnNewMenu_1.add(mnNewMenu_4);
		mnNewMenu_4.setFont(font);
		
		JMenuItem  mntmNewMenuItem_9 = new JMenuItem("柱状图统计");
		mnNewMenu_4.add(mntmNewMenuItem_9);
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			/**
			 * 柱状图统计
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				
				List<Record> recordList = manaop.queryAllRecord();
				Map<String,Integer> cdMap= manaop.statistcsCDs(recordList);
				/**
				 * 调用面板
				 */
				menuBar.setVisible(false);
				showStaticsCD(menuBar, cdMap);
				
			}
		});
		mntmNewMenuItem_9.setFont(font);
		
		JMenuItem menuItem_3 = new JMenuItem("圆饼图统计");
		menuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				List<Record> recordList = manaop.queryAllRecord();
				Map<String,Integer> cdMap= manaop.statistcsCDs(recordList);
				/**
				 * 调用面板
				 */
				menuBar.setVisible(false);
				showStaticsCD_2(menuBar, cdMap);
				
			}
		});
		menuItem_3.setFont(font);
		mnNewMenu_4.add(menuItem_3);
		
		JMenu mnNewMenu_2 = new JMenu("系统");
		mnNewMenu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu_2.setForeground(Color.BLACK);
			}
		});
		
		JMenu menu = new JMenu("日志管理");
		menu.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menu.setForeground(Color.BLACK);
			}
		});
		menu.setFont(font);
		menuBar.add(menu);
		
		JMenuItem menuItem = new JMenuItem("查询所有借阅日志");
		menuItem.addMouseListener(new MouseAdapter() {
			/**
			 * 查看借阅日志
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				List<Record> recordList= null;
				/**
				 * 调用查询方法
				 */
				recordList = manaop.queryAllRecord();
				
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				ShowBorrowedRecordPanel(menuBar,recordList);
				
				
			}
		});
		menuItem.setFont(font);
		menu.add(menuItem);
		
		JMenuItem menuItem_1 = new JMenuItem("查询指定用户日志");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String userAccount = JOptionPane.showInputDialog("请输入将所要查询的用户账号");
				//判断用户是否存在
				if(new UserQuery().checkAccount(userAccount)) {
					List<Record> recordList= null;
					/**
					 * 调用查询方法
					 */
					recordList = manaop.queryRecordByUesrAccount(userAccount);
					
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					ShowBorrowedRecordPanel(menuBar,recordList);
					
				}else {//判断用户不存在
					JOptionPane.showMessageDialog(null, "此用户不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		menuItem_1.setFont(font);
		menu.add(menuItem_1);
		
		JMenu mnNewMenu_5 = new JMenu("权限管理");
		mnNewMenu_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				mnNewMenu_5.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				mnNewMenu_5.setForeground(Color.BLACK);
			}
			
		});
		mnNewMenu_5.setFont(font);
		menuBar.add(mnNewMenu_5);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("查看用户权限");
		mntmNewMenuItem_11.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String userAccount = JOptionPane.showInputDialog("请输入将所要查询的用户账号");
				//判断用户是否存在
				if(new UserQuery().checkAccount(userAccount)) {
					Root root = manaop.queryRoot(userAccount);
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showRootPanel(menuBar, root);
					
				}else {//判断用户不存在
					JOptionPane.showMessageDialog(null, "此用户不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}
			}
		});
		mntmNewMenuItem_11.setFont(font);
		mnNewMenu_5.add(mntmNewMenuItem_11);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("修改用户权限");
		mntmNewMenuItem_12.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String userAccount = JOptionPane.showInputDialog("请输入将所要修改的用户账号");
				//判断用户是否存在
				if(new UserQuery().checkAccount(userAccount)) {
					Root root = manaop.queryRoot(userAccount);
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showModifyRoot(menuBar, root, userAccount);
					
				}else {//判断用户不存在
					JOptionPane.showMessageDialog(null, "此用户不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}
				
			}
		});
		mntmNewMenuItem_12.setFont(font);
		mnNewMenu_5.add(mntmNewMenuItem_12);
		
		JMenuItem menuItem_4 = new JMenuItem("用户权限申请");
		menuItem_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				List<Root> rList = manaop.queryUserApplyRoot();
				boolean b = false;
				for(Root root:rList) {
					if(root.getApplyModifyRoot()!=null) {
					if(root.getApplyModifyRoot().equals("申请")) {
						JOptionPane.showMessageDialog(null, "用户【"+root.getUserAccount()+"】申请修改权限", "提示", JOptionPane.INFORMATION_MESSAGE);
						b=true;
						new ManagerOperation().setNULLApplyRoot(root.getUserAccount(), 0);
					}
			}
					if(root.getApplyBorrowRoot()!=null) {
					if(root.getApplyBorrowRoot().equals("申请")) {
						JOptionPane.showMessageDialog(null, "用户【"+root.getUserAccount()+"】申请借阅权限", "提示", JOptionPane.INFORMATION_MESSAGE);
						b=true;
						new ManagerOperation().setNULLApplyRoot(root.getUserAccount(), 1);
					}
					}
				}
				if(!b) {
					JOptionPane.showMessageDialog(null, "当前无用户申请权限", "提示", JOptionPane.INFORMATION_MESSAGE);
				}
				
			}
		});
		menuItem_4.setFont(font);
		mnNewMenu_5.add(menuItem_4);
		mnNewMenu_2.setFont(font);
		menuBar.add(mnNewMenu_2);
		
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("退出");
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出操作界面", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					//退出时销毁窗口
					frame.dispose();
				}else {
					return;
				}
			}
			
		});
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("锁定");
		mntmNewMenuItem_13.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				showLockPanel(menuBar);
			}
		});
		mntmNewMenuItem_13.setFont(font);
		mnNewMenu_2.add(mntmNewMenuItem_13);
		mntmNewMenuItem_8.setFont(font);
		mnNewMenu_2.add(mntmNewMenuItem_8);
		


		
		


	
		frame.setVisible(true);
		
	}
	/**
	 * 显示锁定面板
	 */
	public void showLockPanel(JMenuBar menuBar) {
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		Font font2 = new Font("黑体", Font.BOLD, 35) ;
		Font font3 = new Font("黑体", Font.BOLD, 20) ;
		Color c = Color.WHITE;
		
		
		
		JLabel lblNewLabel_24 = new JLabel("密码：");
		lblNewLabel_24.setFont(font3);
		lblNewLabel_24.setBounds(176, 242, 76, 41);
		panel.add(lblNewLabel_24);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(250, 242, 217, 34);
		panel.add(passwordField);
		
		JLabel label = new JLabel("当前处于锁定状态");
		label.setForeground(c);
		label.setFont(font2);
		label.setBounds(135, 69, 332, 41);
		panel.add(label);
		
		JLabel lblNewLabel_25 = new JLabel("输入密码解除锁定");
		lblNewLabel_25.setForeground(c);
		lblNewLabel_25.setFont(font2);
		lblNewLabel_25.setBounds(240, 138, 296, 41);
		panel.add(lblNewLabel_25);
		

		JButton button = new JButton("解除锁定");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.GREEN);
			}
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.BLACK);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				String pass = passwordField.getText();
				if(pass.equals(manager.getMpwd())) {
					menuBar.setVisible(true);
					panel.setVisible(false);
					panel.updateUI();
					frame.remove(panel);
				}else {
					passwordField.setText("");
					JOptionPane.showMessageDialog(null,"密码错误，请重新输入", "提示", JOptionPane.ERROR_MESSAGE);
					return;
				}
			}
		});
		button.setFont(font3);
		button.setBounds(176, 385, 120, 50);
		panel.add(button);
		
		JButton btnNewButton_3 = new JButton("退出程序");
		btnNewButton_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_3.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent e) {
				btnNewButton_3.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否退出程序", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					//退出时销毁窗口
					frame.dispose();
				}else {
					return;
				}
			
				
			}
		});
		btnNewButton_3.setFont(font3);
		btnNewButton_3.setBounds(382, 385, 120, 50);
		panel.add(btnNewButton_3);
		
		
		
		panel.updateUI();
		
	}
	/**
	 * 修改用户权限信息
	 */
	public void showModifyRoot(JMenuBar menuBar,Root root,String userAccount) {
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		Font font1 = new Font("黑体", Font.BOLD, 20);
		
		JLabel lblNewLabel_19 = new JLabel("修改信息权限：");
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setFont(font1);
		lblNewLabel_19.setBounds(200, 225, 153, 38);
		panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("借阅光盘权限：");
		lblNewLabel_20.setForeground(Color.WHITE);
		lblNewLabel_20.setFont(font1);
		lblNewLabel_20.setBounds(200, 303, 153, 38);
		panel.add(lblNewLabel_20);
		
		String [] cDType = {"启用","禁用"}; 
		
		JComboBox comboBox = new JComboBox(cDType);
		comboBox.setFont(font1);
		comboBox.setSelectedItem(root.getModifyRoot());
		comboBox.setBounds(375, 234, 116, 24);
		panel.add(comboBox);
		
		JComboBox comboBox_1 = new JComboBox(cDType);
		comboBox_1.setFont(font1);
		comboBox_1.setSelectedItem(root.getBorrowRoot());
		comboBox_1.setBounds(377, 309, 113, 24);
		panel.add(comboBox_1);
		
		JButton btnNewButton_2 = new JButton("修改");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLACK);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否修改用户权限", "提示", JOptionPane.YES_NO_OPTION);
				
				if(option==JOptionPane.YES_OPTION) {//确认修改
					Root newRoot = new Root();
					newRoot.setModifyRoot((String)comboBox.getSelectedItem());
					newRoot.setBorrowRoot((String) comboBox_1.getSelectedItem());
					if(manaop.modifyRoot(userAccount, newRoot)) {
						JOptionPane.showMessageDialog(null, "修改权限成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						menuBar.setVisible(true);
		        		panel.setVisible(false);
		        		panel.updateUI();
		        		frame.remove(panel);
					}else {
						JOptionPane.showMessageDialog(null, "修改权限失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
					
				
				}else {
					return;
				}
				
				
				
			}
		});
		btnNewButton_2.setFont(font1);
		btnNewButton_2.setBounds(196, 417, 128, 38);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_23 = new JLabel("修改用户权限信息");
		lblNewLabel_23.setForeground(Color.ORANGE);
		lblNewLabel_23.setFont(new Font("黑体", Font.BOLD, 35));
		lblNewLabel_23.setBounds(200, 69, 306, 38);
		panel.add(lblNewLabel_23);
		
		JLabel lblNewLabel_24 = new JLabel("用户账号：");
		lblNewLabel_24.setForeground(Color.WHITE);
		lblNewLabel_24.setFont(font1);
		lblNewLabel_24.setBounds(200, 155, 108, 25);
		panel.add(lblNewLabel_24);
		
		JButton btnNewButton_4 = new JButton("取消");
		btnNewButton_4.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_4.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_4.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否取消修改用户权限", "提示", JOptionPane.YES_NO_OPTION);
				
				if(option==JOptionPane.YES_OPTION) {//确认取消
					menuBar.setVisible(true);
	        		panel.setVisible(false);
	        		panel.updateUI();
	        		frame.remove(panel);
				}else {
					return;
				}
				
			}
			
		});
		btnNewButton_4.setFont(font1);
		btnNewButton_4.setBounds(388, 417, 132, 39);
		panel.add(btnNewButton_4);
		
		JLabel lblNewLabel_25 = new JLabel(userAccount);
		lblNewLabel_25.setFont(font1);
		lblNewLabel_25.setBounds(343, 157, 181, 25);
		panel.add(lblNewLabel_25);
		
		
		
		panel.updateUI();
	}
	/**
	 * 显示权限信息的面板
	 * @param menuBar
	 */
	public void showRootPanel(JMenuBar menuBar,Root root) {
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		
		Font font1 = new Font("黑体", Font.BOLD, 20);
		
		JLabel lblNewLabel_19 = new JLabel("修改信息权限：");
		lblNewLabel_19.setForeground(Color.WHITE);
		lblNewLabel_19.setFont(font1);
		lblNewLabel_19.setBounds(200, 170, 153, 38);
		panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("借阅光盘权限：");
		lblNewLabel_20.setForeground(Color.WHITE);
		lblNewLabel_20.setFont(font1);
		lblNewLabel_20.setBounds(200, 303, 153, 38);
		panel.add(lblNewLabel_20);
		
		JLabel lblNewLabel_21 = new JLabel("已"+root.getModifyRoot());
		if(root.getModifyRoot().equals("启用")) {	
			lblNewLabel_21.setForeground(Color.BLUE);
		}else {
			lblNewLabel_21.setForeground(Color.RED);
		}
		lblNewLabel_21.setFont(font1);
		lblNewLabel_21.setBounds(420, 170, 119, 38);
		panel.add(lblNewLabel_21);
		
		JLabel lblNewLabel_22 = new JLabel("已"+root.getBorrowRoot());
		if(root.getBorrowRoot().equals("启用")) {	
			lblNewLabel_22.setForeground(Color.BLUE);
		}else {
			lblNewLabel_22.setForeground(Color.RED);
		}
		lblNewLabel_22.setFont(font1);
		lblNewLabel_22.setBounds(420, 303, 116, 38);
		panel.add(lblNewLabel_22);
		
		JButton btnNewButton_2 = new JButton("确定");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLACK);
			}
			
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(true);
        		panel.setVisible(false);
        		panel.updateUI();
        		frame.remove(panel);
			}
		});
		btnNewButton_2.setFont(font1);
		btnNewButton_2.setBounds(290, 443, 128, 38);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_23 = new JLabel("用户权限信息");
		lblNewLabel_23.setForeground(Color.ORANGE);
		lblNewLabel_23.setFont(new Font("黑体", Font.BOLD, 35));
		lblNewLabel_23.setBounds(239, 69, 232, 38);
		panel.add(lblNewLabel_23);
		
		panel.updateUI();
		

		
	}
	/**
	 * 统计光盘借阅情况_饼图
	 * @throws IOException 
	 */
	public void showStaticsCD_2(JMenuBar menuBar,Map<String,Integer> cdMap) {
		frame.setSize(frame.getWidth(), frame.getHeight());
		 DefaultPieDataset dataset = new DefaultPieDataset();   
		 //获取数据集
		 for(String key:cdMap.keySet()) {	
				Number num = cdMap.get(key);
				dataset.setValue(key, num);
			}
		 
		 Font font = new Font("黑体", Font.BOLD, 15);

		 JFreeChart chart = ChartFactory.createPieChart("光盘借阅统计", dataset, true, false, true);
		 //设置图片标题的字体
		 chart.getTitle().setFont(font);
		    
	     //获得图块,设置标签的字体
		 PiePlot plot = (PiePlot) chart.getPlot();
		
		
		 plot.setExplodePercent("文学", 0.1);
		     
		 //设置标签字体
		 plot.setLabelFont(font);
		 chart.getLegend().setItemFont(font);
		 
		//设置百分比
		 plot.setStartAngle(new Float(3.14f / 2f));
		 
		 //设置背景图片,设置最大的背景
		 Image img;
		try {
			img = ImageIO.read(new File("src/Image/7.jpg"));
			chart.setBackgroundImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		 
		       
		 //设置plot的背景图片
		 try {
			img = ImageIO.read(new File("src/Image/7.jpg"));
			 plot.setBackgroundImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		      
		 //设置plot的前景色透明度
		  plot.setForegroundAlpha(0.7f);
		       
		  //设置plot的背景色透明度
		 plot.setBackgroundAlpha(0.0f);
		      
		 //设置标签生成器(默认{0})
		 //{0}:key {1}:value {2}:百分比 {3}:sum
		 plot.setLabelGenerator(new StandardPieSectionLabelGenerator("{0}(借阅{1}次)/{2}"));

		 ChartPanel cpFrame = new ChartPanel(chart, true);
		 cpFrame.setLayout(null);
		 cpFrame.setBounds(0, 0, 689, 541);
		 
		 JButton button = new JButton("确定");
			button.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseEntered(MouseEvent e) {
					button.setForeground(Color.BLUE);
				}
				public void mouseExited(MouseEvent e) {
					button.setForeground(Color.BLACK);
				}
				/**
				 * 确定
				 * @param e
				 */
				@Override
				public void mouseClicked(MouseEvent e) {	
					menuBar.setVisible(true);
					cpFrame.setVisible(false);
					cpFrame.updateUI();
					frame.remove(cpFrame);
				}
			});
			button.setForeground(Color.BLACK);
			button.setFont(new Font("黑体", Font.BOLD, 20));
			button.setBounds(120, 460, 80, 30);
			cpFrame.add(button);	
			
			
			cpFrame.setBounds(0, 0, 689, 541);
			cpFrame.updateUI();
			
         frame.getContentPane().add(cpFrame);
		
	}
	/**
	 * 统计光盘借阅情况_柱状图
	 */
	public void showStaticsCD(JMenuBar menuBar,Map<String,Integer> cdMap) {
		int width = frame.getWidth();
		int height = frame.getHeight();
		frame.setSize(width, height+35);
		
		//获取数据集
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		
		for(String key:cdMap.keySet()) {
			
			Number num = cdMap.get(key);
			dataset.addValue(num, key, key);
		}
		
		JFreeChart chart = ChartFactory.createBarChart3D("光盘借阅统计", "光盘种类", "借阅次数", dataset,PlotOrientation.VERTICAL,true,false,false);
		CategoryPlot plot = chart.getCategoryPlot();//获取图标区域对象
		Image img = null;
		try {
			img = ImageIO.read(new File("src/Image/7.jpg"));
			chart.setBackgroundImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		//设置plot的背景图片
		 try {
			img = ImageIO.read(new File("src/Image/7.jpg"));
			 plot.setBackgroundImage(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		CategoryAxis Axis = plot.getDomainAxis();//水平区域列表
		
		Axis.setLabelFont(new Font("黑体", Font.BOLD, 14));
		Axis.setTickLabelFont(new Font("宋体", Font.BOLD, 12));
		ValueAxis ranAxis = plot.getRangeAxis();//获取柱状
		ranAxis.setLabelFont(new Font("黑体", Font.BOLD, 15));
		chart.getLegend().setItemFont(new Font("黑体", Font.BOLD, 15));
		chart.getTitle().setFont(new Font("宋体", Font.BOLD, 20));
		ChartPanel cpFrame = new ChartPanel(chart, true);
		cpFrame.setLayout(null);
		
		JButton button = new JButton("确定");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.BLUE);
			}
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.BLACK);
			}
			/**
			 * 确定
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {	
				menuBar.setVisible(true);
				cpFrame.setVisible(false);
				cpFrame.updateUI();
				frame.setSize(width, height);
				frame.remove(cpFrame);
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(new Font("黑体", Font.BOLD, 20));
		button.setBounds(550, 500, 80, 30);
		cpFrame.add(button);	
		
		
		cpFrame.setBounds(0, 0, 689, 541);
		cpFrame.updateUI();
		
		frame.getContentPane().add(cpFrame);
		
		
	}
	
	
	/**
	 * 显示所有用户的界面
	 */
	public void showAllUserPanel(JMenuBar menuBar,List<User> userList) {
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
   		
		String[] columnNames = { "用户账号", "用户密码", "用户姓名", "用户年龄","用户性别","出生日期"};  
		/**
		 * 调用工具方法，将CD列表转换成适合表格的二位数组
		 */
        Object[][] obj = ConversionUtil.userToArray_Manager(userList);
  
        JTable table = new JTable(obj, columnNames);  
        table.setFont(new Font("TimesRoman", Font.BOLD, 15));
        table.setRowHeight(25);
        table.setBackground(Color.pink);
        JTableHeader  tableH = table.getTableHeader();
        //设置表头的背景色
        tableH.setBackground(Color.YELLOW);
        tableH.setPreferredSize(new Dimension(tableH.getWidth(),35));
        tableH.setFont(new Font("TimesRoman", Font.BOLD, 18));
        //设置宽度
        TableColumn column = null;  
        int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i); 
            column.setPreferredWidth(114);  
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
        JScrollPane scroll = new JScrollPane(table);
        
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setBounds(0, 62, 689, 479);
          
        panel.add(scroll);
        
        JLabel lblNewLabel_18 = new JLabel("全部用户信息");
        lblNewLabel_18.setForeground(Color.BLUE);
        lblNewLabel_18.setFont(new Font("黑体", Font.PLAIN, 30));
        
        lblNewLabel_18.setBounds(45, 13, 207, 36);
        panel.add(lblNewLabel_18);
        
        JButton btnNewButton_1 = new JButton("确定");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseEntered(MouseEvent e) {
        		btnNewButton_1.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			/**
			 * 退出界面
			 */
        	@Override
        	public void mousePressed(MouseEvent e) {
        		menuBar.setVisible(true);
        		panel.setVisible(false);
        		panel.updateUI();
        		frame.remove(panel);
        		
        		
        	}
        });
        btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 25));
        btnNewButton_1.setBounds(416, 22, 116, 27);
        panel.add(btnNewButton_1);
		
        panel.updateUI();
		
	}
	/**
	 * 显示删除用户的界面
	 */
	public void showDeleteUserPanel(JMenuBar menuBar,User user) {
		
		JPanel panel2 = new JPanel() ;
		
	panel2.setOpaque(false);
	panel2.setBounds(0, 0, 689, 541);
	panel2.setLayout(null);
	panel2.setVisible(true);
	frame.getContentPane().add(panel2);
	Font font1 = new Font("黑体", Font.PLAIN, 20);
	Font font2 = new Font("黑体", Font.PLAIN, 20);
	Color color = Color.WHITE;
	
	JLabel label = new JLabel("姓名：");
	label.setForeground(color);
	label.setFont(font1);
	label.setBounds(123, 163, 72, 18);
	panel2.add(label);
	
	JLabel label_1 = new JLabel("性别：");
	label_1.setForeground(color);
	label_1.setFont(font1);
	label_1.setBounds(123, 239, 66, 18);
	panel2.add(label_1);
	
	JLabel label_2 = new JLabel("年龄：");
	label_2.setForeground(color);
	label_2.setFont(font1);
	label_2.setBounds(123, 322, 66, 18);
	panel2.add(label_2);
	
	JLabel lblNewLabel_13 = new JLabel("出生日期：");
	lblNewLabel_13.setForeground(color);
	lblNewLabel_13.setFont(font1);
	lblNewLabel_13.setBounds(91, 413, 119, 18);
	panel2.add(lblNewLabel_13);
	
	JLabel lblNewLabel_14 = new JLabel("账号：");
	lblNewLabel_14.setForeground(color);
	lblNewLabel_14.setFont(font1);
	lblNewLabel_14.setBounds(344, 163, 72, 18);
	panel2.add(lblNewLabel_14);
	
	JLabel lblNewLabel_15 = new JLabel("密码：");
	lblNewLabel_15.setForeground(color);
	lblNewLabel_15.setFont(font1);
	lblNewLabel_15.setBounds(344, 239, 66, 18);
	panel2.add(lblNewLabel_15);
		

	JButton button = new JButton("取消");
	button.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			button.setForeground(Color.BLUE);
		}
		public void mouseExited(MouseEvent e) {
			button.setForeground(Color.BLACK);
		}
		/**
		 * 取消
		 * @param e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "是否取消删除", "提示", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {//确认取消				
					menuBar.setVisible(true);
					panel2.setVisible(false);
					panel2.updateUI();
					frame.remove(panel2);
					
			}else {
				return;
			}
			
		}
	});
	button.setForeground(Color.BLACK);
	button.setFont(font2);
	button.setBounds(394, 436, 128, 46);
	panel2.add(button);
	
	JLabel label1 = new JLabel("删除用户");
	label1.setForeground(Color.RED);
	label1.setFont(new Font("黑体",Font.PLAIN, 40));
	label1.setBounds(249, 57, 173, 40);
	panel2.add(label1);
	
	JLabel lblNewLabel_8 = new JLabel(user.getUname());
	lblNewLabel_8.setFont(font1);
	lblNewLabel_8.setBounds(211, 161, 119, 20);
	panel2.add(lblNewLabel_8);
	
	JLabel lblNewLabel_9 = new JLabel(user.getSex());
	lblNewLabel_9.setFont(font1);
	lblNewLabel_9.setBounds(211, 239, 115, 20);
	panel2.add(lblNewLabel_9);
	
	JLabel lblNewLabel_10 = new JLabel(Integer.toString(user.getAge()));
	lblNewLabel_10.setFont(font1);
	lblNewLabel_10.setBounds(207, 320, 123, 20);
	panel2.add(lblNewLabel_10);
	
	JLabel lblNewLabel_11 = new JLabel(StringUtil.DataToString(user.getBirthday()));
	lblNewLabel_11.setFont(font1);
	lblNewLabel_11.setBounds(211, 413, 144, 20);
	panel2.add(lblNewLabel_11);
	
	JLabel lblNewLabel_12 = new JLabel(user.getUaccount());
	lblNewLabel_12.setFont(font1);
	lblNewLabel_12.setBounds(409, 163, 183, 25);
	panel2.add(lblNewLabel_12);
	
	JLabel lblNewLabel_19 = new JLabel(user.getUpwd());
	lblNewLabel_19.setFont(font1);
	lblNewLabel_19.setBounds(419, 234, 173, 25);
	panel2.add(lblNewLabel_19);
	
	JButton btnNewButton_3 = new JButton("删除用户");
	btnNewButton_3.setFont(font2);
	btnNewButton_3.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			btnNewButton_3.setForeground(Color.RED);
		}
		@Override
		public void mouseExited(MouseEvent e) {
			btnNewButton_3.setForeground(Color.BLACK);
		}
		/**
		 * 删除用户
		 */
		public void mousePressed(MouseEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "是否删除此用户", "提示", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {//确认删除
				
				if(manaop.deleteUser(user.getUaccount())) {
					JOptionPane.showMessageDialog(null, "成功删除用户", "提示", JOptionPane.INFORMATION_MESSAGE);
					menuBar.setVisible(true);
					panel2.setVisible(false);
					panel2.updateUI();
					frame.remove(panel2);
					
				}else {
					JOptionPane.showMessageDialog(null, "用户删除失败", "提示", JOptionPane.ERROR_MESSAGE);
				}
				
			}else {
				return;
			}
		}
	});
	btnNewButton_3.setBounds(394, 361, 128, 46);
	panel2.add(btnNewButton_3);
		
	panel2.updateUI();
		
		
		
		
	}
	
	/**
	 * 显示查看用户信息的界面
	 */
	public void showUserInfo(JMenuBar menuBar,User user) {
		
		JPanel panel2 = new JPanel() ;
		
	panel2.setOpaque(false);
	panel2.setBounds(0, 0, 689, 541);
	panel2.setLayout(null);
	panel2.setVisible(true);
	frame.getContentPane().add(panel2);
	Font font1 = new Font("黑体", Font.PLAIN, 20);
	Font font2 = new Font("黑体", Font.PLAIN, 20);
	Color color = Color.WHITE;
	
	JLabel label = new JLabel("姓名：");
	label.setForeground(color);
	label.setFont(font1);
	label.setBounds(123, 163, 72, 18);
	panel2.add(label);
	
	JLabel label_1 = new JLabel("性别：");
	label_1.setForeground(color);
	label_1.setFont(font1);
	label_1.setBounds(123, 239, 66, 18);
	panel2.add(label_1);
	
	JLabel label_2 = new JLabel("年龄：");
	label_2.setForeground(color);
	label_2.setFont(font1);
	label_2.setBounds(123, 322, 66, 18);
	panel2.add(label_2);
	
	JLabel lblNewLabel_13 = new JLabel("出生日期：");
	lblNewLabel_13.setForeground(color);
	lblNewLabel_13.setFont(font1);
	lblNewLabel_13.setBounds(91, 413, 119, 18);
	panel2.add(lblNewLabel_13);
	
	JLabel lblNewLabel_14 = new JLabel("账号：");
	lblNewLabel_14.setForeground(color);
	lblNewLabel_14.setFont(font1);
	lblNewLabel_14.setBounds(344, 163, 72, 18);
	panel2.add(lblNewLabel_14);
	
	JLabel lblNewLabel_15 = new JLabel("密码：");
	lblNewLabel_15.setForeground(color);
	lblNewLabel_15.setFont(font1);
	lblNewLabel_15.setBounds(344, 239, 66, 18);
	panel2.add(lblNewLabel_15);
		

	JButton button = new JButton("确定");
	button.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			button.setForeground(Color.BLUE);
		}
		public void mouseExited(MouseEvent e) {
			button.setForeground(Color.BLACK);
		}
		/**
		 * 确认
		 * @param e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			menuBar.setVisible(true);
			panel2.setVisible(false);
			panel2.updateUI();
			frame.remove(panel2);
			
			
		}
	});
	button.setForeground(Color.BLACK);
	button.setFont(font2);
	button.setBounds(394, 399, 128, 46);
	panel2.add(button);
	
	JLabel label1 = new JLabel("用户信息如下");
	label1.setForeground(Color.ORANGE);
	label1.setFont(new Font("黑体",Font.PLAIN, 40));
	label1.setBounds(224, 56, 255, 40);
	panel2.add(label1);
	
	JLabel lblNewLabel_8 = new JLabel(user.getUname());
	lblNewLabel_8.setFont(font1);
	lblNewLabel_8.setBounds(211, 161, 119, 20);
	panel2.add(lblNewLabel_8);
	
	JLabel lblNewLabel_9 = new JLabel(user.getSex());
	lblNewLabel_9.setFont(font1);
	lblNewLabel_9.setBounds(211, 239, 115, 20);
	panel2.add(lblNewLabel_9);
	
	JLabel lblNewLabel_10 = new JLabel(Integer.toString(user.getAge()));
	lblNewLabel_10.setFont(font1);
	lblNewLabel_10.setBounds(207, 320, 123, 20);
	panel2.add(lblNewLabel_10);
	
	JLabel lblNewLabel_11 = new JLabel(StringUtil.DataToString(user.getBirthday()));
	lblNewLabel_11.setFont(font1);
	lblNewLabel_11.setBounds(211, 413, 144, 20);
	panel2.add(lblNewLabel_11);
	
	JLabel lblNewLabel_12 = new JLabel(user.getUaccount());
	lblNewLabel_12.setFont(font1);
	lblNewLabel_12.setBounds(409, 163, 183, 25);
	panel2.add(lblNewLabel_12);
	
	JLabel lblNewLabel_19 = new JLabel(user.getUpwd());
	lblNewLabel_19.setFont(font1);
	lblNewLabel_19.setBounds(419, 234, 173, 25);
	panel2.add(lblNewLabel_19);
		
	panel2.updateUI();
		
		
		
		
		
	}
	/**
	 * 显示修改用户的界面
	 */
	public void showUpdateUserPanel(JMenuBar menuBar,User user) {
		
		JPanel panel2 = new JPanel() ;
		panel2.setOpaque(false);
	
	panel2.setBounds(0, 0, 689, 541);
	panel2.setLayout(null);
	panel2.setVisible(true);
	frame.getContentPane().add(panel2);
	Font font1 = new Font("黑体", Font.PLAIN, 20);
	Font font2 = new Font("黑体", Font.PLAIN, 20);
	Color color = Color.WHITE;
	
	JLabel label = new JLabel("姓名：");
	label.setForeground(color);
	label.setFont(font1);
	label.setBounds(59, 163, 72, 18);
	panel2.add(label);
	
	JLabel label_1 = new JLabel("性别：");
	label_1.setForeground(color);
	label_1.setFont(font1);
	label_1.setBounds(59, 239, 72, 18);
	panel2.add(label_1);
	
	JLabel label_2 = new JLabel("年龄：");
	label_2.setForeground(color);
	label_2.setFont(font1);
	label_2.setBounds(59, 322, 72, 18);
	panel2.add(label_2);
	
	JLabel lblNewLabel_13 = new JLabel("出生日期：");
	lblNewLabel_13.setForeground(color);
	lblNewLabel_13.setFont(font1);
	lblNewLabel_13.setBounds(59, 413, 119, 18);
	panel2.add(lblNewLabel_13);
	
	JLabel lblNewLabel_14 = new JLabel("账号：");
	lblNewLabel_14.setForeground(color);
	lblNewLabel_14.setFont(font1);
	lblNewLabel_14.setBounds(319, 163, 72, 18);
	panel2.add(lblNewLabel_14);
	
	JLabel lblNewLabel_15 = new JLabel("密码：");
	lblNewLabel_15.setForeground(color);
	lblNewLabel_15.setFont(font1);
	lblNewLabel_15.setBounds(319, 239, 72, 18);
	panel2.add(lblNewLabel_15);
	

	JTextField textField = new JTextField();//姓名内容
	textField.setFont(font1);
	textField.setText(user.getUname());
	textField.setBounds(130, 160, 157, 24);
	panel2.add(textField);
	textField.setColumns(10);
	
	JTextField textField_1 = new JTextField();//年龄内容
	textField_1.setFont(font1);
	textField_1.setText(String.valueOf(user.getAge()));
	textField_1.setBounds(130, 319, 86, 24);
	panel2.add(textField_1);
	textField_1.setColumns(10);
	
	JTextField textField_2 = new JTextField();//密码内容
	textField_2.setFont(font1);
	textField_2.setText(user.getUpwd());
	textField_2.setBounds(394, 236, 219, 24);
	panel2.add(textField_2);
	textField_2.setColumns(10);
	
	JLabel  lblNewLabel_16 = new JLabel("");//账号内容
	lblNewLabel_16.setFont(font1);
	lblNewLabel_16.setBounds(394, 56, 72, 18);
	panel2.add(lblNewLabel_16);
	
	
	
	ButtonGroup buttonGroup = new ButtonGroup();
	JRadioButton man = new JRadioButton("男");
	man.setOpaque(false);
	man.setFont(font1);
	man.setBounds(125, 235, 53, 27);
	buttonGroup.add(man);
	panel2.add(man);
	
	JRadioButton woman = new JRadioButton("女");
	woman.setOpaque(false);
	woman.setFont(font1);
	woman.setBounds(178, 235, 53, 27);
	buttonGroup.add(woman);
	panel2.add(woman);
	
	/**
	 * 判断用户性别，初始化性别选项
	 */
	if(user.getSex().equals("男")) {
		man.setSelected(true);
	}else {
		woman.setSelected(true);
	}
	

	JLabel lblNewLabel_17 = new JLabel("New label");//账号内容
	lblNewLabel_17.setText(user.getUaccount());
	lblNewLabel_17.setFont(font1);
	lblNewLabel_17.setBounds(394, 163, 219, 18);
	panel2.add(lblNewLabel_17);
	
	JTextField textField_3 = new JTextField();//日期内容
	textField_3.setFont(font1);
	textField_3.setText(StringUtil.DataToString(user.getBirthday()));
	textField_3.setBounds(159, 412, 128, 24);
	panel2.add(textField_3);
	textField_3.setColumns(10);
	
	JButton button = new JButton("确认修改");
	button.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			button.setForeground(Color.BLUE);
		}
		public void mouseExited(MouseEvent e) {
			button.setForeground(Color.BLACK);
		}
		/**
		 * 确认修改个人信息
		 * @param e
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "确认修改当前信息", "提示", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {//确认修改
				
				//获取用户信息
				String name = textField.getText();
				String pwd = textField_2.getText();
				String account = user.getUaccount();
				Date birthday = null;
				String sex = null;
				int age = 0;
				
				if(man.isSelected()) {
					sex = man.getText();
				}else {
					sex = woman.getText();
				}
								
				//进行年龄验证
				if(!textField_1.getText().matches("[1-9][0-9]*")) {
					JOptionPane.showMessageDialog(null, "年龄输入格式错误", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					age = Integer.parseInt(textField_1.getText());
				}
				
				//进行姓名，密码验证
				if(name.equals("")||name==null) {
					JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//进行密码验证
				if(!pwd.matches("[0-9a-zA-Z]{6,}")) {
					JOptionPane.showMessageDialog(null, "密码必须由数字或字母构成且不能少于6位", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
				//进行日期验证
				if(!textField_3.getText().matches("^((19[2-9]\\d{1})|(20((0[0-9])|(1[0-8]))))\\-((0?[1-9])|(1[0-2]))\\-((0?[1-9])|([1-2][0-9])|30|31)$")) {
					JOptionPane.showMessageDialog(null, "请输入yyyy-MM-dd格式的日期", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}else {
					birthday = StringUtil.StringToDate(textField_3.getText());
				}
				
				//创建新用户对象
				User nweUser = new User(account, pwd, name, age, sex, birthday);
				//更新用户数据		
				if(new UserOperate().modifyPersonalInfo(nweUser)) {//更新成功
					JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					panel2.setVisible(false);
					menuBar.setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null, "修改信息失败", "错误", JOptionPane.ERROR_MESSAGE);
					return;
				}
					
			}else {
				return;
			}
			
		}
	});
	button.setForeground(Color.BLACK);
	button.setFont(font2);
	button.setBounds(394, 335, 128, 46);
	panel2.add(button);
	
	JButton button_1 = new JButton("取消");
	button_1.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseEntered(MouseEvent e) {
			button_1.setForeground(Color.RED);
		}
		public void mouseExited(MouseEvent e) {
			button_1.setForeground(Color.BLACK);
		}
		/**
		 * 取消修改
		 */
		@Override
		public void mouseClicked(MouseEvent e) {
			int option = JOptionPane.showConfirmDialog(null, "是否取消修改个人信息", "提示", JOptionPane.YES_NO_OPTION);
			if(option==JOptionPane.YES_OPTION) {//确认退出
				menuBar.setVisible(true);
				panel2.setVisible(false);
				panel2.updateUI();
				frame.remove(panel2);
				
			}else {
				return;
			}
		}
	});
	button_1.setForeground(Color.BLACK);
	button_1.setFont(font2);
	button_1.setBounds(394, 413, 128, 46);
	panel2.add(button_1);
	
	JLabel label1 = new JLabel("修改用户信息");
	label1.setForeground(Color.ORANGE);
	label1.setFont(new Font("黑体",Font.PLAIN, 40));
	label1.setBounds(224, 56, 255, 40);
	panel2.add(label1);
		
	panel2.updateUI();
		
	}
	/**
	 * 显示新建用户的界面
	 */
	public void showAddUserPanel(JMenuBar menuBar) {
		
		
		 
		JPanel panel2 = new JPanel();
		panel2.setOpaque(false);
		panel2.setBounds(0, 0, 689, 541);
		panel2.setLayout(null);
		panel2.setVisible(true);
		frame.getContentPane().add(panel2);
		Font font1 = new Font("黑体", Font.PLAIN, 20);
		Font font2 = new Font("黑体", Font.PLAIN, 20);
		Color color = Color.WHITE;//new Color(72, 61, 139);
		
		JLabel label = new JLabel("姓名：");
		label.setForeground(color);
		label.setFont(font1);
		label.setBounds(59, 163, 72, 18);
		panel2.add(label);
		
		JLabel label_1 = new JLabel("性别：");
		label_1.setForeground(color);
		label_1.setFont(font1);
		label_1.setBounds(59, 239, 72, 18);
		panel2.add(label_1);
		
		JLabel label_2 = new JLabel("年龄：");
		label_2.setForeground(color);
		label_2.setFont(font1);
		label_2.setBounds(59, 322, 72, 18);
		panel2.add(label_2);
		
		JLabel lblNewLabel_13 = new JLabel("出生日期：");
		lblNewLabel_13.setForeground(color);
		lblNewLabel_13.setFont(font1);
		lblNewLabel_13.setBounds(59, 413, 119, 18);
		panel2.add(lblNewLabel_13);
		
		JLabel lblNewLabel_14 = new JLabel("账号：");
		lblNewLabel_14.setForeground(color);
		lblNewLabel_14.setFont(font1);
		lblNewLabel_14.setBounds(319, 163, 72, 18);
		panel2.add(lblNewLabel_14);
		
		JLabel lblNewLabel_15 = new JLabel("密码：");
		lblNewLabel_15.setForeground(color);
		lblNewLabel_15.setFont(font1);
		lblNewLabel_15.setBounds(319, 239, 72, 18);
		panel2.add(lblNewLabel_15);
		

		JTextField textField = new JTextField();//姓名内容
		textField.setFont(font1);
		textField.setBounds(130, 160, 157, 24);
		panel2.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();//年龄内容
		textField_1.setFont(font1);
		textField_1.setBounds(130, 319, 86, 24);
		panel2.add(textField_1);
		textField_1.setColumns(10);
		
		JTextField textField_2 = new JTextField();//密码内容
		textField_2.setFont(font1);
		textField_2.setBounds(394, 236, 219, 24);
		panel2.add(textField_2);
		textField_2.setColumns(10);
		
		JLabel  lblNewLabel_16 = new JLabel("");//账号内容
		lblNewLabel_16.setFont(font1);
		lblNewLabel_16.setBounds(394, 56, 72, 18);
		panel2.add(lblNewLabel_16);
		
		
		
		ButtonGroup buttonGroup = new ButtonGroup();
		JRadioButton man = new JRadioButton("男");
		man.setOpaque(false);
		man.setSelected(true);
		man.setFont(font1);
		man.setBounds(125, 235, 53, 27);
		buttonGroup.add(man);
		panel2.add(man);
		
		JRadioButton woman = new JRadioButton("女");
		woman.setOpaque(false);
		woman.setFont(font1);
		woman.setBounds(178, 235, 53, 27);
		buttonGroup.add(woman);
		panel2.add(woman);
		
		JTextField textField_3 = new JTextField();//日期内容
		textField_3.setFont(font1);
		textField_3.setBounds(159, 412, 128, 24);
		panel2.add(textField_3);
		textField_3.setColumns(10);
		
		JTextField textField_4 = new JTextField();//账号内容
		textField_4.setFont(font1);
		textField_4.setBounds(394, 163, 219, 23);
		panel2.add(textField_4);
		textField_4.setColumns(10);
			
		
		JButton button = new JButton("新建用户");
		button.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button.setForeground(Color.BLUE);
			}
			public void mouseExited(MouseEvent e) {
				button.setForeground(Color.BLACK);
			}
			/**
			 * 确认修改个人信息
			 * @param e
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "确认修改当前信息", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认修改
					
					//获取用户信息
					String name = textField.getText();
					String pwd = textField_2.getText();
					String account =textField_4.getText();
					Date birthday = null;
					String sex = null;
					int age = 0;
					
					if(man.isSelected()) {
						sex = man.getText();
					}else {
						sex = woman.getText();
					}
					
					//进行账户验证
					if(!account.matches("[0-9a-zA-Z]{6,}")) {
						JOptionPane.showMessageDialog(null, "账号必须由数字或字母构成且不能少于6位", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}else if(new UserQuery().checkAccount(account)) {
						JOptionPane.showMessageDialog(null, "账号已存在，请重新输入", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//进行年龄验证
					if(!textField_1.getText().matches("[1-9][0-9]*")) {
						JOptionPane.showMessageDialog(null, "年龄输入格式错误", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}else {
						age = Integer.parseInt(textField_1.getText());
					}
					
					//进行姓名，密码验证
					if(name.equals("")||name==null) {
						JOptionPane.showMessageDialog(null, "姓名不能为空", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//进行密码验证
					if(!pwd.matches("[0-9a-zA-Z]{6,}")) {
						JOptionPane.showMessageDialog(null, "密码必须由数字或字母构成且不能少于6位", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
					//进行日期验证
					if(!textField_3.getText().matches("^((19[2-9]\\d{1})|(20((0[0-9])|(1[0-8]))))\\-((0?[1-9])|(1[0-2]))\\-((0?[1-9])|([1-2][0-9])|30|31)$")) {
						JOptionPane.showMessageDialog(null, "请输入yyyy-MM-dd格式的日期", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}else {
						birthday = StringUtil.StringToDate(textField_3.getText());
					}
					
					//创建新用户对象
					User user = new User(account, pwd, name, age, sex, birthday);
					if(manaop.addUser(user)) {//新建成功
						JOptionPane.showMessageDialog(null, "新建用户成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						manaop.buildRoot(user);//新建权限
						panel2.setVisible(false);
						menuBar.setVisible(true);
					}else{
						JOptionPane.showMessageDialog(null, "新建用户失败", "错误", JOptionPane.ERROR_MESSAGE);
						return;
					}
						
				}else {
					return;
				}
				
			}
		});
		button.setForeground(Color.BLACK);
		button.setFont(font2);
		button.setBounds(394, 335, 128, 46);
		panel2.add(button);
		
		JButton button_1 = new JButton("取消");
		button_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				button_1.setForeground(Color.RED);
			}
			public void mouseExited(MouseEvent e) {
				button_1.setForeground(Color.BLACK);
			}
			/**
			 * 取消修改
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否取消修改个人信息", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					menuBar.setVisible(true);
					panel2.setVisible(false);
					panel2.updateUI();
					frame.remove(panel2);
					
				}else {
					return;
				}
			}
		});
		button_1.setForeground(Color.BLACK);
		button_1.setFont(font2);
		button_1.setBounds(394, 413, 128, 46);
		panel2.add(button_1);
		
		JLabel label1 = new JLabel("新建用户");
		label1.setForeground(Color.ORANGE);
		label1.setFont(new Font("黑体",Font.PLAIN, 40));
		label1.setBounds(264, 56, 171, 40);
		panel2.add(label1);
		
		
		panel2.updateUI();
		
		
		 
		
	}
	/**
	 * 显示删除光盘的面板
	 */
	public void showDelectCDPanel(JMenuBar menuBar,CD cd) {
		
		
		JPanel panel =  new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		Color color = Color.WHITE;

		JLabel lblNewLabel_1 = new JLabel("删除光盘信息");
		lblNewLabel_1.setForeground(Color.RED);
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(230, 16, 422, 55);
		panel.add(lblNewLabel_1);
		
		Font font1 = new Font("黑体", Font.PLAIN, 25);
		
		JLabel lblNewLabel_2 = new JLabel("光盘编号:");
		lblNewLabel_2.setForeground(color);
		lblNewLabel_2.setFont(font1);
		lblNewLabel_2.setBounds(220, 131, 125, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("光盘名称:");
		lblNewLabel_3.setForeground(color);
		lblNewLabel_3.setFont(font1);
		lblNewLabel_3.setBounds(220, 224, 125, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("光盘类型:");
		lblNewLabel_4.setForeground(color);
		lblNewLabel_4.setFont(font1);
		lblNewLabel_4.setBounds(220, 316, 125, 30);
		panel.add(lblNewLabel_4);
		
		JButton btnNewButton_1 = new JButton("删除光盘");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.RED);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否删除当前光盘", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {
						/**
						 * 删除光盘
						 */
						if(manaop.deleteCD(cd)) {
							JOptionPane.showMessageDialog(null, "光盘删除成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							menuBar.setVisible(true);
							panel.setVisible(false);
							panel.updateUI();
							frame.remove(panel);
						}else {
							JOptionPane.showMessageDialog(null, "光盘删除失败", "提示", JOptionPane.ERROR_MESSAGE);
						}

					
				}else {
					return;
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(193, 425, 125, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否取消修改光盘？", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					menuBar.setVisible(true);
					panel.setVisible(false);
					panel.updateUI();
					frame.remove(panel);
				}else {
					return;
				}
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(383, 425, 125, 45);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel(cd.getCid());//光盘编号
		lblNewLabel_5.setFont(font1);
		lblNewLabel_5.setBounds(361, 131, 210, 28);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel(cd.getCname());//光盘名称
		lblNewLabel_6.setFont(font1);
		lblNewLabel_6.setBounds(361, 224, 212, 28);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel(cd.getType());//光盘类型
		lblNewLabel_7.setFont(font1);
		lblNewLabel_7.setBounds(361, 316, 212, 28);
		panel.add(lblNewLabel_7);
		
		panel.updateUI();
		
		
		
		
		
		
		
		
	}
	/**
	 * 生成查询CD面板
	 * @param cdArrays 存放cd对象的数组
	 */
	public void queryCDPanel(List<CD> cdList,JMenuBar menuBar) {
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
   		
		String[] columnNames = { "光盘编号", "光盘名称", "光盘类型", "借阅者"};  
		/**
		 * 调用工具方法，将CD列表转换成适合管理员表格的二位数组
		 */
        Object[][] obj = ConversionUtil.cDToArray_Manager(cdList); 
        JTable table = new JTable(obj, columnNames);  
        JTableHeader  tableH = table.getTableHeader();
        table.setRowHeight(25);
        //设置表头的背景色
        tableH.setBackground(Color.YELLOW);
        tableH.setPreferredSize(new Dimension(tableH.getWidth(),35));
        tableH.setFont(new Font("TimesRoman", Font.BOLD, 18));
        /* 
                 * 设置宽度
         */  
        TableColumn column = null;  
        int colunms = table.getColumnCount();  
        for(int i = 0; i < colunms; i++)  
        {  
            column = table.getColumnModel().getColumn(i);
            column.setPreferredWidth(169);  
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        table.setFont(new Font("黑体", Font.PLAIN, 15));
          
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setBounds(0, 62, 680, 450);
          
          
        panel.add(scroll);
        
        JLabel lblNewLabel_18 = new JLabel("查询结果如下");
        lblNewLabel_18.setForeground(Color.BLUE);
        lblNewLabel_18.setFont(new Font("黑体", Font.PLAIN, 30));
        
        lblNewLabel_18.setBounds(45, 13, 207, 36);
        panel.add(lblNewLabel_18);
        
        JButton btnNewButton_1 = new JButton("确定");
        btnNewButton_1.addMouseListener(new MouseAdapter() {
        	@Override
			public void mouseEntered(MouseEvent e) {
        		btnNewButton_1.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			/**
			 * 退出界面
			 */
        	@Override
        	public void mousePressed(MouseEvent e) {
        		menuBar.setVisible(true);
        		panel.setVisible(false);
        		panel.updateUI();
        		frame.remove(panel);
        		
        		
        	}
        });
        btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 25));
        btnNewButton_1.setBounds(416, 22, 116, 27);
        panel.add(btnNewButton_1);
		
       
        
        panel.updateUI();
	}

	/**
	 * 显示修改光盘的面板
	 */
	public void showUpdateCDPanel(JMenuBar menuBar,CD cd) {
		
		JPanel panel =  new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		

		JLabel lblNewLabel_1 = new JLabel("修改光盘信息");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(230, 16, 422, 55);
		panel.add(lblNewLabel_1);
		
		Font font1 = new Font("黑体", Font.PLAIN, 25);
		Color color = Color.WHITE;
		
		JLabel lblNewLabel_2 = new JLabel("光盘编号:");
		lblNewLabel_2.setForeground(color);
		lblNewLabel_2.setFont(font1);
		lblNewLabel_2.setBounds(120, 131, 125, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("光盘名称:");
		lblNewLabel_3.setForeground(color);
		lblNewLabel_3.setFont(font1);
		lblNewLabel_3.setBounds(120, 224, 125, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("光盘类型:");
		lblNewLabel_4.setForeground(color);
		lblNewLabel_4.setFont(font1);
		lblNewLabel_4.setBounds(120, 316, 125, 30);
		panel.add(lblNewLabel_4);
		
		JTextField textField_1 = new JTextField();//光盘名称
		textField_1.setFont(font1);
		textField_1.setText(cd.getCname());
		textField_1.setBounds(259, 228, 212, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		String [] cDType = {"文学","娱乐","音乐","教育","历史","科普","惊悚"};   
		JComboBox comboBox = new JComboBox(cDType);
		comboBox.setFont(new Font("黑体", Font.PLAIN, 20));
		comboBox.setSelectedItem(cd.getType());
		comboBox.setBounds(260, 318, 125, 31);
		comboBox.setMaximumRowCount(4);
		panel.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("修改光盘");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否修改当前光盘", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {
						/**
						 * 修改光盘
						 */
						cd.setCname(textField_1.getText());
						cd.setType((String) comboBox.getSelectedItem());
					
						if(manaop.updateCD(cd)) {
							JOptionPane.showMessageDialog(null, "光盘修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							menuBar.setVisible(true);
							panel.setVisible(false);
							panel.updateUI();
							frame.remove(panel);
						}else {
							JOptionPane.showMessageDialog(null, "光盘修改失败", "提示", JOptionPane.ERROR_MESSAGE);
						}

					
				}else {
					return;
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(193, 425, 125, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否取消修改光盘？", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					menuBar.setVisible(true);
					panel.setVisible(false);
					panel.updateUI();
					frame.remove(panel);
				}else {
					return;
				}
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(383, 425, 125, 45);
		panel.add(btnNewButton_2);
		
		JLabel lblNewLabel_5 = new JLabel(cd.getCid());
		lblNewLabel_5.setFont(font1);
		lblNewLabel_5.setBounds(261, 131, 210, 28);
		panel.add(lblNewLabel_5);
		
		panel.updateUI();
		
		
		
		
	}
	
	/**
	 * 显示新建光盘的面板
	 */
	public void showAddCDPanel(JMenuBar menuBar) {
		
		JPanel panel =  new JPanel() ;
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
		
		
		JLabel lblNewLabel_1 = new JLabel("新建光盘，请输入光盘信息");
		lblNewLabel_1.setForeground(Color.ORANGE);
		lblNewLabel_1.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel_1.setBounds(132, 16, 422, 55);
		panel.add(lblNewLabel_1);
		
		Color color = Color.WHITE;
		Font font1 = new Font("黑体", Font.PLAIN, 25);
		
		JLabel lblNewLabel_2 = new JLabel("光盘编号:");
		lblNewLabel_2.setForeground(color);
		lblNewLabel_2.setFont(font1);
		lblNewLabel_2.setBounds(120, 131, 125, 30);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("光盘名称:");
		lblNewLabel_3.setForeground(color);
		lblNewLabel_3.setFont(font1);
		lblNewLabel_3.setBounds(120, 224, 125, 30);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("光盘类型:");
		lblNewLabel_4.setForeground(color);
		lblNewLabel_4.setFont(font1);
		lblNewLabel_4.setBounds(120, 316, 125, 30);
		panel.add(lblNewLabel_4);
		
		JTextField textField = new JTextField();//光盘编号
		textField.setFont(font1);
		textField.setBounds(259, 131, 211, 31);
		panel.add(textField);
		textField.setColumns(10);
		
		JTextField textField_1 = new JTextField();//光盘名称
		textField_1.setFont(font1);
		textField_1.setBounds(259, 228, 212, 31);
		panel.add(textField_1);
		textField_1.setColumns(10);
		
		String [] cDType = {"文学","娱乐","音乐","教育","历史","科普","惊悚"};   
		JComboBox comboBox = new JComboBox(cDType);
		comboBox.setFont(new Font("黑体", Font.PLAIN, 20));
		comboBox.setBounds(260, 318, 125, 31);
		comboBox.setMaximumRowCount(4);
		panel.add(comboBox);
		
		JButton btnNewButton_1 = new JButton("新建光盘");
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_1.setForeground(Color.BLACK);
			}
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否创建当前光盘", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					/**
					 * 新建光盘
					 */
					CD cd = new CD();
					cd.setCid(textField.getText());
					cd.setCname(textField_1.getText());
					cd.setType((String) comboBox.getSelectedItem());
					if(manaop.checkCDId(cd)) {//光盘编号存在，无法创建
						JOptionPane.showMessageDialog(null, "此光盘编号已存在", "提示", JOptionPane.ERROR_MESSAGE);
					}else if(cd.getCname().equals("")) {
						JOptionPane.showMessageDialog(null, "光盘名称不能为空", "提示", JOptionPane.ERROR_MESSAGE);
					}else{
						if(manaop.insertCD(cd)) {
							JOptionPane.showMessageDialog(null, "光盘创建成功", "提示", JOptionPane.INFORMATION_MESSAGE);
							menuBar.setVisible(true);
							panel.setVisible(false);
							panel.updateUI();
							frame.remove(panel);
						}else {
							JOptionPane.showMessageDialog(null, "光盘创建失败", "提示", JOptionPane.ERROR_MESSAGE);
						}
					}
					
					
					
				}else {
					return;
				}
			}
		});
		btnNewButton_1.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_1.setBounds(193, 425, 125, 45);
		panel.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("取消");
		btnNewButton_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_2.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否取消新建光盘？", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {//确认退出
					menuBar.setVisible(true);
					panel.setVisible(false);
					panel.updateUI();
					frame.remove(panel);
				}else {
					return;
				}
			}
		});
		btnNewButton_2.setFont(new Font("黑体", Font.PLAIN, 20));
		btnNewButton_2.setBounds(383, 425, 125, 45);
		panel.add(btnNewButton_2);
		
		panel.updateUI();
		
	}
	/**
	 * 显示用户借阅记录面板
	 */
	public void ShowBorrowedRecordPanel(JMenuBar menuBar,List<Record> recordList) {
		
		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setLayout(null);
		panel.setBounds(0, 0, 689, 541);
		
		
		JTextArea textArea = new JTextArea();
		//设置不可编辑
		textArea.setEditable(false); 
		textArea.setFont(new Font("黑体", Font.PLAIN, 20));
		
		
		/**
		 * 处理借阅信息列表
		 */
//		for(Record record:recordList) {
//			String account = record.getUserAccount();
//			String action = record.getAction();
//			String cid = record.getCdId();
//			String btime = StringUtil.DataToString_02(record.getBtime());
//			textArea.append("用户【"+account+"】于【"+btime+"】【"+action+"】了【"+cid+"】光盘\r\n");
//				
//		}
//		
		for(int i=0;i<recordList.size();i++) {
			String account = recordList.get(i).getUserAccount();
			String action = recordList.get(i).getAction();
			String cid = recordList.get(i).getCdId();
			String btime = StringUtil.DataToString_02(recordList.get(i).getBtime());
			if(i>0) {
				String lastBtime = StringUtil.DataToString_02(recordList.get(i-1).getBtime());
				if(!lastBtime.substring(0, 11).equals(btime.substring(0, 11))) {//日期间隔一天则分割
					textArea.append("-------------------------------------------------------------\r\n");
				}
			}
			textArea.append("用户【"+account+"】于【"+btime+"】【"+action+"】了【"+cid+"】光盘\r\n");
		}
		textArea.setBounds(0, 74, 689, 467);
		textArea.setLineWrap(false);
		
		JScrollPane jsp=new JScrollPane(textArea);
		jsp.setOpaque(true);//设置透明
		jsp.getViewport().setOpaque(false);
		jsp.setBounds(0, 65, 680, 376);
		panel.add(jsp);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("用户借阅记录");
		lblNewLabel.setForeground(Color.BLUE);
		lblNewLabel.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel.setBounds(233, 13, 210, 39);
		panel.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("确定");
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
			 * 退出界面
			 */
        	@Override
        	public void mousePressed(MouseEvent e) {
        		menuBar.setVisible(true);
        		panel.setVisible(false);
        		panel.updateUI();
        		frame.remove(panel);	
        	}
			
		});
		btnNewButton.setFont(new Font("黑体", Font.PLAIN, 25));
		btnNewButton.setBounds(286, 455, 113, 31);
		panel.add(btnNewButton);
		
		
		panel.updateUI();
		
		
	
		
		
	}
}

