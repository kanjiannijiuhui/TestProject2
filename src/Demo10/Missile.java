package Demo10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//import Demo10.Tank.Direction;

public class Missile {

	// �����ӵ����ٶ�
	public static final int XSPEED = 10;
	public static final int YSPEED = 10;

	// �����ӵ��Ŀ�͸�Ϊ����
	public static final int WIDTH = 10;
	public static final int HEIGH = 10;
	private static final String L = null;
	private static final int Dir = 0;
	private static final int LU = 0;
	private static final int U = 0;

	// ����ӵ�λ��
	int x, y;
	// �ӵ��ķ���
	Direction dir;
	
	//�ӵ��Ƿ����Ѿ���
	private boolean good;
	
	//��ʾ�˵��Ƿ������
	private boolean live=true;
	private TankClient tc;
	
	private static Toolkit tk=Toolkit.getDefaultToolkit();
	
	private static Image[] missileImages=null; 
	private   static Map<String ,Image> imgs =new HashMap<>();
	
	static { missileImages =new Image[]{
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/54654.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/59.png")),
			
	};
	imgs.put("��", missileImages[0]);
	imgs.put("����", missileImages[1]);
	imgs.put("��", missileImages[2]);
	imgs.put("����", missileImages[3]);
	imgs.put("��", missileImages[4]);
	imgs.put("����", missileImages[5]);
	imgs.put("��", missileImages[6]);
	imgs.put("����", missileImages[7]);
	imgs.put("����", missileImages[8]);
	}

	//���ô˷������Ե�֪liveֵ֪���ӵ�������
	public boolean isLive() {
		return live;
	}

	public Missile(int x, int y, Direction dir) {
		super();
		this.x = x;
		this.y = y;
		this.dir = dir;
	}
	
	public Missile(int x,int y, boolean good,Direction dir,TankClient tc ) {
		this (x,y,dir);
		this.tc=tc;
		this.good =good;
	} 

	// ���ӵ�
	public void draw(Graphics g) {
		if(!live) {
			tc.missiles.remove(this);
			tc.explodes.remove(this);
			return;
		}
		if(!good) {
			switch (dir) {
			case L:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case LU:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case U:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case RU:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case R:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case RD:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case D:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			case LD:
				g.drawImage(imgs.get("����"), x, y, null);
				break;
			}	
		}else {
		switch (dir) {
		case L:
			g.drawImage(imgs.get("��"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("����"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("��"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("����"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("��"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("����"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("��"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("����"), x, y, null);
			break;
		}	
		}
//		
//		Color c = g.getColor();
//		if(good) {g.setColor(Color.red);
//		}else {
//		g.setColor(Color.BLACK);
//		}
//		g.fillOval(x, y, WIDTH, HEIGH);
//		g.setColor(c);
		move();
	}

	// �ӵ��ƶ�
	private void move() {

		
		switch (dir) {
		case L:
			x -= XSPEED;
			break;
		case LU:
			x -= XSPEED;
			y -= YSPEED;
			break;
		case U:
			y -= YSPEED;
			break;
		case RU:
			x += XSPEED;
			y -= YSPEED;
			break;
		case R:
			x += XSPEED;
			break;
		case RD:
			x += XSPEED;
			y += YSPEED;
			break;
		case D:
			y += YSPEED;
			break;
		case LD:
			x -= XSPEED;
			y += YSPEED;
			break;
		}
		if(x<0||y<0||x>TankClient.GAME_WIDTH||y>TankClient.GAME_HEIGH) {
			live=false;
		
		}
		
	}

	//��ʾ�ӵ�������
	public Rectangle getRect() {
		return new Rectangle(x,y,WIDTH,HEIGH);
		
	} 
	
	//��̹�ˣ�����ӵ��������̹�˵�������һ���ʾ����̹����
	public boolean hitTank(Tank t) {

		if(this.live &&this.getRect().intersects(t.getRect())&&t.isLive()&&this.good !=t.isGood())
		{
			if (t.isGood()) {
				t.setLife(t.getLife()-20);
				if(t.getLife()<=0) {
					t.setLive(false);
				} 
			}else {
				t.setLive(false);
			}
			
			this.live=false;
//			t.setLive(false);
			
			Explode e=new Explode(x,y,tc);//��ٱ�ը
			tc.explodes.add(e);
			return true;
		}
		return false;
	}
	
	//��̹�� �������˷��ӵ���ÿһ��̹���ж��Ƿ����
	public boolean hitTanks(List<Tank> tanks) {

		for(int i=0;i<tanks.size();i++) {
			if(hitTank(tanks.get(i))) {
				return true;
			}
		}
		return false;
	}

	public boolean hitWall(Wall w) {
	
		if(this.live&&this.getRect().intersects(w.getRect())) {
			this.live=false;
			return true;
		}
		return false;
		
	}
	
}
