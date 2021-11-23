package code;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

@SuppressWarnings("serial")
public class Interface extends JFrame implements ActionListener {
	
	private JLabel bigTitle = new JLabel ("MySQL");
	private JLabel saveEngine = new JLabel ("存储引擎");
	private JLabel operation = new JLabel ("操作");
	private JLabel frequency = new JLabel ("次数");
	private JLabel result = new JLabel ("结果：");
	private JLabel author = new JLabel ("@ Coverli");
	
	private static final String[] arraySaveEngine = {"请选择","default","INNODB","MyISAM","MEMORY"};
	private JComboBox<String> selectedSaveEngine = new JComboBox<String> (arraySaveEngine);
	
	private static final String[] arrayOperation = {"请选择","查询","插入","更新","删除"};
	private JComboBox<String> selectedOperation = new JComboBox<String> (arrayOperation);
	
	private JTextField inputFrequency  = new JTextField ("0");
	private JButton testButton = new JButton ("测试");
	private JTextArea testResult = new JTextArea (350, 150);
	private JScrollPane ScrollPane = new JScrollPane(testResult);
	
	public Interface () {
		
		// 设置窗口
		this.setTitle ("MySQL存储引擎测试程序");
		this.setSize (415, 625);
		this.setIconImage (new ImageIcon("./src/img/Icon.png").getImage());
		this.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
		this.setVisible (true);
		//this.setResizable(false);//设置窗口是否可以放大。true则可以最大化，false则不可变
		
		//设置面板
		JPanel panel = new JPanel ();
		panel.setLayout (null);
		panel.setLocation (0, 0);
		panel.setSize (400, 600);
		this.getContentPane().add(panel);
		
		//设置字体
		Font defaultFont = new Font ("宋体", Font.PLAIN, 14);
		Font iconFont = new Font ("宋体", Font.BOLD + Font.ITALIC, 56);
		
		bigTitle.setFont (iconFont);
		saveEngine.setFont(defaultFont);
		operation.setFont(defaultFont);
		frequency.setFont(defaultFont);
		result.setFont(defaultFont);
		author.setFont(defaultFont);
		selectedSaveEngine.setFont(defaultFont);
		selectedOperation.setFont(defaultFont);
		inputFrequency.setFont(defaultFont);
		testButton.setFont(defaultFont);
		testResult.setFont(defaultFont);
		
		//设置组件的位置
		//第一个参数改组件在JFrame中的x坐标
		//第二个参数改组件在JFrame中的y坐标
		//第三个参数改组件在JFrame中的组件宽度
		//第四个参数改组件在JFrame中的组件高度
		bigTitle.setBounds (25, 0, 250, 140);
		saveEngine.setBounds (25, 215, 70, 25);
		operation.setBounds (150, 215, 70, 25);
		frequency.setBounds (275, 215, 70, 25);
		result.setBounds (25, 390, 70, 25);
		author.setBounds (330, 565, 70, 25);
		selectedSaveEngine.setBounds (25, 240, 100, 25);
		selectedOperation.setBounds (150, 240, 100, 25);
		inputFrequency.setBounds (275, 240, 100, 25);
		testButton.setBounds (275, 315, 100, 25);
		testResult.setBounds (25, 415, 350, 150);
		ScrollPane.setBounds (25, 415, 350, 150);
		
		//添加组件
		panel.add (bigTitle);
		panel.add (saveEngine);
		panel.add (operation);
		panel.add (frequency);
		panel.add (result);
		panel.add (author);
		panel.add (selectedSaveEngine);
		panel.add (selectedOperation);
		panel.add (inputFrequency);
		panel.add (testButton);
		panel.add (testResult);
		panel.add (ScrollPane);
		testResult.setEditable(false); //设置输出结果不可编辑
		testResult.setLineWrap(true); //设置多行文本区域自动换行
		ScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		ScrollPane.setViewportView(testResult);
		
		//注册事件处理器
		testButton.addActionListener(this);
	}
	
	public static void main( String[] args ) {
		new Interface ();
	}
	
	public void DatabaseHandle (int index1, int index2, String num) {
		int count = Integer.parseInt(num);
		if (index1 == 1 && index2 == 1) {
			//默认引擎查询
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("select * from ne where id = " + i + ";");
					pstm.executeQuery();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("默认引擎-查询了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 1 && index2 == 2) {
			//默认引擎插入
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("insert into ne value (" + i + ",1,null,null);");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("默认引擎-插入了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 1 && index2 == 3) {
			//默认引擎更新
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("update ne set sum = 2 where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("默认引擎-更新了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 1 && index2 == 4) {
			//默认引擎删除
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("delete from ne where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("默认引擎-删除了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 2 && index2 == 1) {
			//INNODB引擎查询
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("select * from innodb where id = " + i + ";");
					pstm.executeQuery();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("INNODB引擎-查询了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 2 && index2 == 2) {
			//INNODB引擎插入
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("insert into innodb value (" + i + ",1,null,null);");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("INNODB引擎-插入了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 2 && index2 == 3) {
			//INNODB引擎更新
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("update innodb set sum = 2 where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("INNODB引擎-更新了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 2 && index2 == 4) {
			//INNODB引擎删除
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("delete from innodb where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("INNODB引擎-删除了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 3 && index2 == 1) {
			//MyISAM引擎查询
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("select * from myisam where id = " + i + ";");
					pstm.executeQuery();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MyISAM引擎-查询了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 3 && index2 == 2) {
			//MyISAM引擎插入
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("insert into myisam value (" + i + ",1,null,null);");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MyISAM引擎-插入了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 3 && index2 == 3) {
			//MyISAM引擎更新
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("update myisam set sum = 2 where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MyISAM引擎-更新了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 3 && index2 == 4) {
			//MyISAM引擎删除
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("delete from myisam where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MyISAM引擎-删除了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 4 && index2 == 1) {
			//MEMORY引擎查询
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("select * from memory where id = " + i + ";");
					pstm.executeQuery();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MEMORY引擎-查询了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 4 && index2 == 2) {
			//MEMORY引擎插入
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("insert into memory value (" + i + ",1,null,null);");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MEMORY引擎-插入了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 4 && index2 == 3) {
			//MEMORY引擎更新
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("update memory set sum = 2 where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MEMORY引擎-更新了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else if (index1 == 4 && index2 == 4) {
			//MEMORY引擎删除
			PreparedStatement pstm = null;
			Connection conn = Conn.Connect();
			try {
				long startTime = System.currentTimeMillis();// 获取开始时间
				//SQL语句主体
				for (int i = 1; i <= count; i++) {
					pstm = conn.prepareStatement("delete from memory where id = " + i + ";");
					pstm.executeUpdate();
				}
				long endTime = System.currentTimeMillis(); // 获取结束时间
				//返回结果到JTextArea.testResult
				String execute = ("MEMORY引擎-删除了"+ count +"条信息");
				String runTime = ("程序运行时间：" + (endTime - startTime) + "ms");
				testResult.append(String.format(execute)+"\n");
				testResult.append(String.format(runTime)+"\n");
				testResult.append("----------------------"+"\n");
			} catch (Exception e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			} finally {
				if (pstm != null) {
					try {
						pstm.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						e.printStackTrace();
						throw new RuntimeException(e);
					}
				}
			}
		}
		else {
			//报错
			String err = "请选择存储引擎以及操作！！！";
			testResult.append(String.format(err)+"\n");
			testResult.append("----------------------"+"\n");
		}
	}
	
	@Override
	public void actionPerformed (ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == testButton) {
			int indexSaveEngine = selectedSaveEngine.getSelectedIndex();
			int indexOperation = selectedOperation.getSelectedIndex();
			String frequencyValue =  inputFrequency.getText();
			DatabaseHandle(indexSaveEngine, indexOperation, frequencyValue);
		}
	}
}