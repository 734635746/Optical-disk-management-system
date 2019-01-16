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

import po.CD;
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

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.JTableHeader;
import javax.swing.table.TableColumn;

import ManagerDao.managerOperateInt;
import UserDao.UserOperate;

import javax.swing.JRadioButton;
import java.awt.Button;
import java.awt.Label;
import javax.swing.JPasswordField;
/**
 * 用户操作界面
 * @author liuyoubin
 *
 */
public class UserOperateWindow {

	private JFrame frame;
	private User user;//用户对象，一个操作界面一个对象，标识唯一用户
	private UserOperate userop;//用户操作模块
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTable table;
	private JTable table_1;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					UserOperateWindow window = new UserOperateWindow();
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
	public UserOperateWindow() {
		initialize();
	}
	
	/**
	 * Create the application.
	 */
	public UserOperateWindow(User user) {
		this.user = user;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("欢迎【"+user.getUaccount()+"】使用光盘借阅系统");
		frame.setBounds(600, 250, 689, 541);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setUndecorated(true); // 去掉窗口的装饰 
		frame.getRootPane().setWindowDecorationStyle(JRootPane.PLAIN_DIALOG);//采用指定的窗口装饰风格 
		//禁止调整大小
		frame.setResizable(false); 
		/**
		 * 用户操作方法模块
		 */
		UserOperate userOp = new UserOperate();
		/**
		 * 添加界面背景
		 */
		JPanel bj = new JPanel() {
			
			@Override
			protected void paintComponent(Graphics g) {
				Image image;
				try {
					image = ImageIO.read(new File("src/Image/8.jpg"));
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
				int option = JOptionPane.showConfirmDialog(null, "是否退出", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) { 
					frame.dispose();
				}
		}		
			          
		});
		
		
		JPanel panel2 = new JPanel();
		
		
		Font font = new Font("黑体", Font.PLAIN, 20);
		JMenuBar menuBar = new JMenuBar();
		menuBar.setBounds(0, 0, 689, 38);
		frame.getContentPane().add(menuBar);
		
		JMenu mnNewMenu = new JMenu("个人信息");
		mnNewMenu.setFont(font);
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
		menuBar.add(mnNewMenu);
		
		JMenuItem mntmNewMenuItem = new JMenuItem("查看个人信息");
		mntmNewMenuItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/**
				 * 查看个人信息
				 */
				menuBar.setVisible(false);
				//刷新面板
				queryPanelInit(menuBar);
				
			}
		});
		mntmNewMenuItem.setFont(font);
		mnNewMenu.add(mntmNewMenuItem);
		
		JMenuItem mntmNewMenuItem_1 = new JMenuItem("修改个人信息");
		mntmNewMenuItem_1.addMouseListener(new MouseAdapter() {
			/**
			 * 修改个人信息
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				//查看修改权限
				String modifyRoot = userOp.queryRoot(user.getUaccount()).getModifyRoot();
				if(modifyRoot==null) {
					JOptionPane.showMessageDialog(null, "您没有修改个人信息的权限，请联系管理员", "提示", JOptionPane.ERROR_MESSAGE);
				}else if(modifyRoot.equals("启用")) {
					menuBar.setVisible(false);
					//刷新面板
					modifyPanelInit(menuBar);
				}else {
					JOptionPane.showMessageDialog(null, "您没有修改个人信息的权限，请联系管理员", "提示", JOptionPane.ERROR_MESSAGE);
				}
			}
		});
		mntmNewMenuItem_1.setFont(font);
		mnNewMenu.add(mntmNewMenuItem_1);
		JMenu menu = new JMenu("光盘管理");
		menu.setFont(font);
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
		menuBar.add(menu);
		
		JMenuItem mntmNewMenuItem_2 = new JMenuItem("借阅光盘");
		mntmNewMenuItem_2.addMouseListener(new MouseAdapter() {
			/**
			 * 借阅光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				//查看借阅权限
				String borrowRoot = userOp.queryRoot(user.getUaccount()).getBorrowRoot();
				if(borrowRoot==null) {
					JOptionPane.showMessageDialog(null, "您没有借阅光盘的权限，请联系管理员", "提示", JOptionPane.ERROR_MESSAGE);
				}else if(borrowRoot.equals("启用")) {
					/**
					 * 允许借阅
					 */
					String inputID = JOptionPane.showInputDialog("请输入将借阅的光盘编号");
					//待借阅CD对象
					CD cd = userOp.queryCD(inputID);
					if(cd==null) {	
						JOptionPane.showMessageDialog(null, "此光盘不存在", "提示", JOptionPane.ERROR_MESSAGE); 
					}else if(cd.getBorrowed()!=null) {//判断是否已被借阅
						JOptionPane.showMessageDialog(null, "此光盘已经被借阅", "提示", JOptionPane.INFORMATION_MESSAGE); 
					}else {//借阅
						if(userOp.borrowCD(cd, user)) {
							//借阅成功则更新记录表
							userOp.recordBorrowCD(user, cd);
							JOptionPane.showMessageDialog(null, "光盘借阅成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						}else {
							JOptionPane.showMessageDialog(null, "光盘借阅失败", "提示", JOptionPane.ERROR_MESSAGE); 
						}
					}
				}else {
					JOptionPane.showMessageDialog(null, "您没有借阅光盘的权限，请联系管理员", "提示", JOptionPane.ERROR_MESSAGE);
				}
			
			
			}
		});
		mntmNewMenuItem_2.setFont(font);
		menu.add(mntmNewMenuItem_2);
		
		JMenuItem mntmNewMenuItem_3 = new JMenuItem("归还光盘");
		mntmNewMenuItem_3.addMouseListener(new MouseAdapter() {
			/**
			 * 归还光盘
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				String inputID = JOptionPane.showInputDialog("请输入将归还的光盘编号");
				//待归还CD对象
				CD cd = userOp.queryCD(inputID);
				if(cd==null) {
					JOptionPane.showMessageDialog(null, "此光盘不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}else if(cd.getBorrowed()==null){
					JOptionPane.showMessageDialog(null, "您尚未借阅该光盘", "提示", JOptionPane.INFORMATION_MESSAGE); 
				}else if(!cd.getBorrowed().equals(user.getUaccount())) {//判断是否已被自己借阅
					JOptionPane.showMessageDialog(null, "您尚未借阅该光盘", "提示", JOptionPane.INFORMATION_MESSAGE); 
				}else {//归还
					if(userOp.returnCD(cd)) {
						//归还成功则更新记录表
						userOp.recordReturnCD(user, cd);
						JOptionPane.showMessageDialog(null, "光盘归还成功", "提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "光盘归还失败", "提示", JOptionPane.ERROR_MESSAGE); 
					}
				}
			}
		});
		mntmNewMenuItem_3.setFont(font);
		menu.add(mntmNewMenuItem_3);
		
		JMenu menu_1 = new JMenu("查询光盘");
		menu_1.setFont(font);
		menu.add(menu_1);
		
		JMenuItem menuItem_1 = new JMenuItem("按编号查询");
		menuItem_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String inputID = JOptionPane.showInputDialog("请输入待查询的光盘编号");
				List<CD> cdList= new ArrayList<CD>();
				CD cd = null;
				//查询
				cd = userOp.queryCD(inputID);
				if(cd==null) {
					JOptionPane.showMessageDialog(null, "此光盘不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}else {
					cdList.add(cd);
					menuBar.setVisible(false);
					queryCDPanel(cdList,menuBar);
				}
				
			}
		});
		menuItem_1.setFont(font);
		menu_1.add(menuItem_1);
		
		JMenuItem menuItem_2 = new JMenuItem("按名称查询");
		menuItem_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String inputName = JOptionPane.showInputDialog("请输入待查询的光盘名称");
				List<CD> cdList= (List<CD>) userOp.queryCDs(inputName);
				
				if(cdList.size()==0||cdList==null) {
					JOptionPane.showMessageDialog(null, "此类光盘不存在", "提示", JOptionPane.ERROR_MESSAGE); 
				}else {
					menuBar.setVisible(false);
					queryCDPanel(cdList,menuBar);
				}
				
			}
		});
		menuItem_2.setFont(font);
		menu_1.add(menuItem_2);
		
		JMenu mnNewMenu_3 = new JMenu("按类型查询");
		mnNewMenu_3.setFont(font);
		menu_1.add(mnNewMenu_3);
		
		JMenuItem mntmNewMenuItem_7 = new JMenuItem("教育");
		mntmNewMenuItem_7.setFont(font);
		mntmNewMenuItem_7.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("教育", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_7);
		
		JMenuItem mntmNewMenuItem_8 = new JMenuItem("科普");
		mntmNewMenuItem_8.setFont(font);
		mntmNewMenuItem_8.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("科普", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_8);
		
		JMenuItem mntmNewMenuItem_9 = new JMenuItem("惊悚");
		mntmNewMenuItem_9.setFont(font);
		mntmNewMenuItem_9.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("惊悚", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_9);
		
		JMenuItem mntmNewMenuItem_10 = new JMenuItem("音乐");
		mntmNewMenuItem_10.setFont(font);
		mntmNewMenuItem_10.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("音乐", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_10);
		
		JMenuItem mntmNewMenuItem_11 = new JMenuItem("历史");
		mntmNewMenuItem_11.setFont(font);
		mntmNewMenuItem_11.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("历史", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_11);
		
		JMenuItem mntmNewMenuItem_12 = new JMenuItem("文学");
		mntmNewMenuItem_12.setFont(font);
		mntmNewMenuItem_12.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("文学", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_12);
		
		JMenuItem mntmNewMenuItem_13 = new JMenuItem("娱乐");
		mntmNewMenuItem_13.setFont(font);
		mntmNewMenuItem_13.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mousePressed(MouseEvent e) {
				queryCDByType("娱乐", menuBar);
				
			}
		});
		mnNewMenu_3.add(mntmNewMenuItem_13);
		
				JMenuItem menuItem_4 = new JMenuItem("查看可借阅光盘");
				menuItem_4.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						List<CD> cdList= (List<CD>) userOp.queryCDsCouldBeBorrowed();
						if(cdList.size()==0||cdList==null) {
							JOptionPane.showMessageDialog(null, "当前无可借阅光盘", "提示", JOptionPane.ERROR_MESSAGE);
						}else {
							menuBar.setVisible(false);
							queryCDPanel(cdList,menuBar);
						}
						}
				});
				menuItem_4.setFont(font);
				menu_1.add(menuItem_4);
		
			
			
			JMenuItem menuItem = new JMenuItem("已借阅光盘");
			menuItem.addMouseListener(new MouseAdapter() {
				/**
				 * 查看已借阅光盘
				 */
				@Override
				public void mousePressed(MouseEvent e) {
					List<CD> cdList= (List<CD>) userOp.queryCDsHaveBeBorrowed(user.getUaccount());
					
					if(cdList.size()==0||cdList==null) {
					
						JOptionPane.showMessageDialog(null, "您当前没有借阅任何光盘", "提示", JOptionPane.ERROR_MESSAGE);
					
					}else {
						menuBar.setVisible(false);
						queryCDPanel(cdList,menuBar);
					}	
				}
			});
			menuItem.setFont(font);
			menu.add(menuItem);
		
		JMenu mnNewMenu_1 = new JMenu("权限管理");
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
		
		JMenuItem mntmNewMenuItem_5 = new JMenuItem("查看个人权限");
		mntmNewMenuItem_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				/**
				 * 获得权限信息
				 */
				
				String useraccount = user.getUaccount();
				Root root =userOp.queryRoot(useraccount);
				if(root==null) {
					JOptionPane.showMessageDialog(null,"查询不到权限信息，请联系管理员", "提示", JOptionPane.ERROR_MESSAGE);
				}else {
					menuBar.setVisible(false);
					/**
					 * 调用面板
					 */
					showRootPanel(menuBar, root);
				}
			}
		});
		mntmNewMenuItem_5.setFont(font);
		mnNewMenu_1.add(mntmNewMenuItem_5);
		
		JMenuItem menuItem_3 = new JMenuItem("申请权限");
		menuItem_3.setFont(font);
		menuItem_3.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				Root root = userOp.queryRoot(user.getUaccount());
				showApplyRootPanel(menuBar,root);
			}
		});
		mnNewMenu_1.add(menuItem_3);
		
		JMenu menu_2 = new JMenu("系统");
		menu_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				menu_2.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				menu_2.setForeground(Color.BLACK);
			}
			
		});
		
		JMenu mnNewMenu_2 = new JMenu("借阅记录");
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
		
		mnNewMenu_2.setFont(font);
		menuBar.add(mnNewMenu_2);
		
		JMenuItem mntmNewMenuItem_6 = new JMenuItem("查询借阅记录");
		mntmNewMenuItem_6.addMouseListener(new MouseAdapter() {
			/**
			 * 查询借阅记录
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				List<Record> recordList= null;
				/**
				 * 调用查询方法
				 */
				recordList = userOp.queryRecordByUesrAccount(user.getUaccount());
				
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				ShowBorrowedRecordPanel(menuBar,recordList);
			}
		});
		mntmNewMenuItem_6.setFont(font);
		mnNewMenu_2.add(mntmNewMenuItem_6);
		menu_2.setFont(font);
		menuBar.add(menu_2);
		
		JMenuItem mntmNewMenuItem_4 = new JMenuItem("退出");
		mntmNewMenuItem_4.addMouseListener(new MouseAdapter() {
			/**
			 * 退出
			 */
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
		
		JMenuItem mntmNewMenuItem_14 = new JMenuItem("锁定");
		mntmNewMenuItem_14.addMouseListener(new MouseAdapter() {
			/**
			 * 锁定
			 */
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(false);
				/**
				 * 调用面板
				 */
				showLockPanel(menuBar);
				
			}
		});
		mntmNewMenuItem_14.setFont(font);
		menu_2.add(mntmNewMenuItem_14);
		mntmNewMenuItem_4.setFont(font);
		menu_2.add(mntmNewMenuItem_4);
		

	
		frame.setVisible(true);
	}
	/**
	 * 显示申请权限的面板
	 * @param menuBar
	 */
	public void showApplyRootPanel(JMenuBar menuBar,Root root) {
		
		JPanel panel = new JPanel();
		panel.setVisible(true);
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		Font font1 = new Font("黑体", Font.BOLD, 35);
		Font font2 = new Font("黑体", Font.BOLD, 20);
		
		JLabel lblNewLabel_26 = new JLabel("申 请 权 限");
		lblNewLabel_26.setFont(font1);
		lblNewLabel_26.setBounds(246, 94, 218, 56);
		panel.add(lblNewLabel_26);
		
		JButton btnNewButton_4 = new JButton("申请修改权限");
		
		if(root.getModifyRoot().equals("启用")) {
			btnNewButton_4.setEnabled(false);
		}else {
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
				int option = JOptionPane.showConfirmDialog(null, "是否申请此项权限", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {
					if(new UserOperate().applyModifyRoot(user.getUaccount())) {
						JOptionPane.showMessageDialog(null, "申请成功，请耐心等候", "提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "申请失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					return;
				}
			}
		});
		}
		btnNewButton_4.setFont(font2);
		btnNewButton_4.setBounds(254, 199, 210, 44);
		panel.add(btnNewButton_4);
		
		JButton btnNewButton_5 = new JButton("申请借阅权限");
		if(root.getBorrowRoot().equals("启用")) {
			btnNewButton_5.setEnabled(false);
		}else {
		btnNewButton_5.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_5.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_5.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				int option = JOptionPane.showConfirmDialog(null, "是否申请此项权限", "提示", JOptionPane.YES_NO_OPTION);
				if(option==JOptionPane.YES_OPTION) {
					if(new UserOperate().applyBorrowRoot(user.getUaccount())) {
						JOptionPane.showMessageDialog(null, "申请成功，请耐心等候", "提示", JOptionPane.INFORMATION_MESSAGE);
					}else {
						JOptionPane.showMessageDialog(null, "申请失败", "提示", JOptionPane.ERROR_MESSAGE);
					}
					
				}else {
					return;
				}
			}
		});
		}
		btnNewButton_5.setFont(font2);
		btnNewButton_5.setBounds(254, 298, 210, 44);
		panel.add(btnNewButton_5);
		
		JButton btnNewButton_6 = new JButton("确定");
		btnNewButton_6.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				btnNewButton_6.setForeground(Color.BLUE);
			}
			@Override
			public void mouseExited(MouseEvent e) {
				btnNewButton_6.setForeground(Color.BLACK);
			}
			@Override
			public void mousePressed(MouseEvent e) {
				menuBar.setVisible(true);
				panel.setVisible(false);
				panel.updateUI();
				frame.remove(panel);
				
			}
		});
		btnNewButton_6.setFont(font2);
		btnNewButton_6.setBounds(298, 404, 130, 39);
		panel.add(btnNewButton_6);
		
		panel.updateUI();
		
		
	}
	/**
	 * 按类型查询光盘
	 */
	public void queryCDByType(String type,JMenuBar menuBar) {
		
		List<CD> cdList= (List<CD>) new UserOperate().queryCDsWithType(type);
		
		if(cdList.size()==0||cdList==null) {
			JOptionPane.showMessageDialog(null, "此类光盘不存在", "提示", JOptionPane.ERROR_MESSAGE); 
		}else {
			menuBar.setVisible(false);
			queryCDPanel(cdList,menuBar);
		}
		
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
				if(pass.equals(user.getUpwd())) {
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
	 * 显示借阅记录面板
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
//			String action = record.getAction();
//			String cid = record.getCdId();
//			String btime = StringUtil.DataToString_02(record.getBtime());
//			textArea.append("  您   于  【"+btime+"】  【"+action+"】  了  【"+cid+"】光盘\r\n");
//		}
		for(int i=0;i<recordList.size();i++) {
			String action = recordList.get(i).getAction();
			String cid = recordList.get(i).getCdId();
			String btime = StringUtil.DataToString_02(recordList.get(i).getBtime());
			if(i>0) {
				String lastBtime = StringUtil.DataToString_02(recordList.get(i-1).getBtime());
				if(!lastBtime.substring(0, 11).equals(btime.substring(0, 11))) {//日期间隔一天则分割
					textArea.append("-------------------------------------------------------------\r\n");
				}
			}
			textArea.append("  您   于  【"+btime+"】  【"+action+"】  了  【"+cid+"】光盘\r\n");
		}
		
		textArea.setBounds(0, 74, 689, 467);
		textArea.setLineWrap(false);
		
		JScrollPane jsp=new JScrollPane(textArea);
		jsp.setOpaque(true);//设置透明
		jsp.getViewport().setOpaque(false);
		jsp.setBounds(0, 65, 680, 376);
		panel.add(jsp);
		frame.getContentPane().add(panel);
		
		JLabel lblNewLabel = new JLabel("借阅记录如下");
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
		lblNewLabel_19.setForeground(Color.BLUE);
		lblNewLabel_19.setFont(font1);
		lblNewLabel_19.setBounds(200, 170, 153, 38);
		panel.add(lblNewLabel_19);
		
		JLabel lblNewLabel_20 = new JLabel("借阅光盘权限：");
		lblNewLabel_20.setForeground(Color.BLUE);
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
		
		JLabel lblNewLabel_23 = new JLabel("权限信息");
		lblNewLabel_23.setForeground(Color.ORANGE);
		lblNewLabel_23.setFont(new Font("黑体", Font.BOLD, 35));
		lblNewLabel_23.setBounds(270, 69, 158, 38);
		panel.add(lblNewLabel_23);
		
		panel.updateUI();
		

		
	}
	/**
	 * 生成查询已借阅CD面板
	 * @param cdArrays 存放cd对象的数组
	 */
	public void queryCDBeBorrowedPanel(List<CD> cdList,JMenuBar menuBar) {
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
   		
		String[] columnNames = { "光盘编号", "光盘名称", "光盘类型", "借阅情况"};  
		/**
		 * 调用工具方法，将CD列表转换成适合表格的二位数组
		 */
        Object[][] obj = ConversionUtil.cDToArray(cdList);
  
        JTable table = new JTable(obj, columnNames);  
        JTableHeader  tableH = table.getTableHeader();
        table.setRowHeight(25);
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
            column.setPreferredWidth(169);  
        }
        
        table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);  
        JScrollPane scroll = new JScrollPane(table);
        scroll.getViewport().setOpaque(false);
        scroll.setOpaque(false);
        scroll.setBounds(0, 62, 689, 479);
          
        panel.add(scroll);
        
        JLabel lblNewLabel_18 = new JLabel("以下是您已经借阅的光盘");
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
	 * 生成查询CD面板
	 * @param cdArrays 存放cd对象的数组
	 */
	public void queryCDPanel(List<CD> cdList,JMenuBar menuBar) {
		

		JPanel panel = new JPanel();
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		frame.getContentPane().add(panel);
   		
		String[] columnNames = { "光盘编号", "光盘名称", "光盘类型", "借阅情况"};  
		/**
		 * 调用工具方法，将CD列表转换成适合表格的二位数组
		 */
        Object[][] obj = ConversionUtil.cDToArray(cdList); 
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
	 * 生成修改个人信息的面板
	 * @param panel
	 */
	private void modifyPanelInit(JMenuBar menuBar) {
		 
		JPanel panel2 = new JPanel() ;
		panel2.setOpaque(false);
		panel2.setBounds(0, 0, 689, 541);
		panel2.setLayout(null);
		panel2.setVisible(true);
		frame.getContentPane().add(panel2);
		Font font1 = new Font("黑体", Font.PLAIN, 20);
		Font font2 = new Font("黑体", Font.PLAIN, 20);
		Color color = new Color(72, 61, 139);
		
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
					boolean isSuccess = new UserOperate().modifyPersonalInfo(nweUser);
					
					if(isSuccess) {//更新成功
						JOptionPane.showMessageDialog(null, "修改成功", "提示", JOptionPane.INFORMATION_MESSAGE);
						user = nweUser;
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
		
		JLabel label1 = new JLabel("修改个人信息");
		label1.setForeground(Color.BLUE);
		label1.setFont(new Font("黑体",Font.PLAIN, 40));
		label1.setBounds(224, 56, 255, 40);
		panel2.add(label1);
			
		panel2.updateUI();
	}
	/**
	 * 查询个人信息的面板初始化
	 * @param panel
	 */
	private void queryPanelInit(JMenuBar menuBar) {
		JPanel panel = new JPanel() ;
		panel.setOpaque(false);
		panel.setBounds(0, 0, 689, 541);
		panel.setLayout(null);
		panel.setVisible(true);
		frame.getContentPane().add(panel);
		Font font1 = new Font("黑体", Font.PLAIN, 20);
		Color color = new Color(72, 61, 139);
		
		JLabel lblNewLabel = new JLabel("姓名：");
		lblNewLabel.setForeground(color);
		lblNewLabel.setFont(font1);
		lblNewLabel.setBounds(100, 143, 72, 18);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("性别：");
		lblNewLabel_1.setForeground(color);
		lblNewLabel_1.setFont(font1);
		lblNewLabel_1.setBounds(100, 224, 72, 18);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("年龄：");
		lblNewLabel_2.setForeground(color);
		lblNewLabel_2.setFont(font1);
		lblNewLabel_2.setBounds(100, 304, 72, 18);
		panel.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("出生日期：");
		lblNewLabel_3.setForeground(color);
		lblNewLabel_3.setFont(font1);
		lblNewLabel_3.setBounds(100, 396, 136, 18);
		panel.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("账号：");
		lblNewLabel_4.setForeground(color);
		lblNewLabel_4.setFont(font1);
		lblNewLabel_4.setBounds(360, 143, 72, 18);
		panel.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("密码：");
		lblNewLabel_5.setForeground(color);
		lblNewLabel_5.setFont(font1);
		lblNewLabel_5.setBounds(360, 224, 72, 18);
		panel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("以下是您的个人信息");
		lblNewLabel_6.setFont(new Font("黑体", Font.PLAIN, 35));
		lblNewLabel_6.setForeground(Color.BLUE);
		lblNewLabel_6.setBounds(160, 55, 359, 41);
		panel.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel();//姓名内容
		lblNewLabel_7.setBounds(172, 143, 147, 18);
		lblNewLabel_7.setFont(font1);
		lblNewLabel_7.setText("");
		lblNewLabel_7.setText(user.getUname());
		panel.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("New label");//性别内容
		lblNewLabel_8.setFont(font1);
		lblNewLabel_8.setText(user.getSex());
		lblNewLabel_8.setBounds(172, 224, 72, 18);
		panel.add(lblNewLabel_8);
		
		JLabel lblNewLabel_9 = new JLabel("New label");//年龄内容
		lblNewLabel_9.setFont(font1);
		lblNewLabel_9.setText(String.valueOf(user.getAge()));
		lblNewLabel_9.setBounds(172, 304, 72, 18);
		panel.add(lblNewLabel_9);
		
		JLabel lblNewLabel_10 = new JLabel("New label");//出生日期
		lblNewLabel_10.setFont(font1);
		lblNewLabel_10.setText(StringUtil.DataToString(user.getBirthday()));
		lblNewLabel_10.setBounds(200, 396, 199, 18);
		panel.add(lblNewLabel_10);
		
		JLabel lblNewLabel_11 = new JLabel("New label");//账号内容
		lblNewLabel_11.setFont(font1);
		lblNewLabel_11.setText(user.getUaccount());
		lblNewLabel_11.setBounds(430, 143, 199, 18);
		panel.add(lblNewLabel_11);
		
		JLabel lblNewLabel_12 = new JLabel("New label");//密码内容
		lblNewLabel_12.setFont(font1);
		lblNewLabel_12.setText(user.getUpwd());
		lblNewLabel_12.setBounds(430, 224, 199, 18);
		panel.add(lblNewLabel_12);
		

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
			 * 退出面板
			 */
			@Override
			public void mouseClicked(MouseEvent e) {
				menuBar.setVisible(true);
				panel.setVisible(false);
				panel.updateUI();
				frame.remove(panel);
			}
		});
		btnNewButton.setFont(font1);
		btnNewButton.setBounds(384, 385, 113, 41);
		panel.add(btnNewButton);
		
		//刷新面板
		panel.updateUI();
	}
}
