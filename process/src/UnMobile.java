import java.awt.*;
import java.util.Random;

import javax.swing.*;

class UnMobile extends JPanel implements Runnable
{
    int saLargeur, saHauteur, sonDebDessin;
    final int sonPas = 1, sonTemps=5, sonCote=10;
    static semaphoreBinaire sem = new semaphoreBinaire(12);
    Color color = Color.BLACK;
    
    UnMobile(int telleLargeur, int telleHauteur)
    {
		super();
		saLargeur = telleLargeur;
		saHauteur = telleHauteur;
		setSize(telleLargeur, telleHauteur);
    }

    public Color genColor(){
    	Random rand = new Random();

    	// Java 'Color' class takes 3 floats, from 0 to 1.
    	float r = rand.nextFloat();
    	float g = rand.nextFloat();
    	float b = rand.nextFloat();

    	return new Color(r, g, b);
    }
    
    public void run(){
    	
	    while (true){
			for (sonDebDessin=0; sonDebDessin < saLargeur/3 - sonPas; sonDebDessin+= sonPas){
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
			}
			sem.syncWait();
			for (sonDebDessin=saLargeur/3 - sonPas; sonDebDessin < 2*saLargeur/3 - sonPas; sonDebDessin+= sonPas){ // CRITIQUE
				color = genColor();
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
		    }
			sem.syncSignal();
			for (sonDebDessin=2*saLargeur/3 - sonPas; sonDebDessin < saLargeur - sonPas; sonDebDessin+= sonPas){
				color = Color.BLACK;
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
		    }
			for (sonDebDessin=saLargeur; sonDebDessin > 2*saLargeur/3 + sonPas; sonDebDessin-= sonPas){
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
		    }
			sem.syncWait();
			for (sonDebDessin=2*saLargeur/3; sonDebDessin > saLargeur/3; sonDebDessin-= sonPas){ // CRITIQUE
				color = genColor();
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
		    }
			sem.syncSignal();
			for (sonDebDessin=saLargeur/3; sonDebDessin > sonPas; sonDebDessin-= sonPas){
				color = Color.BLACK;
				repaint();
				try{Thread.sleep(sonTemps);}
				catch (InterruptedException telleExcp)
				    {telleExcp.printStackTrace();}
		    }
	    }
    }

    public void paintComponent(Graphics telCG)
    {
	super.paintComponent(telCG);
	telCG.setColor(color);
	telCG.fillRect(sonDebDessin, saHauteur/2, sonCote, sonCote);
    }
}