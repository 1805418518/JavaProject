package baishu957.ShootGame;

import java.util.Random;

/**
 * С�۷��ࣨBigBee���̳��Է������ࣨFlyingObj����ʵ���˽�����Award���ӿ�
 * @author baishu
 * @version 1.02	2016-05-19
 */
public class SmallBee extends FlyingObj implements Award{

	private int xSpeed;//���ƣ�X�ᣩ�ٶ�
	private int ySpeed;//���䣨Y�ᣩ�ٶ�
	Random random = new Random();
	
	/**	�������췽��	*/
	public SmallBee() {
		image  = ShootGame.bee;//����С�۷��ͼƬ
		width  = image.getWidth();//����width��ֵ
		height = image.getHeight();//����height��ֵ
		x 	   = random.nextInt(400);//����x��ֵ���ڴ����Ϸ����λ�ó���
		y 	   = -this.height;//����y��ֵ������С�۷����ʱ��Ӧ�����Ϸ�����֮��
		xSpeed = 1;//���ú����ٶ�
		ySpeed = 2;//���������ٶ�
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
	 * ʵ�ִ��۷������ƶ�
	 */
	public void step() {
		x += xSpeed;//С�۷������X��ĺ����ƶ�
		/*
		 * ��С�۷���󵽴ﴰ�ڱ�Ե����x >= ����width-this.widthʱ
		 * �ı�xSpeed = -xSpeed,ʹС�۷������X�����������ƶ�
		 */
		if (x >= (ShootGame.WIDTH - width)) {
			xSpeed = -xSpeed;
		}
		y += ySpeed;//С�۷�����������˶�
	}
}
