//********************************************************************
//  
//	게임.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable
{
	private String word;
	
	private Point slimePt;//객체의 위치.
	
	private int nStage;//스테이지.
	private int slimeColor, rLine, line[];//슬라임 색상, y좌표.
	private int numWord, nWord1, nWord2, language1, language2;//몇번째 단어인지, 한영.
	private int move,max,min,time,tmax,tmin;//이동거리, 이동거리최대최소, 등장시간,등장시간최대최소.
	private int Die;//객체 죽음 확인.
	
	public Game()//생성자.
	{//객체들의 정보를 초기화 시킴.
	
		//단어장에서 어떤 단어를 가져올지 랜덤으로 결정.
		nWord1 = (int)(Math.random()*30);
		language1 = (int)(Math.random()*2);
		nWord2 = (int)(Math.random()*30);
		language2 = (int)(Math.random()*2);
		
		max = 3;//이동거리 최대 최소 설정.
		min = 2;
		
		tmax=6000;//얼마나 시간이 지난 후에 객체가 등장할 지 결정.
		tmin=1000;
		time = (int)(Math.random()*tmax)+tmin;
			
		rLine = (int)(Math.random()*4);//객체가 몇번째 라인에서 등장할지 결정.
		
		line = new int[4];//라인 위치 설정.
		line[0] = 40;
		line[1] = 180;
		line[2] = 320;
		line[3] = 460;
		
		slimePt = new Point();//객체의 위치정보.
		slimePt.x = -200;//처음에 객체의 위치는 화면 바깥에 위치.
		slimePt.y = line[rLine];
		
		slimeColor = 0;	//어떤 색상의 슬라임인지 결정.
		
		Die = 0;
	}//Game()
	
	public void slimeInit()//객체 정보 초기화.(개체가 죽고나면 초기화할때 사용)
	{
		max = nStage + 2;//스테이지가 증가한다면 이동거리 최대를 늘림(->빨리짐)
		
		tmax -= nStage*300;//스테이지가 증가한다면 객체의 등장시간을 줄임.
		
		move = (int)(Math.random()*max-min+1)+min;//각각의 객체가 다른 이동속도를 가짐.
		time = (int)(Math.random()*tmax-tmin+1)+tmin;//각각의 객체가 다른 등장시간을 가짐.
		
		slimeColor = (int)(Math.random()*3);//각각의 객체가 다른 색상을 가짐..
		
		rLine = (int)(Math.random()*4);//각각의 객체가 등장할 라인을 결정.
		
		slimePt.x = 1000;//초기화를 하면 객체를 화면오른쪽 바깥에 위치하도록함.
		slimePt.y = line[rLine];
		
		numWord = (int)(Math.random()*(nStage+2));//두개의 단어를 가질 확률.(numWord == 0일 경우 단어는 2개)
		
		nWord1 = (int)(Math.random()*30);//어떤 단어를 가질지 결정.
		language1 = (int)(Math.random()*2);//어떤 언어로 단어를 나타낼지 결정.
	
		nWord2 = (int)(Math.random()*30);//어떤 단어를 가질지 결정.
		language2 = (int)(Math.random()*2);//어떤 언어로 단어를 나타낼지 결정.
		
	}//slimeInit()
	
	public void setMove(int mov)//이동속도를 결정함.(속도아이템을 사용하면 적용)
	{
		move = mov;
	}//setMove(int)
	
	public void setStage(int stage)//게임 단계를 얻어옴.
	{
		nStage = stage;
	}//setStage
	
	public Point getPt()//객체의 위치정보를 반환.
	{
		return slimePt;	
	}//getPt()
	
	public void setX(int SlimeX)//객체의 x좌표를 바꿈.(개체가 죽으면 화면바깥으로 옮길때 사용)
	{
		slimePt.x = SlimeX;
	}//setX
	
	public int getSlimeColor()//슬라임의 색상이 무엇인지 알수있도록함.
	{
		return slimeColor;
	}//getSlimeColor()
	
	public int getNumWord()//객체가 가진 단어를 2개 가질 확률.
	{
		return numWord;
	}//getNumWord()
	
	public void setNumWord(int w)//객체가 가진 단어를 2개 가질 확률을 고침.
	{
		numWord = w;
	}//setNumWord()
	
	public int getnWord1()//객체가 어떤 단어를 가지고 있는지 확인.
	{
		return nWord1;
	}//getWord1()
	
	public int getLanguage1()//객체가 어떤 언어로된 단어를 가지고 있는지 확인.
	{
		return language1;
	}//setLanguage1()
	
	public int getnWord2()//객체가 어떤 단어를 가지고 있는지 확인.
	{
		return nWord2;
	}//getWord2()
	
	public int getLanguage2()//객체가 어떤 언어로된 단어를 가지고 있는지 확인.
	{
		return language2;
	}//setLanguage2()
	
	public boolean getFish()//슬라임이 물고기를 잡았나 확인.
	{
		if(slimePt.x<161&&slimePt.x>150)//일정 라인을 넘어가는 순간 true반환.
			return true;
		else
			return false;
	}//getFish()
	
	public int getDie()
	{
		return Die;
	}//getDie()
	
	public void setDie(int d)
	{
		Die = d;
	}//setDie()

	public void checkSlime()//객체 정보를 초기화 해야하는지 확인.
	{
		if(slimePt.x<((-1)*time))//객체의 x좌표가 등장시간만큼 화면밖으로 이동하면 초기화.
			slimeInit();//등장시간이 정말 시간이 아니라 일정 수준만큼 슬라임이 왼쪽 화면밖으로 이동하면 초기화시킬지 정하는 변수.
	}//checkSlime()
	
	public void run()//thread
	{
		try
		{
			//이미지의 이동.
			if(slimePt.x>200)//객체가 일정범위 밖이라면.
			{
				if(Die == 0)
					slimePt.x -= move;//객체의 이동거리만큼 이동.
				Thread.sleep(10);
			}
			else //객체가 일정범위 안이라면.
			{
				slimePt.x -= 10;//객체를 일정속도로 이동.
				Thread.sleep(10);
			}
			checkSlime();//슬라임을 초기화할지 확인.
		}catch(Exception e){}
	}//run()
	
}//Game class