package Demo10;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
/**
 *  @author 棉花不是糖
 *  TankClient调用playMusic进行播放音乐
 *  main方法是用来测试这个类写完是否可以正常播放背景音乐
 */
	 public class Music2 extends JFrame{ 
		 File f;
		 URI  uri;
		 URL  url; 
		 
	 Music2(){  
		
		try {      
	       f = new File("E:\\Java_Workspace_2018E\\TankWar2.8\\src\\images\\Time.wav"); 
	       uri = f.toURI();
	       url = uri.toURL();  //解析地址
	       AudioClip aau; 
	       aau = Applet.newAudioClip(url);
	       aau.loop();  //循环播放
		   }catch (Exception e) 
	       { e.printStackTrace();} 
	 }
 
	 public static void playMusic() {  //TankClient调用playMusic进行播放音乐
		 new Music2();
	 } 
 
// 	public static void main(String args[]) { 
// 		 new Music2();    //main方法是用来测试这个类写完是否可以正常播放背景音乐
//	} 	 	 	 

}
	

