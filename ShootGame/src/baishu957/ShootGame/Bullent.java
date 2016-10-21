package baishu957.ShootGame;

import java.util.Random;

/**
 * �ӵ��ࣨBullent���̳��Է������ࣨFlyingObj��
 * @author baishu
 * @version	1.02	2016-05-19
 */
public class Bullent extends FlyingObj{

	private int speed; 				//�ӵ�������˶��ٶ�
	Random random = new Random();
	
	/**
	 * �ӵ���Ĺ��췽��
	 * @param hx	������������ӵ������x
	 * @param hy	������������ӵ������y
	 */
	
	public Bullent(int hx,int hy){
		image  = ShootGame.bullet;  //���ö����image
		width  = image.getWidth();  //���ÿ�
		height = image.getHeight(); //���ø�
		x 	   = hx ;				//����x
		y      = hy ;				//����y
		speed  = 2; 				//�����ٶ�speed
	}
	
	/**
	 * ��д��������ࣨFlyingObj���е�step()���󷽷�
	 * ʵ���ӵ�������ƶ�
	 */
	public void step() {
		y -= speed;//�ӵ��������Ϸɣ������á�-��
	}
	
	/**
	 * ײ���¼�
	 * @return boolean
	 */
	public boolean beBoom(FlyingObj obj){
		int x1 = x-obj.width/2;
		int y1 = y-obj.height/2;
		int x2 = x+obj.width/2;
		int y2 = y+obj.height/2;
		
		int x0 = obj.x + obj.width/2;
		int y0 = obj.y + obj.height/2;
		
		return  x0 >= x1 && x0 <= x2
				&&
				y0 >= y1 && y0 <= y2;
		
	}
}



