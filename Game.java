//********************************************************************
//  
//	����.
//  
//********************************************************************

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Game implements Runnable
{
	private String word;
	
	private Point slimePt;//��ü�� ��ġ.
	
	private int nStage;//��������.
	private int slimeColor, rLine, line[];//������ ����, y��ǥ.
	private int numWord, nWord1, nWord2, language1, language2;//���° �ܾ�����, �ѿ�.
	private int move,max,min,time,tmax,tmin;//�̵��Ÿ�, �̵��Ÿ��ִ��ּ�, ����ð�,����ð��ִ��ּ�.
	private int Die;//��ü ���� Ȯ��.
	
	public Game()//������.
	{//��ü���� ������ �ʱ�ȭ ��Ŵ.
	
		//�ܾ��忡�� � �ܾ �������� �������� ����.
		nWord1 = (int)(Math.random()*30);
		language1 = (int)(Math.random()*2);
		nWord2 = (int)(Math.random()*30);
		language2 = (int)(Math.random()*2);
		
		max = 3;//�̵��Ÿ� �ִ� �ּ� ����.
		min = 2;
		
		tmax=6000;//�󸶳� �ð��� ���� �Ŀ� ��ü�� ������ �� ����.
		tmin=1000;
		time = (int)(Math.random()*tmax)+tmin;
			
		rLine = (int)(Math.random()*4);//��ü�� ���° ���ο��� �������� ����.
		
		line = new int[4];//���� ��ġ ����.
		line[0] = 40;
		line[1] = 180;
		line[2] = 320;
		line[3] = 460;
		
		slimePt = new Point();//��ü�� ��ġ����.
		slimePt.x = -200;//ó���� ��ü�� ��ġ�� ȭ�� �ٱ��� ��ġ.
		slimePt.y = line[rLine];
		
		slimeColor = 0;	//� ������ ���������� ����.
		
		Die = 0;
	}//Game()
	
	public void slimeInit()//��ü ���� �ʱ�ȭ.(��ü�� �װ��� �ʱ�ȭ�Ҷ� ���)
	{
		max = nStage + 2;//���������� �����Ѵٸ� �̵��Ÿ� �ִ븦 �ø�(->������)
		
		tmax -= nStage*300;//���������� �����Ѵٸ� ��ü�� ����ð��� ����.
		
		move = (int)(Math.random()*max-min+1)+min;//������ ��ü�� �ٸ� �̵��ӵ��� ����.
		time = (int)(Math.random()*tmax-tmin+1)+tmin;//������ ��ü�� �ٸ� ����ð��� ����.
		
		slimeColor = (int)(Math.random()*3);//������ ��ü�� �ٸ� ������ ����..
		
		rLine = (int)(Math.random()*4);//������ ��ü�� ������ ������ ����.
		
		slimePt.x = 1000;//�ʱ�ȭ�� �ϸ� ��ü�� ȭ������� �ٱ��� ��ġ�ϵ�����.
		slimePt.y = line[rLine];
		
		numWord = (int)(Math.random()*(nStage+2));//�ΰ��� �ܾ ���� Ȯ��.(numWord == 0�� ��� �ܾ�� 2��)
		
		nWord1 = (int)(Math.random()*30);//� �ܾ ������ ����.
		language1 = (int)(Math.random()*2);//� ���� �ܾ ��Ÿ���� ����.
	
		nWord2 = (int)(Math.random()*30);//� �ܾ ������ ����.
		language2 = (int)(Math.random()*2);//� ���� �ܾ ��Ÿ���� ����.
		
	}//slimeInit()
	
	public void setMove(int mov)//�̵��ӵ��� ������.(�ӵ��������� ����ϸ� ����)
	{
		move = mov;
	}//setMove(int)
	
	public void setStage(int stage)//���� �ܰ踦 ����.
	{
		nStage = stage;
	}//setStage
	
	public Point getPt()//��ü�� ��ġ������ ��ȯ.
	{
		return slimePt;	
	}//getPt()
	
	public void setX(int SlimeX)//��ü�� x��ǥ�� �ٲ�.(��ü�� ������ ȭ��ٱ����� �ű涧 ���)
	{
		slimePt.x = SlimeX;
	}//setX
	
	public int getSlimeColor()//�������� ������ �������� �˼��ֵ�����.
	{
		return slimeColor;
	}//getSlimeColor()
	
	public int getNumWord()//��ü�� ���� �ܾ 2�� ���� Ȯ��.
	{
		return numWord;
	}//getNumWord()
	
	public void setNumWord(int w)//��ü�� ���� �ܾ 2�� ���� Ȯ���� ��ħ.
	{
		numWord = w;
	}//setNumWord()
	
	public int getnWord1()//��ü�� � �ܾ ������ �ִ��� Ȯ��.
	{
		return nWord1;
	}//getWord1()
	
	public int getLanguage1()//��ü�� � ���ε� �ܾ ������ �ִ��� Ȯ��.
	{
		return language1;
	}//setLanguage1()
	
	public int getnWord2()//��ü�� � �ܾ ������ �ִ��� Ȯ��.
	{
		return nWord2;
	}//getWord2()
	
	public int getLanguage2()//��ü�� � ���ε� �ܾ ������ �ִ��� Ȯ��.
	{
		return language2;
	}//setLanguage2()
	
	public boolean getFish()//�������� ����⸦ ��ҳ� Ȯ��.
	{
		if(slimePt.x<161&&slimePt.x>150)//���� ������ �Ѿ�� ���� true��ȯ.
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

	public void checkSlime()//��ü ������ �ʱ�ȭ �ؾ��ϴ��� Ȯ��.
	{
		if(slimePt.x<((-1)*time))//��ü�� x��ǥ�� ����ð���ŭ ȭ������� �̵��ϸ� �ʱ�ȭ.
			slimeInit();//����ð��� ���� �ð��� �ƴ϶� ���� ���ظ�ŭ �������� ���� ȭ������� �̵��ϸ� �ʱ�ȭ��ų�� ���ϴ� ����.
	}//checkSlime()
	
	public void run()//thread
	{
		try
		{
			//�̹����� �̵�.
			if(slimePt.x>200)//��ü�� �������� ���̶��.
			{
				if(Die == 0)
					slimePt.x -= move;//��ü�� �̵��Ÿ���ŭ �̵�.
				Thread.sleep(10);
			}
			else //��ü�� �������� ���̶��.
			{
				slimePt.x -= 10;//��ü�� �����ӵ��� �̵�.
				Thread.sleep(10);
			}
			checkSlime();//�������� �ʱ�ȭ���� Ȯ��.
		}catch(Exception e){}
	}//run()
	
}//Game class