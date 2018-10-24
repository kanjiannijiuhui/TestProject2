   package Demo10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * 版本0.7 加入坦克类 版本0.8 加入方向控制 实现逻辑按下键之后 方向会改变，方向改变之后重画时根据方向定位下一个位置
 */
public class Tank {

	// 设置向X方向及Y方向的速度分别是5
	public static final int XSPEED = 5;
	public static final int YSPEED = 5;

	
	TankClient tc;// 加入成员的引用
	
	//坦克是我方还是敌方
	private boolean good;
	private int  life=100;
	private BloodBar bb=new BloodBar();

	public int getLife() {
		return life;
	}

	public void setLife(int life) {
		this.life = life;
	}

	public boolean isGood() {
		return good;
	}

	private int x, y;// 坦克的位置
	private int oldX,oldY;
	
	private boolean live =true;//表示坦克生死
	
	
private static Toolkit tk=Toolkit.getDefaultToolkit();
	
	//爆炸的圆的直径
	private static Image[] tankImages=null; 
	private   static Map<String ,Image> imgs =new HashMap<>();
	
	
	//敌人是1-7 我方是8-15
	static { tankImages =new Image[]{
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankL.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankLU.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankU.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankRU.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankR.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankRD.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankD.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/TankLD.gif")),
			
			
			tk.getImage(Explode.class.getClassLoader().getResource("images/左.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/左上.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/shang.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/右上.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/右.png")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/右下.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/下.jpg")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/左下.jpg")),
			
			
			
	};
	imgs.put("左", tankImages[0]);
	imgs.put("左上", tankImages[1]);
	imgs.put("上", tankImages[2]);
	imgs.put("右上", tankImages[3]);
	imgs.put("右", tankImages[4]);
	imgs.put("右下", tankImages[5]);
	imgs.put("下", tankImages[6]);
	imgs.put("左下", tankImages[7]);
	
	imgs.put("我左", tankImages[8]);
	imgs.put("我左上", tankImages[9]);
	imgs.put("我上", tankImages[10]);
	imgs.put("我右上", tankImages[11]);
	imgs.put("我右", tankImages[12]);
	imgs.put("我右下", tankImages[13]);
	imgs.put("我下", tankImages[14]);
	imgs.put("我左下", tankImages[15]);
	
	
	}

	
	// 设置坦克的宽和高为常量
		public static final int WIDTH = 65;
		public static final int HEIGH = 60;
		
		
	public boolean isLive() {
		return live;
	}

	public void setLive(boolean live) {
		this.live = live;
	}
	
	//随机数产生器，随机产生得人坦克方向
	private  static Random r =new Random();

	private boolean bL = false, bU = false, bR = false, bD = false; // 表示向下左右有没有按下，如果按下变为true



	private Direction dir = Direction.STOP;// 坦克方向默认为停止不动

	private Direction ptDir=Direction.D;//炮筒方向
	
	private int step=r.nextInt(12)+3;
	
	public Tank(int x, int y,boolean good) {

		this.x = x;
		this.y = y;
		this.oldX =x;
		this.oldY=y;
		this.good=good;
	}

	public Tank(int x, int y,boolean good,Direction dir, TankClient tc) {

		this(x, y,good);
		this.dir=dir;
		this.tc = tc;
	}

	// 画个圆代表坦克
	public void draw(Graphics g) {
		if (!live)	
		{	
			if(!good){
				tc.Tanks.remove(this);
			}
			return;
			}
		Color c = g.getColor();
		//根据坦克是否是敌方设置前景色
		if(good) {g.setColor(Color.RED);}//我方为红色
		else {g.setColor(Color.blue);}//敌方为蓝色
	//	g.setColor(Color.RED);
	//	g.fillOval(x, y, WIDTH, HEIGH);// xy是方块左上角坐标
		g.setColor(c);
		move();
		if(good) {
		bb.draw(g);
		}
		
		if(!good) {
		//画出炮筒
		switch (ptDir) {
		case L:
			g.drawImage(imgs.get("左"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("左上"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("上"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("右上"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("右"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("右下"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("下"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("左下"), x, y, null);
			break;
		}		
		
	}else {
		
		switch (ptDir) {
		case L:
			g.drawImage(imgs.get("我左"), x, y, null);
			break;
		case LU:
			g.drawImage(imgs.get("我左上"), x, y, null);
			break;
		case U:
			g.drawImage(imgs.get("我上"), x, y, null);
			break;
		case RU:
			g.drawImage(imgs.get("我右上"), x, y, null);
			break;
		case R:
			g.drawImage(imgs.get("我右"), x, y, null);
			break;
		case RD:
			g.drawImage(imgs.get("我右下"), x, y, null);
			break;
		case D:
			g.drawImage(imgs.get("我下"), x, y, null);
			break;
		case LD:
			g.drawImage(imgs.get("我左下"), x, y, null);
			break;
		}		
		
	}
	
	
	
	
	}

	// 坦克移动
	void move() {
		this.oldX=x;
		this.oldY=y;
		
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
		case STOP:
			break;
		}
		
		//如果判断坦克没停止，就把炮筒方向设定和坦克一致
		if(this.dir!=Direction.STOP) {
		this.ptDir=dir;	
		}
		
		//添加不让坦克出界
		if(x<0){x=0;}
		if(y<30) {y=30;}
		if(x+Tank.WIDTH>TankClient.GAME_WIDTH) {x=TankClient.GAME_WIDTH-Tank.WIDTH;}
		if(y+Tank.HEIGH>TankClient.GAME_HEIGH) {y=TankClient.GAME_HEIGH-Tank.HEIGH;}
		
		//添加敌方坦克运动方向
		if(!good) {
			Direction[] dirs=Direction.values();
			if(step==0) {
				step=r.nextInt(12)+3;
				int rn =r.nextInt(dirs.length);
				dir=dirs[rn];		
			}
		
			
			step--;
			
			if(r.nextInt(40)>37) {
			this.fire();
			}
		}
	
	}
	
	private void stay() {
		x=oldX;
		y=oldY;
	}

	// 添加键盘监听类,键盘可以控制坦克上下左右移动
	// 逻辑按下键之后 方向会改变，方向改变之后重画时根据方向定位下一个位置
	public void KeyPressed(KeyEvent e) {
		int key = e.getKeyCode();// 获得虚拟键

		switch (key) {
		case KeyEvent.VK_F2:
			if(!this.live) {
				this.live=true;
				this.life=100;
			}
			break;
		case KeyEvent.VK_LEFT:// 向左
			bL = true;
			break;
		case KeyEvent.VK_UP:// 向上
			bU = true;
			break;
		case KeyEvent.VK_RIGHT:// 向右
			bR = true;
			break;
		case KeyEvent.VK_DOWN:// 向下
			bD = true;
			break;
		}
		LocationDirection();// 根据按键通过LocationDirection方法来判断及决定坦克的方向
	}

	// 控制8个方向通过逻辑
	void LocationDirection() {
		if (bL && !bU && !bR && !bD) {
			dir = Direction.L;
		} // 左
		else if (bL && bU && !bR && !bD) {
			dir = Direction.LU;
		} // 左上
		else if (!bL && bU && !bR && !bD) {
			dir = Direction.U;
		} // 上
		else if (!bL && bU && bR && !bD) {
			dir = Direction.RU;
		} // 右上
		else if (!bL && !bU && bR && !bD) {
			dir = Direction.R;
		} // 右
		else if (!bL && !bU && bR && bD) {
			dir = Direction.RD;
		} // 右下
		else if (!bL && !bU && !bR && bD) {
			dir = Direction.D;
		} // 下
		else if (bL && !bU && !bR && bD) {
			dir = Direction.LD;
		} // 左下
		else if (!bL && !bU && !bR && !bD) {
			dir = Direction.STOP;
		} // 停止
	}

	// 添加键盘俺家抬起后复位的动作
	public void KeyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		int key = e.getKeyCode();// 获得虚拟键

		switch (key) {
		case KeyEvent.VK_CONTROL://每抬起一次键打一发炮弹
			fire();
//			tc.missiles.add(fire());// 把产生的子弹对象传到ArrayList中去
				break;
	
		case KeyEvent.VK_LEFT:// 向左键抬起后 变成false
			bL = false;
			break;
		case KeyEvent.VK_UP:// 向上
			bU = false;
			break;
		case KeyEvent.VK_RIGHT:// 向右
			bR = false;
			break;
		case KeyEvent.VK_DOWN:// 向下
			bD = false;
			break;
		case KeyEvent.VK_A:
			superFire();
			break;
		}
		LocationDirection();// 根据按键通过LocationDirection方法来判断及决定坦克的方向
	}

	// 添加开火的方法
	public Missile fire() {
		if(!live) {
			return null;
		}
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;// 计算出坦克子弹应该出去的位置
		int y = this.y + Tank.HEIGH / 2 - Missile.HEIGH / 2;
		Missile m = new Missile(x, y,good, ptDir,this.tc);// 造一颗子弹 位置及方向与炮筒方向相同
		tc.missiles.add(m);// 把产生的子弹对象传到ArrayList中去
		return m;
	}
	
	public Missile fire(Direction dir) {
		if(!live) {
			return null;
		}
		int x = this.x + Tank.WIDTH / 2 - Missile.WIDTH / 2;// 计算出坦克子弹应该出去的位置
		int y = this.y + Tank.HEIGH / 2 - Missile.HEIGH / 2;
		Missile m = new Missile(x, y,good, dir,this.tc);// 造一颗子弹 位置及方向与炮筒方向相同
		tc.missiles.add(m);// 把产生的子弹对象传到ArrayList中去
		return m;
	}
	
	//表示坦克的区域
	public Rectangle getRect() {
		return new Rectangle(x,y,WIDTH,HEIGH);
		
	} 

	public boolean collidesWithWall(Wall w) {
		if(this.live&&this.getRect().intersects(w.getRect())) {
			this.stay();
			return true;
		}
		return false;
		
	}
	
	public boolean collidesWithTanks(List <Tank> tanks) {
		for (int i=0;i<tanks.size();i++) {
			Tank t=tanks.get(i);
			if(this!=t) {
				if(this.live&&t.isLive()&&this.getRect().intersects(t.getRect())) {
					this.stay();
					t.stay();
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void superFire() {
		Direction[] dirs=Direction.values();
		for(int i=0;i<8;i++) {
		fire(dirs[i]);
		}
	}

	//定义小血条
	private class BloodBar{
		public void draw (Graphics g) {
			Color c=g.getColor();
			g.setColor(Color.RED);
			g.drawRect(x, y-10, WIDTH, 10);
			int w=WIDTH*life/100;
			g.fillRect(x, y-10, w, 10);
			g.setColor(c);
		}
		
	}

	/**
	 *  //吃了血块生命值加满
	 * @param b
	 * @return 
	 */
	
	public boolean eat(Blood b) {
		if(this.live&&b.isLive()&&this.getRect().intersects(b.getRect())) {
			this.life=100;
			b.setLive(false);
			return true;
		}
		return false;
		
	}
	
}
