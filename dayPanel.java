//********************************************************************
//  
//	날짜 선택 화면.
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
		//기본 설정.
		setBackground(new Color(255,255,230));
		setPreferredSize(new Dimension(1028,720));
		setLayout(null);
		
		
		//메뉴 생성.
		goMain = new JButton (new ImageIcon("img\\home.png")); // 메인으로 가기위한 버튼 생성과 동시에 버튼 이미지를 가져옴 
		goMain.setBounds(10,10,54,54); //경계 설정 
		goMain.setContentAreaFilled(false); // 빈 부분 채우기 설정 
		goMain.setBorderPainted(false); // 경계 색칠 설정 
		goMain.addActionListener (new ButtonListener()); //ActionListener를 더해줌 
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));//눌렸을 떄 이미지 설정 
		add(goMain);// dayPanel에 더해줌 
		
		//날짜 라벨 생성.
		dayLabel = new JLabel(new ImageIcon("img\\choose.png")); // 날짜를 선택하라는 것을 알여주기 위한 라벨로 생성과 동시에 이미지를 가져옴 
		dayLabel.setBounds(200,80,600,200); //경계 설정 
		dayLabel.setFont(new Font("Verdana",Font.BOLD,50)); //글씨체 설정 
		dayLabel.setForeground(Color.red); // 전경색 설정 
		add(dayLabel);// dayPanel에 더해줌 
		
		//날짜 버튼 생성.
		day1 = new JButton(new ImageIcon("img\\day1.png")); // 날짜를 선택하기 위한 버튼으로 생성과 동시에 이미지를 가져옴 
		day1.setActionCommand("Day1"); // 버튼 리스너에서 확인할 때의 이름을 설정 
		day1.setBounds(180,290,110,110); //경계 설정 
		day1.setContentAreaFilled(false); // 빈곳 색칠 설정 
		day1.setBorderPainted(false); // 경계선 색칠 설정 
		day1.addActionListener(new ButtonListener());//ActionListener를 더해줌 
		day1.setPressedIcon(new ImageIcon("img\\day1_p.png"));// 눌렸을 때 이미지 설정 
		add(day1); // dayPanel에 더해줌 
		
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
		
		// 날짜 버튼 생성 끝 
		
	}//dayPanel()
	
	
	private class ButtonListener implements ActionListener
	{
		//버튼을 눌렀을 경우.
		public void actionPerformed(ActionEvent event)
		{		
			//어떤 날짜가 선택됐는지 확인하기 위해.		
			String str = event.getActionCommand();
			obj = event.getSource();
		//	int day  = Integer.parseInt(str.substring(3));
			
			int day;
			
			day= getDay(obj);
			word.setWord(day);
			word.init();
			test.changePanel("word");
			System.out.println(str);
			
			//어떤 버튼이 눌렸는지 구분.
			if(test.getMain().getButton()=="word")
			{
			
			}
			else if(test.getMain().getButton()=="game")
			{
				test.changePanel("game");
			}
			
			if(str=="")
			{
				//패널 전환.
				test.changePanel("main");
			}
			
			
		}//actionPerformed()
	}//ButtonLsitener class
	
	public void setTest(Test reference) // up call을 위한 함수 
	{
		test=reference;
	}//set Test
	
	public Object getButton(){ // 눌린 버튼을 반환하기 위한 함수 
		return obj;
	}//getButton
	
	public void setWordPanel(wordPanel reference) //up calll을 위한 함수 
	{
		word=reference;
	}
	
	
	public int getDay(Object obj){ // 눌린 날짜의 값을 int로 반환하는 함수 
	
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