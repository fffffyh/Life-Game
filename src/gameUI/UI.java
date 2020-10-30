package gameUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import Matrix.cellMatrix;

public class UI extends JFrame {
	private JButton startBtn=new JButton("开始游戏");      //开始游戏按钮
	private JButton generateBtn=new JButton("生成细胞");   //生成细胞按钮
	private JLabel flashTimeLab=new JLabel("刷新时间/ms"); //文本框
	private JTextField flashTimeText=new JTextField();     //输入框
	private Boolean startGame=false;                       //开始游戏标志
	
	private JPanel Filepanel=new JPanel(new GridLayout(2,2)); //菜单栏布局
	private JPanel gamePanel=new JPanel();                    //游戏面板
	
	private cellMatrix cMatrix=new cellMatrix(10,10);
	private JTextField[][] textMatrix; 
	private int flashTime=1000;         //默认刷新时间为1000ms
	
	private int length=cMatrix.getLength(); 
	private int width=cMatrix.getWidth();
	
	public UI()
	{
		setTitle("LifeGame");
		
		startBtn.addActionListener(new startListener());
		generateBtn.addActionListener(new generateListener());
		
		Filepanel.add(generateBtn);
		Filepanel.add(startBtn);
		Filepanel.add(flashTimeLab);
		Filepanel.add(flashTimeText);
		Filepanel.setBackground(Color.white);
		getContentPane().add("North",Filepanel);
		
		this.setSize(300,300);
		this.setVisible(true);
		
		cellLayout();
		gamePanel.updateUI();     //显示游戏界面
	}	
	
	//开始游戏监听器
	public class startListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				flashTime=Integer.parseInt(flashTimeText.getText().trim());    //trim()用于去掉空格
			}catch(NumberFormatException e1) {
				flashTime=500;
			}
			
			if(!startGame) {
				startGame=true;
				new Thread(new timeControl()).start();     //开始游戏
				startBtn.setText("暂停游戏");
			}
			else {
				startGame=false;
				startBtn.setText("开始游戏");
			}
		}
	}
	
	public class generateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			cMatrix.matrixinit();
			showCell();
		}
	}
	
	//创建游戏格子布局
	public void cellLayout()
	{
		gamePanel.setLayout(new GridLayout(width,length));
		textMatrix=new JTextField[width][length];
		for(int i=0;i<width;i++)
			for(int j=0;j<length;j++) {
				JTextField Text=new JTextField();
				textMatrix[i][j]=Text;
				gamePanel.add(Text);
			}
		getContentPane().add("Center",gamePanel);

	}
	
	//显示活细胞
	public void showCell() {
		for(int i=0;i<length;i++)
			for(int j=0;j<width;j++) {
				if(cMatrix.getMatrix()[i][j]==0)
					textMatrix[i][j].setBackground(Color.white);
				else if(cMatrix.getMatrix()[i][j]==1)
					textMatrix[i][j].setBackground(Color.black);
			}
	}
	
	//游戏线程
	public class timeControl extends Thread{
		public void run() {
			while(startGame) {
				cMatrix.matrixflash();
				showCell();
				try {
					Thread.sleep(flashTime);//停顿
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
