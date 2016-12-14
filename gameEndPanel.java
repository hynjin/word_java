//********************************************************************
//  
//	���� ���� ȭ��.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class gameEndPanel extends JPanel
{
	private JButton goMain, rePlay, nextStage;//��ư ��ü.
	private JLabel gameOver, gameClear,mgameOver, mgameClear, gameClear1, gameClear2;//�ΰ� ��ü.

	private Test test;//�гε鿡 ���� ������ ���� Test Ŭ����.
	
	public gameEndPanel()//�⺻ ����.
	{
		//�⺻ ����.
		setBackground(Color.white);
		setPreferredSize(new Dimension(1028,720));
		setLayout(null);
		
		
		//�ΰ� ����.
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
		
		//�̹��� ����.
		mgameOver = new JLabel(new ImageIcon("img\\Over.png"),SwingConstants.CENTER);
		mgameOver.setBounds(0,0,1028,720);
		add(mgameOver);
		
		mgameClear = new JLabel(new ImageIcon("img\\gameClear.png"),SwingConstants.CENTER);
		mgameClear.setBounds(0,0,1028,720);
		add(mgameClear);
		
		
		//�޴� �̵� ��ư ����.
		goMain = new JButton (new ImageIcon("img\\gomain.png"));
		goMain.setActionCommand("Main");
		goMain.setBounds(330,580,180,80);
		goMain.setContentAreaFilled(false);
		goMain.setBorderPainted(false);
		goMain.addActionListener (new ButtonListener());
		goMain.setPressedIcon(new ImageIcon("img\\gomain_p.png"));
		
		//�ٽ� �÷��� ��ư ����.
		rePlay = new JButton (new ImageIcon("img\\replay.png"));
		rePlay.setActionCommand("Replay");
		rePlay.setBounds(540,580,180,80);
		rePlay.setContentAreaFilled(false);
		rePlay.setBorderPainted(false);
		rePlay.addActionListener(new ButtonListener());
		rePlay.setPressedIcon(new ImageIcon("img\\replay_p.png"));
		
		//���� �ܰ� ��ư ����.
		nextStage = new JButton (new ImageIcon("img\\nextStage.png"));
		nextStage.setActionCommand("nextStage");
		nextStage.setBounds(540,580,180,80);
		nextStage.setContentAreaFilled(false);
		nextStage.setBorderPainted(false);
		nextStage.addActionListener(new ButtonListener());
		nextStage.setPressedIcon(new ImageIcon("img\\nextStage_p.png"));
		mgameClear.add(nextStage);
		
	}//gameEndPanel()
	
	public void gameEnd(String str)//������ ���¸� �޾ƿ�.
	{
		
		gameClear1.setVisible(false);
		gameClear2.setVisible(false);
		
		if(str == "over")//������ ������ ���(���� ���)
		{
			//gameOver�� ���õ� ��ư�� �̹����� Ȱ��ȭ.
			gameClear.setVisible(false);
			mgameClear.setVisible(false);
			gameOver.setVisible(true);
			mgameOver.setVisible(true);
			
			nextStage.setVisible(false);
			rePlay.setVisible(true);
			
			mgameOver.add(goMain);
			mgameOver.add(rePlay);
		}
		else if(str == "clear")//������ �̰��� ���.
		{
			//gameClear�� ���õ� ��ư�� �̹����� Ȱ��ȭ.
			gameClear.setVisible(true);
			mgameClear.setVisible(true);
			gameOver.setVisible(false);
			mgameOver.setVisible(false);
			
			nextStage.setVisible(false);
			rePlay.setVisible(true);
			
			mgameClear.add(goMain);
			mgameClear.add(rePlay);
		}
		else //�������� 1,2�� ���� ���.
		{
			//�������� 1,2 �� ���õ� ��ư�� �̹����� Ȱ��ȭ.
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
		//��ư�� ������ ���.
		public void actionPerformed(ActionEvent event)
		{
			if(event.getActionCommand()=="Main")
			{
				//�г� ��ȯ.
				test.changePanel("main");
			}
			else if(event.getActionCommand()== "Replay")
			{
				//�г� ��ȯ.
				test.changePanel("game");
			}
			else if(event.getActionCommand()== "nextStage")
			{
				//�г� ��ȯ.
				test.changePanel("game");
			}
		}//actionPerformed()
		
	}//ButtonListener class
	
	
	public void setTest(Test reference)
	{
		test=reference;
	}//setTest(Test)
	
}//gameEndPanel class