//********************************************************************
//  
//	���� ȭ��.
//  
//********************************************************************
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class mainPanel extends JPanel
{
	private JButton goWord, goGame;
	private JLabel mainLogo, mainC;
	private Test test;
	private String str;
	
	public mainPanel()
	{
		//�⺻ ����.
		setBackground(new Color(255,255,230));
		setPreferredSize(new Dimension(1028,720));
		setLayout(null);
		
		
		//���ηΰ�.
		mainLogo = new JLabel(new ImageIcon("img\\7day.png"),SwingConstants.CENTER);
		mainLogo.setBounds(280,100,650,300);
		add(mainLogo);
		
		mainC = new JLabel(new ImageIcon("img\\Over_c.png"),SwingConstants.CENTER);
		mainC.setBounds(190,270,287,248);
		add(mainC);
		
		//�ܾ����̵� ��ư ����.
		goWord = new JButton (new ImageIcon("img\\goWord.png"));
		goWord.setActionCommand("Word");
		goWord.setBounds(330,550,180,80);
		goWord.setContentAreaFilled(false);
		goWord.setBorderPainted(false);
		goWord.addActionListener (new ButtonListener());
		goWord.setPressedIcon(new ImageIcon("img\\goWord_p.png"));
		add(goWord);
		
		
		//���� �̵� ��ư ����.
		goGame = new JButton (new ImageIcon("img\\goGame.png"));
		goGame.setActionCommand("Game");
		goGame.setBounds(540,550,180,80);
		goGame.setContentAreaFilled(false);
		goGame.setBorderPainted(false);
		goGame.addActionListener (new ButtonListener());
		goGame.setPressedIcon(new ImageIcon("img\\goGame_p.png"));
		add(goGame);
		
	}//mainPanel()
	
	public String getButton()
	{
		return this.str;	
	}//getButton()
	
	private class ButtonListener implements ActionListener
	{
		//��ư�� ������ ���.
		public void actionPerformed(ActionEvent event)
		{
			//� ��ư�� ���ȴ��� ����.
			if(event.getActionCommand()=="Word")
			{
				str = "word";
			}
			else
			{
				str = "game";
			}

			//�г� ��ȯ.
			test.changePanel("day");
		}//actionPerformed()
	}//ButtonLsitener class
	
	public void setTest(Test reference)
	{
		test=reference;
	}
	

}//mainPanel class