package Demo10;

import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.imageio.ImageIO;

/**
 * 
// * @author 冰哥
 * 版本0.1 创建窗口 版本0.2 添加窗口可关闭窗口大小不可变 版本0.3 画一个圆代表坦克 版本0.4 加入坦克可移动 版本0.41
 * 加入双缓存消除闪烁现象 版本0.5 更改长宽为变量 版本0.6 加入键盘控制坦克移动 版本0.9 修复方向键按下后不复位bug 版本1.0 加入子弹类
 * 修复子弹从中间位置打出来的bug
 * 版本1.2加入炮筒
 * 版本1.3 修正炮弹数量bug
 * 版本1.4 修正ArrayList存储的炮弹数量不减少的问题  修正坦克开出界的bug
 * 版本1.5 加入敌方坦克，设置坦克敌我并且改变颜色。
 * 版本1.6 加入子弹打中坦克坦克和子弹消失的效果
 * 版本1.7 加入爆炸类 爆炸效果
 * 板门1.8 加入坦克被打会消失的情况
 * 版本1.9 敌人坦克可以移动和打我们
 * 版本2.0 加入墙
 * 2.1  修正相撞bug 2.2 加入大招  2.3 加入生命值 2.4加入血块条 2.5 计入吃的
 */
public class TankClient extends Frame {

	// 常量名一般为大写
	public static final int GAME_WIDTH = 1000;// 设置游戏框架的长宽为常量
	public static final int GAME_HEIGH = 660;

	// //设置坦克圆圈坐标
	// int x=50;
	// int y=50;//版本7 取消 改为直接new Tank

	Tank MyTank = new Tank(250, 350, true,Direction.STOP,this);//我方坦克
	
	//定义两堵墙
	Wall w1=new Wall(150,200,20,150,this);
	Wall w2=new Wall(300,300,600,50,this);
	
//	Wall w1=new Wall(0,0,0,0,this);
//	Wall w2=new Wall(0,0,0,0,this);
//	
	//Missile m = null;// 造子弹对象
	List <Missile> missiles=new ArrayList<>();//添加容器装子弹
	List<Explode> explodes= new ArrayList<>();
	List<Tank> Tanks=new ArrayList<>();//装坦克
	Image offScreenImage = null;// 定义背后虚拟图片
	Blood b=new Blood();

	// 画个圆代表坦克
	@Override
	public void paint(Graphics g ) {// 注意区分paint和print

//		public void run() {
//			//设置背景图片
//				
//			 BufferedImage bg = null;
//
//				try {
//					bg = ImageIO.read(new File("E:\\Java_Workspace_2018E\\TankWar2.9\\src\\images\\足球背景8.jpg"));
//					}catch(Exception e) {
//						e.printStackTrace();
//					}
//				g.drawImage(bg, 0, 0, null);
//		
			
		g.drawString("子弹数目"+missiles.size(), 10, 50);
		g.drawString("击毁敌坦克数"+explodes.size(), 10, 65);
		g.drawString("坦克数目"+Tanks.size(), 10, 80);
		g.drawString("生命值"+MyTank.getLife(), 10, 95);
		if (Tanks.size()<=0) {
			for(int i=0;i<7;i++) {
				Tanks.add (new Tank( 750,77*(i+1),false,Direction.D,this));
			}
		}
		MyTank.draw(g);// 调用Tank的画坦克的方法
		MyTank.eat(b);
		w1.draw(g);
		w2.draw(g);//画墙
		b.draw(g);
	
		//子弹
		for(int i=0;i<missiles.size();i++) {
			Missile m=missiles.get(i);
			m.hitTanks(Tanks);
			m.hitTank(MyTank);//敌方坦克可以打我
			
			m.hitWall(w1);
			m.hitWall(w2);
			m.draw(g);
			}
			//爆炸
			for(int j=0;j<explodes.size();j++) {
				Explode e=explodes.get(j);
				e.draw(g);
			}
			
		for(int i=0;i<Tanks.size();i++) {
			Tank t=Tanks.get(i);
			t.collidesWithWall(w1);
			t.collidesWithWall(w2);
			t.collidesWithTanks(Tanks);
			t.draw(g);
			
		}
		
	}

	// 把画滑到背面图片上
	@Override
	public void update(Graphics g) {
		if (offScreenImage == null) {
			offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGH);
		}
		Graphics goffScreen = offScreenImage.getGraphics();// 得到画笔
		Color c = goffScreen.getColor();
		goffScreen.setColor(Color.green);
		goffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGH);// 重画背景
		goffScreen.setColor(c);
		paint(goffScreen);
		g.drawImage(offScreenImage, 0, 0, null);
	}

	public static void main(String[] args) {
		TankClient tc = new TankClient();
		tc.lauchFrame();

	}

	public void lauchFrame() {
		
		Properties propes=new Properties();
		try {
			propes.load(this.getClass().getClassLoader().getResourceAsStream("config/propeities"));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		int  initTankCount=Integer.parseInt(propes.getProperty("initTankCount"));
		//添加10辆坦克
		for(int i=0;i< initTankCount;i++) {
			Tanks.add (new Tank( 10+65*(i+5),70,false,Direction.D,this));
		}
		
		this.setLocation(100, 50);
		this.setSize(GAME_WIDTH, GAME_HEIGH);
		this.setTitle("坦克大战");
		this.setResizable(false);// 设置大小不可变
		this.setBackground(Color.green);// 设置背景为绿色
		
		
		
		// 加入监听器对象
		this.addKeyListener(new KeyMonitor());
		this.setVisible(true);

		new Thread(new PaintThread()).start();
		;// 启动重画坦克线程

		// 设置关闭窗口
		this.addWindowListener(new WindowAdapter() {

			@Override
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}

		});
		
		/* @author 棉花不是糖   
		 * 写在lauchFrame()方法中
		 * 添加背景音乐
		 * 为保证音乐不是响一下就停，所以单独起一个线程播放音乐
		 */
		 new Thread(new Runnable() { 
			 @Override
			public void run() {
				 new Music2().playMusic();	
			}
		 }).start();                                                              

	}

	// 起一个线程间隔一定时间重画一次
	private class PaintThread implements Runnable {// 注意内部类不要写到人家方法的括号里去
		@Override
		public void run() {
			while (true) {
				repaint();
				try {
					Thread.sleep(50);// 每隔50秒刷新一次
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}

		}

	}

	// 添加键盘监听类,键盘可以控制坦克上下左右移动
	private class KeyMonitor extends KeyAdapter {

		// 添加键盘复位后执行的操作
		@Override
		public void keyReleased(KeyEvent e) {
			MyTank.KeyReleased(e);
		}

		@Override
		public void keyPressed(KeyEvent e) {
			MyTank.KeyPressed(e);// 调用坦克类的方法
		}
	}

	
}
