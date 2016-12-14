//********************************************************************
//  
//	게임 종료 화면.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameEndPanel extends JPanel
{
	private JButton goMain, rePlay, nextStage;//버튼 객체.
	private JLabel gameOver, gameClear,mgameOver, mgameClear, gameClear1, gameClear2;//로고 객체.

	private Test test;//패널들에 대한 정보를 가진 Test 클래스.
	
	public gameEndPanel()//기본 설정.
	{
		//기본 설정.
		setBackground(Color.white);
		setPreferredSize(new Dimension(1028,720));
		setLayout(null);
		
		
		//로고 생성.
		gameOver = new JLabel(new ImageIcon("img\\GameOver.png"),SwingConstants.CENTER);
		gameOver.setBounds(230,100,510,140);
		add(gameOver);
		
		gameClear = new JLabel(new ImageIcon("img\\Clear.png"),SwingConstants.CENTER);
		gameClear.setBounds(190,100,650,150);
		add(gameClear);
		
		gameClear1 = new JLabel(new ImageIcon("img\\Clear1.png"),SwingConstants.CENTER);
		gameClear1.setBounds(180,100,650,150);
		add(gameClear1);
		
		gameClear2 = new JLabel(new ImageIcon("img\\Clear2.png"),SwingConstants.CENTER);
		gameClear2.setBounds(180,100,650,150);
		add(gameClear2);
		
		//이미지 생성.
		mgameOver = new JLabel(new ImageIcon("img\\Over.png"),SwingConstants.CENTER);
		mgameOver.setBounds(0,0,1028,720);
		add(mgameOver);
		
		mgameClear = new JLabel(new ImageIcon("img\\gameClear.png"),SwingConstants.CENTER);
		mgameClear.setBounds(0,0,1028,720);
		add(mgameClear);
		
		
		//메뉴 이동 버튼 생성.
		goMain = new JButton (new ImageIcon("img\\gomain.png"));
		goMain.setActionCommand("Main");
		goMain.setBounds(330,580,180,80);
		goMain.setContentAreaFilled(false);
		goMain.setBorderPainted(false);
		goMain.addActionListener (new ButtonListener());
		goMain.setPressedIcon(new ImageIcon("img\\gomain_p.png"));
		
		//다시 플레이 버튼 생성.
		rePlay = new JButton (new ImageIcon("img\\replay.png"));
		rePlay.setActionCommand("Replay");
		rePlay.setBounds(540,580,180,80);
		rePlay.setContentAreaFilled(false);
		rePlay.setBorderPainted(false);
		rePlay.addActionListener(new ButtonListener());
		rePlay.setPressedIcon(new ImageIcon("img\\replay_p.png"));
		
		//다음 단계 버튼 생성.
		nextStage = new JButton (new ImageIcon("img\\nextStage.png"));
		nextStage.setActionCommand("nextStage");
		nextStage.setBounds(540,580,180,80);
		nextStage.setContentAreaFilled(false);
		nextStage.setBorderPainted(false);
		nextStage.addActionListener(new ButtonListener());
		nextStage.setPressedIcon(new ImageIcon("img\\nextStage_p.png"));
		mgameClear.add(nextStage);
		
	}//gameEndPanel()
	
	public void gameEnd(String str)//게임의 상태를 받아옴.
	{
		
		gameClear1.setVisible(false);
		gameClear2.setVisible(false);
		
		if(str == "over")//게임이 끝났을 경우(졌을 경우)
		{
			//gameOver와 관련된 버튼과 이미지만 활성화.
			gameClear.setVisible(false);
			mgameClear.setVisible(false);
			gameOver.setVisible(true);
			mgameOver.setVisible(true);
			
			nextStage.setVisible(false);
			rePlay.setVisible(true);
			
			mgameOver.add(goMain);
			mgameOver.add(rePlay);
		}
		else if(str == "clear")//게임을 이겼을 경우.
		{
			//gameClear와 관련된 버튼과 이미지만 활성화.
			gameClear.setVisible(true);
			mgameClear.setVisible(true);
			gameOver.setVisible(false);
			mgameOver.setVisible(false);
			
			nextStage.setVisible(false);
			rePlay.setVisible(true);
			
			mgameClear.add(goMain);
			mgameClear.add(rePlay);
		}
		else //스테이지 1,2를 깼을 경우.
		{
			//스테이지 1,2 와 관련된 버튼과 이미지만 활성화.
			if(str == "stage1")
				gameClear1.setVisible(true);
			else if(str == "stage2")
				gameClear2.setVisible(true);
				
			gameClear.setVisible(false);
			mgameClear.setVisible(true);
			gameOver.setVisible(false);
			mgameOver.setVisible(false);
			
			nextStage.setVisible(true);
			rePlay.setVisible(false);
			
			mgameClear.add(goMain);
		}
	}//gameEnd()
	
	private class ButtonListener implements ActionListener
	{
		//버튼을 눌렀을 경우.
		public void actionPerformed(ActionEvent event)
		{
			if(event.getActionCommand()=="Main")
			{
				//패널 전환.
				test.changePanel("main");
			}
			else if(event.getActionCommand()== "Replay")
			{
				//패널 전환.
				test.changePanel("game");
			}
			else if(event.getActionCommand()== "nextStage")
			{
				//패널 전환.
				test.changePanel("game");
			}
		}//actionPerformed()
		
	}//ButtonListener class
	
	
	public void setTest(Test reference)
	{
		test=reference;
	}//setTest(Test)
	
}//gameEndPanel class