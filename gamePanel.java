//********************************************************************
//  
//	게임 화면.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class gamePanel extends JPanel // implements Runnable
{
	private JButton goMain, btnInput; // 버튼.
	private Image Slime[][], slime, Fish[], fish, Cat[], cat, fishF[], fishItem, bomb[], bombItem,speed[], speedItem, back;//이미지 객체.
	private int cntAnimation, cntTime, cntSlime, cntFish, nStage, attack;//게임에 필요한 변수들.
	private Toolkit tool;//이미지를 그리기 위한 툴킷.

	private Test test;//패널의 정보를 가지고 있는 Test 클래스.

	private JTextField txtInput; //단어입력.
	private ButtonListener btnL;//버튼 리스너.
	
	private ArrayList<Game> game;//슬라임 객체.
	private ArrayList<Game> item;//아이템 객체.

	public gamePanel()//패널의 기본 설정.
	{
		// 기본 설정.
		setBackground(Color.white);
		setPreferredSize(new Dimension(1028, 720));
		setLayout(null);
		
		// 메뉴 생성.
		goMain = new JButton (new ImageIcon("img\\home.png"));
		goMain.setBounds(10,10,54,54);
		goMain.setContentAreaFilled(false);
		goMain.setBorderPainted(false);
		goMain.addActionListener (new ButtonListener());
		goMain.setPressedIcon(new ImageIcon("img\\home_p.png"));
		add(goMain);

		// 이미지를 그리기 위한 툴킷.
		tool = Toolkit.getDefaultToolkit();

		//객체의 애니메이션을 위해.
		Fish = new Image[6];//물고기[갯수]
		Slime = new Image[3][9];//슬라임[색상][애니메이션]

		// 이미지 로딩.
		for(int i=1;i<4;i++)
        	for(int j=1;j<10;j++)
        		this.Slime[i-1][j-1] = tool.getImage("img\\slime"+i+"-"+j+".png");//슬라임 이미지.
        		
        for(int i=0;i<6;i++)
	        this.Fish[i] = tool.getImage("img\\fish"+i+".png");//물고기 이미지.
        
        Cat = new Image[4];
        
        for(int i=0;i<4;i++)
	        this.Cat[i] = tool.getImage("img\\cat"+i+".png");//고양이 이미지.
        cat = Cat[0];
        attack = 0;
        
        fishF = new Image[4];
        bomb = new Image[4];
        speed = new Image[4];
        
        for(int i=0;i<4;i++)
        {
	        this.fishF[i] = tool.getImage("img\\fishf"+i+".png");//물고기 아이템 이미지.
	        this.bomb[i] = tool.getImage("img\\bomb"+i+".png");//폭탄 아이템 이미지/
	        this.speed[i] = tool.getImage("img\\time"+i+".png");//속도증가 아이템 이미지.
    	}
    	
        this.back = tool.getImage("img\\background.png");//배경 이미지.
        
        this.slime = Slime[0][0];
        this.fish = Fish[5];

		//슬라임 객체 생성.
		game = new ArrayList<Game>();
		for(int i=0; i<4; i++)
			game.add(new Game());
			
		//아이템 객체 생성.
		item = new ArrayList<Game>();
		for(int i=0; i<3;i++)
			item.add(new Game());

		nStage = 1;//스테이지 1.
		
		gameInit();//게임 객체 초기화.

		btnL = new ButtonListener();//버튼 리스너 생성.
		
		txtInput = new JTextField();//단어 입력을 위한 텍스트 필드 생성.
		txtInput.setBounds(350, 650, 250, 40);
		txtInput.addActionListener(btnL);
		add(txtInput);

		btnInput = new JButton(new ImageIcon("img\\enter.png"));//단어 입력을 위한 확인 버튼 생성.
		btnInput.setBounds(620, 650, 70, 40);
		btnInput.setActionCommand("Enter");
		btnInput.setContentAreaFilled(false);
		btnInput.setBorderPainted(false);
		btnInput.addActionListener(btnL);
		btnInput.setPressedIcon(new ImageIcon("img\\enter_p.png"));
		this.add(btnInput);


	}// gamePanel()

	public void gameInit()//객체(슬라임, 생선) 정보 초기화.
	{
		cntAnimation = cntTime = cntSlime = 0;
		cntFish = 5;
		
		for(int i=0;i<game.size();i++)//모든 슬라임 객체 초기화.
		{
			game.get(i).slimeInit();
			game.get(i).setX(-200);
		}
		
		for(int i=0;i<item.size();i++)//모든 생선 객체 초기화.
		{
			item.get(i).slimeInit();
			item.get(i).setX(-200);
		}	
	}//gameInit()
	
	public void paintComponent(Graphics page)//이미지를 그림.
	{
		super.paintComponent(page);//부모로 부터 권한 받아옴.

		page.drawImage(back,0,0,1028,720,this);
		//슬라임이 생선을 가져가게 되는 범위를 눈에 보여주기 위한 선.
		page.setColor(Color.red);
		page.drawLine(220, 20, 220, 580);

		// 쓰레드 각각 run().
		for(int i=0;i<game.size();i++)
			game.get(i).run();
		for(int i=0;i<item.size();i++)
			item.get(i).run();
			

		// 슬라임 애니메이션을 위해.
		if (cntTime == 5) {
			cntAnimation++;
			cntAnimation %= 3;

			cntTime = 0;
		} else
			cntTime++;

		// 이미지를 그림.
		page.setFont(new Font("돋움",Font.BOLD,30));//단어 글자에 대한 설정.
		page.setColor(Color.yellow);
	
		speedItem = speed[(cntAnimation+1)%2];
		bombItem = bomb[(cntAnimation+2)%2];
		fishItem = fishF[(cntAnimation+3)%2];

	
		for(int i=0;i<game.size();i++)//슬라임 객체 그리기.
		{
			Game temp = game.get(i);
			
			// 선을 넘는 슬라임 갯수 확인->물고기수 감소.
			if(temp.getFish() && (cntFish > 0))
				cntFish--;
					
			fish = Fish[cntFish];//현재 생선의 갯수에 따라 어떤 생선 이미지를 띄울지 받아옴,
		
			page.drawImage(fish, 40, 250, 140, 140, this);//생선 그림.
			
			if(temp.getPt().x>200)//슬라임이 일정 범위 바깥일 경우.
			{
				if(temp.getDie() != 0)//슬라임이 죽었을 경우.
				{
					slime = Slime[temp.getSlimeColor()][((cntAnimation + temp.getSlimeColor()) % 3)+6];//물고리르 물고 있는 슬라임 이미지  받아옴.

					if((((cntAnimation + temp.getSlimeColor()) % 3)+6)==8)
					{
						temp.slimeInit();//객체 초기화.
						temp.setX(-200);
						temp.setDie(0);
					}
				}
				else
					slime = Slime[temp.getSlimeColor()][(cntAnimation + temp.getSlimeColor()) % 3];//어떤 슬라임을 그릴지 받아옴.
				page.drawImage(slime,temp.getPt().x,temp.getPt().y,140,140,this);//슬라임.
				
				if(temp.getNumWord()==0)
					page.drawString(wordPanel.getWordArr(temp.getnWord1(),temp.getLanguage1()),temp.getPt().x+25,temp.getPt().y+130);//단어.
				else
					page.drawString(wordPanel.getWordArr(temp.getnWord2(),temp.getLanguage2()),temp.getPt().x+25,temp.getPt().y+130);//단어.
			}
			else //슬라임이 일정 범위 안일 경우. 물고기를 물고 가는 이미지를 그림.
			{
				slime = Slime[temp.getSlimeColor()][((cntAnimation + temp.getSlimeColor()) % 3)+3];//물고리르 물고 있는 슬라임 이미지  받아옴.
				page.drawImage(slime,temp.getPt().x,temp.getPt().y,140,140,this);
			}
				
		}
		
		for(int i=0;i<item.size();i++)//아이템 객체 그리기.
		{
			Game temp = item.get(i);
						
				
			if(temp.getPt().x>200)//각 아이템이 일정 범위 바깥이면 단어를 가지고 있음.
			{				
				if(i==0)//속도 아이템.
					page.drawImage(speedItem,item.get(0).getPt().x,item.get(0).getPt().y,300,150,this);
				else if(i==1)//물고기 아이템.
					page.drawImage(fishItem,item.get(1).getPt().x,item.get(1).getPt().y,300,150,this);
				else //폭탄 아이템.
					page.drawImage(bombItem,item.get(2).getPt().x,item.get(2).getPt().y,300,150,this); 
					
				page.drawString(wordPanel.getWordArr(temp.getnWord1(),temp.getLanguage1()),temp.getPt().x+100,temp.getPt().y+130);
			}
			else
			{
				if(i==0)//속도 아이템.
					page.drawImage(speed[3],item.get(0).getPt().x,item.get(0).getPt().y,100,100,this);
				else if(i==1)//물고기 아이템.
					page.drawImage(fishF[3],item.get(1).getPt().x,item.get(1).getPt().y,100,100,this);
				else //폭탄 아이템.
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
		
		page.drawImage(cat, 50, 530, 180, 180, this);//고양이 그림.
		

		if (cntFish == 0)//남은 물고기 수가 0일 경우.
		{
			gameInit();//게임 초기화.
			nStage = 1;//스테이지를 1로 설정.
		
			test.getGameEnd().gameEnd("over");//gameEndPanel에 게임이 끝났다는 것을 넘김.
			
			test.changePanel("gameEnd");//gameEndPanel로 넘어감.
		} 
		else if (cntSlime == 3*nStage)//죽은 슬라임의 수가 스테이지에 따라 일정 수가 되면
		{
			gameInit();//게임 초기화.
		
			if(nStage < 3)//스테이지 1,2일 경우.
			{
				game.add(new Game());//한화면에 등장하는 최대 슬라임의 갯수를 늘림.(전체 슬라임수 증가)
				
				if(nStage == 1)
					test.getGameEnd().gameEnd("stage1");//gameEndPanel에 몇번째 스테이지가 끝났는지 넘김.
				else if(nStage == 2)
					test.getGameEnd().gameEnd("stage2");
					
				for(int i=0;i<game.size();i++)
					game.get(i).setStage(nStage);
					
				for(int i=0;i<item.size();i++)
					item.get(i).setStage(nStage);
					
				nStage ++;//스테이지 증가.
		
			}
			else //스테이지 3일 경우.
			{
				nStage = 1;//스테이지 1로 초기화.
				test.getGameEnd().gameEnd("clear");//gameEndPanel에 마지막 스테이지가 끝났다는 것을 넘김.

			}
			test.changePanel("gameEnd");//gameEndPanel로 넘어감.
		}
		
		repaint();//paontComponent() 호출.
		
	}// paintComponent()
	
	
	private class ButtonListener implements ActionListener
	{
		// 버튼을 눌렀을 경우.
		public void actionPerformed(ActionEvent event) {
		
			Object obj = event.getSource();

			if (event.getActionCommand() == "")//메인 버튼이 눌렸을 경우.
			{
				gameInit();//화면을 전환하기 전에 게임을 초기화.
			
				// 패널 전환.
				test.changePanel("main");
			}

			//텍스트를 입력받으면 어떻게 해줘야 하는지에 대해
			if (obj == txtInput || obj == btnInput) {	// 텍스트 입력, 버튼 입력 if시작
			
				txtInput.grabFocus();
				for(int i=0;i<game.size();i++)//슬라임 객체의 단어 입력을 비교.
				{
					Game temp = game.get(i);
					if(temp.getNumWord()==0)//슬라임이 가지고 있는 단어를 2개 가질 확률이 0이면.
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
							cntSlime ++;//죽은 슬라임수 증가.
							
							attack = 1;
							
							cntAnimation = 0;
							temp.setDie(1);
						}
						
					}
					
					
				}
				
				for(int i=0; i<item.size();i++)//아이템 객체와 단어 입력을 비교.
				{
					Game temp = item.get(i);
				
					if(txtInput.getText().equals(wordPanel.getWordArr(temp.getnWord1(),(temp.getLanguage1()==0)?1:0))&&temp.getPt().x>200)
					{
						cntAnimation = 0;
						attack = 1;
						
						if(i==0)//속도 아이템.
						{
							for(int j=0;j<game.size();j++)//화면에 있는 모든 슬라임의 이동속도를 1로 설정.
								game.get(j).setMove(1);
								
						}
						else if(i==1)//물고기 아이템.
						{
							if(cntFish<5)//접시에 담긴 물고기수가 5보다 작다면 1증가.
								cntFish++;
								
							repaint();
						}
						else //폭탄 아이템.
						{
							for(int j=0;j<game.size();j++)//모든 슬라임 객체의 정보 초기화.
							{
								Game t = game.get(j);
								if(t.getPt().x>200 && t.getPt().x<1028)
								{
									cntAnimation = 0;
									t.setDie(1);
								}
								
							}
						}
						temp.slimeInit();//입력된 단어와 동일한 단어를 가진 아이템 객체 초기화.
						temp.setX(-200);
					}
				}	
			}	// 텍스트 입력, 버튼 입력 if  끝
		
			txtInput.setText("");
		
		}// actionPerformed()
	}// ButtonListener class
	
	public void setTest(Test reference)
	{
		test = reference;
	}//setTest(Test)

}