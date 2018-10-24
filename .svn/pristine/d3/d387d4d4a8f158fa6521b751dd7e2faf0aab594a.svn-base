package Demo10;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

public class Explode {

	//爆炸的位置
	int x,y;
	//爆炸是否存活
	private boolean live =true;
	
	private static Toolkit tk=Toolkit.getDefaultToolkit();
	
	//爆炸的圆的直径
	private static Image[] images= {
			tk.getImage(Explode.class.getClassLoader().getResource("images/0.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/1.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/2.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/3.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/4.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/5.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/6.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/7.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/8.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/9.gif")),
			tk.getImage(Explode.class.getClassLoader().getResource("images/10.gif")),
			
	};
	int step =0;//现在画到第几步了
	
	private boolean  init= false;
	
	private TankClient tc;
	
	public  Explode (int x,int y,TankClient tc) {
		this.x=x;
		this.y=y;
		this.tc=tc;
	
	}
	
	//画爆炸的方法
	public void draw(Graphics g) {
		
		if(!init) {
			for (int j = 0; j < images.length; j++) {
				g.drawImage(images[j], -100, -100, null);
			}
			init=true;	
		}
		
		if(!live) {
			return;//如果爆炸不存在就不画了
		}
		
		if(step==images.length) {
			live=false;
			step=0;
			
			
			return;
		
		}
		g.drawImage(images [step], x, y, null);
		step++;
	}
	
}
