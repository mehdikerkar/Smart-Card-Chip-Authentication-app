package interfaces;

import java.util.Date;

public class ConsoleProgressBar {

	public ConsoleProgressBar(String etat, String info,String done,int sleep){
		char[] animationChars = new char[] {'#'};//{'|', '/', '-', '\\'};
        System.out.print("Processing "+etat+" "+info+" : [ ");
        for (int i = 0; i <= 50; i++) {//50
            System.out.print(animationChars[0]);

            try {
                Thread.sleep(sleep);//100
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("] 100%");
        System.out.println("Processing "+etat+" : "+done+"!");
	}
}