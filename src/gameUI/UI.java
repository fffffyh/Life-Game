package gameUI;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.*;

import javax.swing.*;

import Matrix.cellMatrix;

public class UI extends JFrame {
	private JButton startBtn=new JButton("��ʼ��Ϸ");      //��ʼ��Ϸ��ť
	private JButton generateBtn=new JButton("����ϸ��");   //����ϸ����ť
	private JLabel flashTimeLab=new JLabel("ˢ��ʱ��/ms"); //�ı���
	private JTextField flashTimeText=new JTextField();     //�����
	private Boolean startGame=false;                       //��ʼ��Ϸ��־
	
	private JPanel Filepanel=new JPanel(new GridLayout(2,2)); //�˵�������
	private JPanel gamePanel=new JPanel();                    //��Ϸ���
	
	private cellMatrix cMatrix=new cellMatrix(10,10);
	private JTextField[][] textMatrix; 
	private int flashTime=1000;         //Ĭ��ˢ��ʱ��Ϊ1000ms
	
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
		gamePanel.updateUI();     //��ʾ��Ϸ����
	}	
	
	//��ʼ��Ϸ������
	public class startListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			try {
				flashTime=Integer.parseInt(flashTimeText.getText().trim());    //trim()����ȥ���ո�
			}catch(NumberFormatException e1) {
				flashTime=500;
			}
			
			if(!startGame) {
				startGame=true;
				new Thread(new timeControl()).start();     //��ʼ��Ϸ
				startBtn.setText("��ͣ��Ϸ");
			}
			else {
				startGame=false;
				startBtn.setText("��ʼ��Ϸ");
			}
		}
	}
	
	public class generateListener implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			
			cMatrix.matrixinit();
			showCell();
		}
	}
	
	//������Ϸ���Ӳ���
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
	
	//��ʾ��ϸ��
	public void showCell() {
		for(int i=0;i<length;i++)
			for(int j=0;j<width;j++) {
				if(cMatrix.getMatrix()[i][j]==0)
					textMatrix[i][j].setBackground(Color.white);
				else if(cMatrix.getMatrix()[i][j]==1)
					textMatrix[i][j].setBackground(Color.black);
			}
	}
	
	//��Ϸ�߳�
	public class timeControl extends Thread{
		public void run() {
			while(startGame) {
				cMatrix.matrixflash();
				showCell();
				try {
					Thread.sleep(flashTime);//ͣ��
				}catch(InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
}
