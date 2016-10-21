package baishu957.ShootGame;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Graphics;
import java.util.Arrays;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

/**
 * ShootGame����̳��Ի�����JPanel
 * @author baishu
 * @version 1.02 2016-05-20
 */

public class ShootGame extends JPanel{

	public final static int WIDTH  = 400;//���û���Ŀ�
	public final static int HEIGHT = 654;//���û���ĸ�
	
	
	private final static int START = 0;//��ʼ״̬
	private final static int PAUSER = 1;//��ͣ״̬
	private final static int RUNNING = 2;//����״̬
	private final static int GAME_OVER = 3;//����״̬
	private static int state = 0;//����״̬����
	
	public static BufferedImage start;//��ʼ״̬ͼƬ
	public static BufferedImage background;//����ͼƬ
	public static BufferedImage pause;//��ͣ״̬ͼƬ
	public static BufferedImage gameover;//����״̬ͼƬ
	public static BufferedImage airplane;//�л�ͼƬ
	public static BufferedImage bee;//�۷�ͼƬ
	public static BufferedImage hero0;//Ӣ�ۻ�ͼƬ0
	public static BufferedImage hero1;//Ӣ�ۻ�ͼƬ1
	public static BufferedImage bullet;//�ӵ�ͼƬ

	private FlyingObj[] flyings ={};//��������ˣ���������
	private Bullent[] bullents ={};//�ӵ���������
	private Hero hero = new Hero();//Ӣ�ۻ������ʼ��
	
	static{//���ؾ�̬��Դ
		try {
			//����ͼƬ
			start      = ImageIO.read(ShootGame.class.getResource("start.png"));
			pause	   = ImageIO.read(ShootGame.class.getResource("pause.png"));
			gameover   = ImageIO.read(ShootGame.class.getResource("gameover.png"));
			airplane   = ImageIO.read(ShootGame.class.getResource("airplane.png"));
			bee        = ImageIO.read(ShootGame.class.getResource("bee.png"));
			hero0      = ImageIO.read(ShootGame.class.getResource("hero0.png"));
			hero1      = ImageIO.read(ShootGame.class.getResource("hero1.png"));
			bullet     = ImageIO.read(ShootGame.class.getResource("bullet.png"));
			background = ImageIO.read(ShootGame.class.getResource("background.png"));
		} catch(Exception ex){
			ex.printStackTrace();
		}
	}

	/**
	 * nextOne:FlyingObject���󹤳�
	 * @���ܣ�������ɵл����۷���󣬲�����
	 */
	
	public static FlyingObj nextOne(){
		Random random = new Random();
		
		int type = random.nextInt(20);//���ƶ�������
		if(type % 10 == 0){//���ش��۷����
			return new BigBee();
		}else if (type % 5 == 0) {//����С�۷����
			return new SmallBee();
		}else {//���صл�����
			return new SmallPlane();
		}
	}
	private int flyEnterIndex = 0;//���Ʒ������볡����
	/**
	 * enterAction:��������ӵ��볡����
	 * @���ܣ��������л�������flyings�м���л�����
	 */
	public void enterAction(){
		flyEnterIndex ++;//��������
		if(flyEnterIndex % 40 == 0){//���Ʒ������볡����
			flyings = Arrays.copyOf(flyings, flyings.length+1);//��������������
			//���÷�������󹤳������������µķ�����������flyings�������һ��
			flyings[flyings.length - 1] = ShootGame.nextOne();
			/*ͬ��ʵ���ӵ�������������ӵ��볡*/
			Bullent b = new Bullent(hero.x+hero.getWidth()/2, hero.y - 20);
			bullents = Arrays.copyOf(bullents, bullents.length+1);
			bullents[bullents.length-1] = b;
		}
	}
	
	/**
	 * stepAction:�����˶�����
	 * @���ܣ����ƶ�����˶�
	 */
	public void stepAction(){
		/*�������������飬ʹ������������ÿ�����󶼵����˶�����step()*/
		for (int i = 0; i < flyings.length; i++) {
			flyings[i].step();
		}
		/*�����ӵ����飬ʹ�ӵ�������ÿ�����󶼵����˶�����step()*/
		for (int i = 0; i < bullents.length; i++) {
			bullents[i].step();
		}
		/*Ӣ�ۻ������ƶ�*/
		hero.step();
	}
	
	private int bullentsIndex = 0;//�����ӵ��볡����
	
	/**
	 * shootAction: �ӵ��볡
	 * @���ܣ�ʵ���ӵ��볡
	 */
	/*public void shootAction(){
		bullentsIndex ++;
		if(bullentsIndex % 40 == 0){
			Bullent b = new Bullent(hero.x+hero.getWidth()/2, hero.y - 20);
			bullents = Arrays.copyOf(bullents, bullents.length+1);
			bullents[bullents.length-1] = b;
		}
	}*/
	
/*	public void heroMoveAction() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyPressed(e);
				int keyCode = e.getKeyCode();
				System.out.println(keyCode);
			}
		});
	}*/
	/**
	 * �ӵ��͵л�ײ���жϷ���
	 */
	public void boomAction(){
		for (int i = 0; i < bullents.length; i++) {
			for (int j = 0; j < flyings.length; j++) {
				if(bullents[i].beBoom(flyings[j])){
					FlyingObj temp = flyings[flyings.length-1];
					flyings[flyings.length-1] = flyings[j];
					flyings[j] = temp;
					flyings = Arrays.copyOf(flyings, flyings.length-1);
					return;
				}
			}
		}
	}
	private int time01 = 10;//ʱ����
	private KeyAdapter l;
	/**
	 * action����Ҫ��������������ʱ����������������
	 * @���ܣ���������������ִ�С�����ʱ����������������
	 */
	public void action(){
		
		/*	����һ����ʱ����Ϸ�Ǹ�timer	*/
		Timer timer = new Timer();
		/*�������Դ�����*/
		timer.schedule(new TimerTask() {//������д�����ڲ���
			/**
			 * run����д��run����
			 * @���ܣ����ƶ�ʱ�������еķ���
			 */
			@Override
			public void run() {
				enterAction();//��������ӵ��볡����
				stepAction();//�����˶�����
				boomAction();//�ж��ӵ��͵л���ײ
				//heroMoveAction();//Ӣ�ۻ��ƶ�
				//shootAction();
				repaint();//�ػ�
			}
		}, time01, time01);
	}
	/**
	 * ��д��paint����
	 * ��ͼ
	 */
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawImage(background, 0, 0, null);//������ͼ
		paintHero(g);//��Ӣ�ۻ�ͼ
		paintKnocks(g);//������ͼ
		paintBullents(g);//���ӵ�ͼ
	}
	//��Ӣ�ۻ�ͼ
	public void paintHero(Graphics g){
		g.drawImage(hero.image, hero.x, hero.y, null);
	}
	//������ͼ
	public void paintKnocks(Graphics g){
		for (int i = 0; i < flyings.length; i++) {
			FlyingObj f = flyings[i];
			g.drawImage(f.image, f.x, f.y, null);
		}
	}
	//���ӵ�ͼ
	public void paintBullents(Graphics g){
		for (int i = 0; i < bullents.length; i++) {
			Bullent b = bullents[i];
			g.drawImage(b.image, b.x, b.y, null);
		}
	}
	/**
	 * ���ñ������巽������frame�Ĳ��������һ�������ķ�����Ϊ�˱�����staitc�в��ܷ��ʷǾ�̬����
	 * @param game
	 */
	public void window(ShootGame game) {
		JFrame frame = new JFrame("Fly"); //�������ڶ���
		frame.add(game); //�������ӵ�������
		
		frame.setSize(WIDTH, HEIGHT); //���ô��ڴ�С
		frame.setAlwaysOnTop(true); //������������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //���ô���Ĭ�Ϲرղ���(�رմ������˳�����)
		frame.setLocationRelativeTo(null); //���ô��ڳ�ʼλ��(����)
		frame.setVisible(true); //1.���ô��ڿɼ�  2.�������paint()
		/*��frame��Ӽ��̼�������*/
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				//System.out.println(e.getKeyCode());
				//super.keyPressed(e);
				int speed = 10;//�ɻ��ƶ��ٶ�
				int x = hero.x;
				int y = hero.y;
				switch (e.getKeyCode()) {//��ȡ��������ֵ�����ж�����ƶ�
				case 37:
					hero.move(x-speed, y);
					break;
				case 38:
					hero.move(x, y-speed);
					break;
				case 39:
					hero.move(x+speed, y);
					break;
				case 40:
					hero.move(x, y+speed);
					break;
				default:
					break;
				}
				
			}
		});
	}
	
	public static void main(String[] args) {

		
		ShootGame game = new ShootGame(); //����������
		game.window(game);
		game.action();//����action����
		
	}

}
