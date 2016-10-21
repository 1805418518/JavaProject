package baishu957.ShootGame;
import java.awt.image.BufferedImage;

/**
 * Ӣ�ۻ��ࣨhero���̳��Է������ࣨFlyingObj��
 * @author baishu
 * @version 1.02 2016-05-19
 */
public class Hero extends FlyingObj{
	
	private BufferedImage[] images ; //ͼƬ��������
	private int index;				 //step()ͼƬ�˶�����
	
	/**
	 * Ӣ�ۻ����췽��
	 */
	public Hero() {
		image  = ShootGame.hero0;	 //����image
		width  = image.getWidth();	 //����width
		height = image.getHeight();	 //����height
		x 	   = 150;				 //����x
		y 	   = 400;				 //����y
		index  = 0;					 //����index
		images = new BufferedImage[]{//����imagesͼƬ����
				ShootGame.hero0,
				ShootGame.hero1
		};
	}
	
	/**
	 * ��д��������ࣨFlyingObj���е�step()���󷽷�
	 * ʵ��Ӣ�ۻ������ͼƬ�˶�
	 */
	public void step() {
		//������˶�����
		image = images[index++/10%images.length];
	}
	/**
	 * ����Ӣ�ۻ�����Ŀ�
	 * @return	width
	 */
	public int getWidth(){
		return this.width;
	}
	/**
	 * ����Ӣ�ۻ�����ĸ�
	 * @return	height
	 */
	public int getHeight(){
		return this.height;
	}
	/**
	 * Ӣ�ۻ��ƶ�
	 * @param x
	 * @param y
	 */
	public void move(int x,int y){
		this.x = x ;
		this.y = y ;
	}
}
