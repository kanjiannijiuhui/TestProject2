package Demo10;
import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URI;
import java.net.URL;
import javax.swing.JFrame;
/**
 *  @author �޻�������
 *  TankClient����playMusic���в�������
 *  main�������������������д���Ƿ�����������ű�������
 */
	 public class Music2 extends JFrame{ 
		 File f;
		 URI  uri;
		 URL  url; 
		 
	 Music2(){  
		
		try {      
	       f = new File("E:\\Java_Workspace_2018E\\TankWar2.8\\src\\images\\Time.wav"); 
	       uri = f.toURI();
	       url = uri.toURL();  //������ַ
	       AudioClip aau; 
	       aau = Applet.newAudioClip(url);
	       aau.loop();  //ѭ������
		   }catch (Exception e) 
	       { e.printStackTrace();} 
	 }
 
	 public static void playMusic() {  //TankClient����playMusic���в�������
		 new Music2();
	 } 
 
// 	public static void main(String args[]) { 
// 		 new Music2();    //main�������������������д���Ƿ�����������ű�������
//	} 	 	 	 

}
	

