package baishu957.ShootGame;
import java.awt.image.BufferedImage;//����ͼƬ����
import java.util.Random;

/**
 * �����ࣺ�����FlyingObj��
 * @author baishu
 * @version	1.02	2016-05-19
 */
abstract public class FlyingObj {
	
	protected int 			x;		//x����
	protected int 			y;		//y����
	protected int 			width;  //ͼƬ��
	protected int 			height; //ͼƬ��
	protected BufferedImage image;  //ͼƬ����
	
	/**
	 * ÿ�ζ����˶�һ���ĳ��󷽷�step()
	 */
	abstract public void step();
}
