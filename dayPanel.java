//********************************************************************
//  
//	��¥ ���� ȭ��.
//  
//********************************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class dayPanel extends JPanel
{
	private JLabel dayLabel;
	private JButton day1, day2, day3, day4, day5, day6, day7, goMain;
	private Test test;
	private Object obj;
	private wordPanel word;
	
	public dayPanel()
	{
		//�⺻ ����.
		setBackground(new Color(255,255,230));
		setPreferredSize(new Dimension(1028,720));
		setLayout(null);
		
		
		//�޴� ����.
		goMain = new JButton (new ImageIcon("img\\home.png")); // �������� �������� ��ư ������ ���ÿ� ��ư �̹����� ������ 
		goMain.setBounds(10,10,54,54); //��� ���� 
		goMain.setContentAreaFilled(false); // �� �κ� ä��� ���� 
		goMain.setBorderPainted(false); // ��� ��ĥ ���� 
		goMain.addActionListener (new ButtonListener()); //ActionListener�� ������ 
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));//������ �� �̹��� ���� 
		add(goMain);// dayPanel�� ������ 
		
		//��¥ �� ����.
		dayLabel = new JLabel(new ImageIcon("img\\choose.png")); // ��¥�� �����϶�� ���� �˿��ֱ� ���� �󺧷� ������ ���ÿ� �̹����� ������ 
		dayLabel.setBounds(200,80,600,200); //��� ���� 
		dayLabel.setFont(new Font("Verdana",Font.BOLD,50)); //�۾�ü ���� 
		dayLabel.setForeground(Color.red); // ����� ���� 
		add(dayLabel);// dayPanel�� ������ 
		
		//��¥ ��ư ����.
		day1 = new JButton(new ImageIcon("img\\day1.png")); // ��¥�� �����ϱ� ���� ��ư���� ������ ���ÿ� �̹����� ������ 
		day1.setActionCommand("Day1"); // ��ư �����ʿ��� Ȯ���� ���� �̸��� ���� 
		day1.setBounds(180,290,110,110); //��� ���� 
		day1.setContentAreaFilled(false); // ��� ��ĥ ���� 
		day1.setBorderPainted(false); // ��輱 ��ĥ ���� 
		day1.addActionListener(new ButtonListener());//ActionListener�� ������ 
		day1.setPressedIcon(new ImageIcon("img\\day1_p.png"));// ������ �� �̹��� ���� 
		add(day1); // dayPanel�� ������ 
		
		day2 = new JButton(new ImageIcon("img\\day2.png"));
		day2.setActionCommand("Day2");
		day2.setBounds(360,290,110,110);
		day2.setContentAreaFilled(false);
		day2.setBorderPainted(false);
		day2.addActionListener(new ButtonListener());
		day2.setPressedIcon(new ImageIcon("img\\day2_p.png"));
		add(day2);
		
		day3 = new JButton(new ImageIcon("img\\day3.png"));
		day3.setActionCommand("Day3");
		day3.setBounds(540,290,110,110);
		day3.setContentAreaFilled(false);
		day3.setBorderPainted(false);
		day3.addActionListener(new ButtonListener());
		day3.setPressedIcon(new ImageIcon("img\\day3_p.png"));
		add(day3);
		
		day4 = new JButton(new ImageIcon("img\\day4.png"));
		day4.setActionCommand("Day4");
		day4.setBounds(720,290,110,110);
		day4.setContentAreaFilled(false);
		day4.setBorderPainted(false);
		day4.addActionListener(new ButtonListener());
		day4.setPressedIcon(new ImageIcon("img\\day4_p.png"));
		add(day4);
		
		day5 = new JButton(new ImageIcon("img\\day5.png"));
		day5.setActionCommand("Day5");
		day5.setBounds(280,490,110,110);
		day5.setContentAreaFilled(false);
		day5.setBorderPainted(false);
		day5.addActionListener(new ButtonListener());
		day5.setPressedIcon(new ImageIcon("img\\day5_p.png"));
		add(day5);
		
		day6 = new JButton(new ImageIcon("img\\day6.png"));
		day6.setActionCommand("Day6");
		day6.setBounds(460,490,110,110);
		day6.setContentAreaFilled(false);
		day6.setBorderPainted(false);
		day6.addActionListener(new ButtonListener());
		day6.setPressedIcon(new ImageIcon("img\\day6_p.png"));
		add(day6);
		
		day7 = new JButton(new ImageIcon("img\\day7.png"));
		day7.setActionCommand("Day7");
		day7.setBounds(640,490,110,110);
		day7.setContentAreaFilled(false);
		day7.setBorderPainted(false);
		day7.addActionListener(new ButtonListener());
		day7.setPressedIcon(new ImageIcon("img\\day7_p.png"));
		add(day7);
		
		// ��¥ ��ư ���� �� 
		
	}//dayPanel()
	
	
	private class ButtonListener implements ActionListener
	{
		//��ư�� ������ ���.
		public void actionPerformed(ActionEvent event)
		{		
			//� ��¥�� ���õƴ��� Ȯ���ϱ� ����.		
			String str = event.getActionCommand();
			obj = event.getSource();
		//	int day  = Integer.parseInt(str.substring(3));
			
			int day;
			
			day= getDay(obj);
			word.setWord(day);
			word.init();
			test.changePanel("word");
			System.out.println(str);
			
			//� ��ư�� ���ȴ��� ����.
			if(test.getMain().getButton()=="word")
			{
			
			}
			else if(test.getMain().getButton()=="game")
			{
				test.changePanel("game");
			}
			
			if(str=="")
			{
				//�г� ��ȯ.
				test.changePanel("main");
			}
			
			
		}//actionPerformed()
	}//ButtonLsitener class
	
	public void setTest(Test reference) // up call�� ���� �Լ� 
	{
		test=reference;
	}//set Test
	
	public Object getButton(){ // ���� ��ư�� ��ȯ�ϱ� ���� �Լ� 
		return obj;
	}//getButton
	
	public void setWordPanel(wordPanel reference) //up calll�� ���� �Լ� 
	{
		word=reference;
	}
	
	
	public int getDay(Object obj){ // ���� ��¥�� ���� int�� ��ȯ�ϴ� �Լ� 
	
	if(obj==day1) return 1;
	else if(obj==day2) return 2;
	else if(obj==day3) return 3;
	else if(obj==day4) return 4;
	else if(obj==day5) return 5;
	else if(obj==day6) return 6;
	else if(obj==day7) return 7;
	else return 1;
		
	}//getDay
	
}//dayPanel class