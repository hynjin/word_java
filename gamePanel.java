//********************************************************************
//  
//	���� ȭ��.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class gamePanel extends JPanel // implements Runnable
{
	private JButton goMain, btnInput; // ��ư.
	private Image Slime[][], slime, Fish[], fish, Cat[], cat, fishF[], fishItem, bomb[], bombItem,speed[], speedItem, back;//�̹��� ��ü.
	private int cntAnimation, cntTime, cntSlime, cntFish, nStage, attack;//���ӿ� �ʿ��� ������.
	private Toolkit tool;//�̹����� �׸��� ���� ��Ŷ.

	private Test test;//�г��� ������ ������ �ִ� Test Ŭ����.

	private JTextField txtInput; //�ܾ��Է�.
	private ButtonListener btnL;//��ư ������.
	
	private ArrayList<Game> game;//������ ��ü.
	private ArrayList<Game> item;//������ ��ü.

	public gamePanel()//�г��� �⺻ ����.
	{
		// �⺻ ����.
		setBackground(Color.white);
		setPreferredSize(new Dimension(1028, 720));
		setLayout(null);
		
		// �޴� ����.
		goMain = new JButton (new ImageIcon("img\\home.png"));
		goMain.setBounds(10,10,54,54);
		goMain.setContentAreaFilled(false);
		goMain.setBorderPainted(false);
		goMain.addActionListener (new ButtonListener());
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));
		add(goMain);

		// �̹����� �׸��� ���� ��Ŷ.
		tool = Toolkit.getDefaultToolkit();

		//��ü�� �ִϸ��̼��� ����.
		Fish = new Image[6];//�����[����]
		Slime = new Image[3][9];//������[����][�ִϸ��̼�]

		// �̹��� �ε�.
		for(int i=1;i<4;i++)
        	for(int j=1;j<10;j++)
        		this.Slime[i-1][j-1] = tool.getImage("img\\slime"+i+"-"+j+".png");//������ �̹���.
        		
        for(int i=0;i<6;i++)
	        this.Fish[i] = tool.getImage("img\\fish"+i+".png");//����� �̹���.
        
        Cat = new Image[4];
        
        for(int i=0;i<4;i++)
	        this.Cat[i] = tool.getImage("img\\cat"+i+".png");//����� �̹���.
        cat = Cat[0];
        attack = 0;
        
        fishF = new Image[4];
        bomb = new Image[4];
        speed = new Image[4];
        
        for(int i=0;i<4;i++)
        {
	        this.fishF[i] = tool.getImage("img\\fishf"+i+".png");//����� ������ �̹���.
	        this.bomb[i] = tool.getImage("img\\bomb"+i+".png");//��ź ������ �̹���/
	        this.speed[i] = tool.getImage("img\\time"+i+".png");//�ӵ����� ������ �̹���.
    	}
    	
        this.back = tool.getImage("img\\background.png");//��� �̹���.
        
        this.slime = Slime[0][0];
        this.fish = Fish[5];

		//������ ��ü ����.
		game = new ArrayList<Game>();
		for(int i=0; i<4; i++)
			game.add(new Game());
			
		//������ ��ü ����.
		item = new ArrayList<Game>();
		for(int i=0; i<3;i++)
			item.add(new Game());

		nStage = 1;//�������� 1.
		
		gameInit();//���� ��ü �ʱ�ȭ.

		btnL = new ButtonListener();//��ư ������ ����.
		
		txtInput = new JTextField();//�ܾ� �Է��� ���� �ؽ�Ʈ �ʵ� ����.
		txtInput.setBounds(350, 650, 250, 40);
		txtInput.addActionListener(btnL);
		add(txtInput);

		btnInput = new JButton(new ImageIcon("img\\enter.png"));//�ܾ� �Է��� ���� Ȯ�� ��ư ����.
		btnInput.setBounds(620, 650, 70, 40);
		btnInput.setActionCommand("Enter");
		btnInput.setContentAreaFilled(false);
		btnInput.setBorderPainted(false);
		btnInput.addActionListener(btnL);
		btnInput.setPressedIcon(new ImageIcon("img\\enter_p.png"));
		this.add(btnInput);


	}// gamePanel()

	public void gameInit()//��ü(������, ����) ���� �ʱ�ȭ.
	{
		cntAnimation = cntTime = cntSlime = 0;
		cntFish = 5;
		
		for(int i=0;i<game.size();i++)//��� ������ ��ü �ʱ�ȭ.
		{
			game.get(i).slimeInit();
			game.get(i).setX(-200);
		}
		
		for(int i=0;i<item.size();i++)//��� ���� ��ü �ʱ�ȭ.
		{
			item.get(i).slimeInit();
			item.get(i).setX(-200);
		}	
	}//gameInit()
	
	public void paintComponent(Graphics page)//�̹����� �׸�.
	{
		super.paintComponent(page);//�θ�� ���� ���� �޾ƿ�.

		page.drawImage(back,0,0,1028,720,this);
		//�������� ������ �������� �Ǵ� ������ ���� �����ֱ� ���� ��.
		page.setColor(Color.red);
		page.drawLine(220, 20, 220, 580);

		// ������ ���� run().
		for(int i=0;i<game.size();i++)
			game.get(i).run();
		for(int i=0;i<item.size();i++)
			item.get(i).run();
			

		// ������ �ִϸ��̼��� ����.
		if (cntTime == 5) {
			cntAnimation++;
			cntAnimation %= 3;

			cntTime = 0;
		} else
			cntTime++;

		// �̹����� �׸�.
		page.setFont(new Font("����",Font.BOLD,30));//�ܾ� ���ڿ� ���� ����.
		page.setColor(Color.yellow);
	
		speedItem = speed[(cntAnimation+1)%2];
		bombItem = bomb[(cntAnimation+2)%2];
		fishItem = fishF[(cntAnimation+3)%2];

	
		for(int i=0;i<game.size();i++)//������ ��ü �׸���.
		{
			Game temp = game.get(i);
			
			// ���� �Ѵ� ������ ���� Ȯ��->������ ����.
			if(temp.getFish() && (cntFish > 0))
				cntFish--;
					
			fish = Fish[cntFish];//���� ������ ������ ���� � ���� �̹����� ����� �޾ƿ�,
		
			page.drawImage(fish, 40, 250, 140, 140, this);//���� �׸�.
			
			if(temp.getPt().x>200)//�������� ���� ���� �ٱ��� ���.
			{
				if(temp.getDie() != 0)//�������� �׾��� ���.
				{
					slime = Slime[temp.getSlimeColor()][((cntAnimation + temp.getSlimeColor()) % 3)+6];//������ ���� �ִ� ������ �̹���  �޾ƿ�.

					if((((cntAnimation + temp.getSlimeColor()) % 3)+6)==8)
					{
						temp.slimeInit();//��ü �ʱ�ȭ.
						temp.setX(-200);
						temp.setDie(0);
					}
				}
				else
					slime = Slime[temp.getSlimeColor()][(cntAnimation + temp.getSlimeColor()) % 3];//� �������� �׸��� �޾ƿ�.
				page.drawImage(slime,temp.getPt().x,temp.getPt().y,140,140,this);//������.
				
				if(temp.getNumWord()==0)
					page.drawString(wordPanel.getWordArr(temp.getnWord1(),temp.getLanguage1()),temp.getPt().x+25,temp.getPt().y+130);//�ܾ�.
				else
					page.drawString(wordPanel.getWordArr(temp.getnWord2(),temp.getLanguage2()),temp.getPt().x+25,temp.getPt().y+130);//�ܾ�.
			}
			else //�������� ���� ���� ���� ���. ����⸦ ���� ���� �̹����� �׸�.
			{
				slime = Slime[temp.getSlimeColor()][((cntAnimation + temp.getSlimeColor()) % 3)+3];//������ ���� �ִ� ������ �̹���  �޾ƿ�.
				page.drawImage(slime,temp.getPt().x,temp.getPt().y,140,140,this);
			}
				
		}
		
		for(int i=0;i<item.size();i++)//������ ��ü �׸���.
		{
			Game temp = item.get(i);
						
				
			if(temp.getPt().x>200)//�� �������� ���� ���� �ٱ��̸� �ܾ ������ ����.
			{				
				if(i==0)//�ӵ� ������.
					page.drawImage(speedItem,item.get(0).getPt().x,item.get(0).getPt().y,300,150,this);
				else if(i==1)//����� ������.
					page.drawImage(fishItem,item.get(1).getPt().x,item.get(1).getPt().y,300,150,this);
				else //��ź ������.
					page.drawImage(bombItem,item.get(2).getPt().x,item.get(2).getPt().y,300,150,this); 
					
				page.drawString(wordPanel.getWordArr(temp.getnWord1(),temp.getLanguage1()),temp.getPt().x+100,temp.getPt().y+130);
			}
			else
			{
				if(i==0)//�ӵ� ������.
					page.drawImage(speed[3],item.get(0).getPt().x,item.get(0).getPt().y,100,100,this);
				else if(i==1)//����� ������.
					page.drawImage(fishF[3],item.get(1).getPt().x,item.get(1).getPt().y,100,100,this);
				else //��ź ������.
					page.drawImage(bomb[3],item.get(2).getPt().x,item.get(2).getPt().y,100,100,this); 
			}
				
		}
		
		if(attack != 0)
		{
			cat = Cat[cntAnimation];
			//attack++;
			
			if(cntAnimation == 2)
			{
				attack = 0;
				cat = Cat[attack];
			}
		}
		
		page.drawImage(cat, 50, 530, 180, 180, this);//����� �׸�.
		

		if (cntFish == 0)//���� ����� ���� 0�� ���.
		{
			gameInit();//���� �ʱ�ȭ.
			nStage = 1;//���������� 1�� ����.
		
			test.getGameEnd().gameEnd("over");//gameEndPanel�� ������ �����ٴ� ���� �ѱ�.
			
			test.changePanel("gameEnd");//gameEndPanel�� �Ѿ.
		} 
		else if (cntSlime == 3*nStage)//���� �������� ���� ���������� ���� ���� ���� �Ǹ�
		{
			gameInit();//���� �ʱ�ȭ.
		
			if(nStage < 3)//�������� 1,2�� ���.
			{
				game.add(new Game());//��ȭ�鿡 �����ϴ� �ִ� �������� ������ �ø�.(��ü �����Ӽ� ����)
				
				if(nStage == 1)
					test.getGameEnd().gameEnd("stage1");//gameEndPanel�� ���° ���������� �������� �ѱ�.
				else if(nStage == 2)
					test.getGameEnd().gameEnd("stage2");
					
				for(int i=0;i<game.size();i++)
					game.get(i).setStage(nStage);
					
				for(int i=0;i<item.size();i++)
					item.get(i).setStage(nStage);
					
				nStage ++;//�������� ����.
		
			}
			else //�������� 3�� ���.
			{
				nStage = 1;//�������� 1�� �ʱ�ȭ.
				test.getGameEnd().gameEnd("clear");//gameEndPanel�� ������ ���������� �����ٴ� ���� �ѱ�.

			}
			test.changePanel("gameEnd");//gameEndPanel�� �Ѿ.
		}
		
		repaint();//paontComponent() ȣ��.
		
	}// paintComponent()
	
	
	private class ButtonListener implements ActionListener
	{
		// ��ư�� ������ ���.
		public void actionPerformed(ActionEvent event) {
		
			Object obj = event.getSource();

			if (event.getActionCommand() == "")//���� ��ư�� ������ ���.
			{
				gameInit();//ȭ���� ��ȯ�ϱ� ���� ������ �ʱ�ȭ.
			
				// �г� ��ȯ.
				test.changePanel("main");
			}

			//�ؽ�Ʈ�� �Է¹����� ��� ����� �ϴ����� ����
			if (obj == txtInput || obj == btnInput) {	// �ؽ�Ʈ �Է�, ��ư �Է� if����
			
				txtInput.grabFocus();
				for(int i=0;i<game.size();i++)//������ ��ü�� �ܾ� �Է��� ��.
				{
					Game temp = game.get(i);
					if(temp.getNumWord()==0)//�������� ������ �ִ� �ܾ 2�� ���� Ȯ���� 0�̸�.
					{
						if(txtInput.getText().equals(wordPanel.getWordArr(temp.getnWord1(),(temp.getLanguage1()==0)?1:0))&&temp.getPt().x>200)
						{
							temp.setNumWord(temp.getNumWord()+1);
							attack = 1;
							cntAnimation = 0;
						}
					}
					else
					{
						if(txtInput.getText().equals(wordPanel.getWordArr(temp.getnWord2(),(temp.getLanguage2()==0)?1:0))&&temp.getPt().x>200)
						{
							cntSlime ++;//���� �����Ӽ� ����.
							
							attack = 1;
							
							cntAnimation = 0;
							temp.setDie(1);
						}
						
					}
					
					
				}
				
				for(int i=0; i<item.size();i++)//������ ��ü�� �ܾ� �Է��� ��.
				{
					Game temp = item.get(i);
				
					if(txtInput.getText().equals(wordPanel.getWordArr(temp.getnWord1(),(temp.getLanguage1()==0)?1:0))&&temp.getPt().x>200)
					{
						cntAnimation = 0;
						attack = 1;
						
						if(i==0)//�ӵ� ������.
						{
							for(int j=0;j<game.size();j++)//ȭ�鿡 �ִ� ��� �������� �̵��ӵ��� 1�� ����.
								game.get(j).setMove(1);
								
						}
						else if(i==1)//����� ������.
						{
							if(cntFish<5)//���ÿ� ��� �������� 5���� �۴ٸ� 1����.
								cntFish++;
								
							repaint();
						}
						else //��ź ������.
						{
							for(int j=0;j<game.size();j++)//��� ������ ��ü�� ���� �ʱ�ȭ.
							{
								Game t = game.get(j);
								if(t.getPt().x>200 && t.getPt().x<1028)
								{
									cntAnimation = 0;
									t.setDie(1);
								}
								
							}
						}
						temp.slimeInit();//�Էµ� �ܾ�� ������ �ܾ ���� ������ ��ü �ʱ�ȭ.
						temp.setX(-200);
					}
				}	
			}	// �ؽ�Ʈ �Է�, ��ư �Է� if  ��
		
			txtInput.setText("");
		
		}// actionPerformed()
	}// ButtonListener class
	
	public void setTest(Test reference)
	{
		test = reference;
	}//setTest(Test)

}