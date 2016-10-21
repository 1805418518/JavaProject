package baishu957.ShootGame;

import java.util.Random;

/**
 * С�ɻ��ࣨBigPlane���̳��Է������ࣨFlyingObj����ʵ���˵л���Enemy���ӿ�
 * @author baihsu
 * @version	1.02	2016-05-19
 */
public class SmallPlane extends FlyingObj implements Enemy{
	
	private int speed;//С�ɻ������˶��ٶ�
	
	Random random = new Random();
	/**
	 * �������췽��
	 */
	public SmallPlane(){
		image  = ShootGame.airplane;
		width  = image.getWidth();
		height = image.getHeight();
		x      = random.nextInt(400)-width;//��ֹ�����ڴ�������
		y      = -this.height;
		speed  = 2;
	}
	/**	
	 * ��дײ����Knocks���ӿ��е�beKnocked()���󷽷�������ֵΪboolean
	 */
	public boolean beKnocked() {
		return false;
	}
	/**
	 * ��дײ����Knocks���ӿ��е�getType()���󷽷�
	 */
	public void getType() {
		
	}
	/**
	 * ��д��������ࣨFlyingObj���е�step()���󷽷�
	 * ʵ��С�л�������ƶ�
	 */
	public void step() {
		y += speed;//�����˶�
	}
}
