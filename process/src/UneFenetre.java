import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

class UneFenetre extends JFrame implements ActionListener
{
    UnMobile sonMobile;
    private final int LARG=900, HAUT=1000, NBRLIG=40, NBRCOL = 2, LARGE_MOBILE=LARG/NBRCOL-39, HAUT_MOBILE=HAUT/NBRLIG/2;
    private JButton[] tabBouton;
    private Thread[] tabT;
    private UnMobile[] tabMobile;
    private boolean[] arret;
    
    public UneFenetre()
    {
    	
    super("Thread");
    setSize( LARG, HAUT );
    setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
    
    tabMobile = new UnMobile[NBRLIG];
    tabBouton = new JButton[NBRLIG];
    tabT = new Thread[NBRLIG];
    arret = new boolean[NBRLIG];
    
    Container leConteneur = getContentPane();
    leConteneur.setLayout (new GridLayout(NBRLIG, NBRCOL));
    
    for(int i=0; i<NBRLIG; i++){
    	tabMobile[i] = new UnMobile(LARGE_MOBILE, HAUT_MOBILE);
    	tabBouton[i] = new JButton("Pause");
    	arret[i] = false;
    	tabBouton[i].addActionListener(this);
    	tabBouton[i].setName(Integer.toString(i));
    	leConteneur.add(tabBouton[i]);
    	leConteneur.add(tabMobile[i]);
    	tabT[i] = new Thread(tabMobile[i]);
    	tabT[i].start();
    }
    
    }

	public void actionPerformed(ActionEvent evt) {
		System.out.println("pause");
		int i = Integer.parseInt(((JButton) evt.getSource()).getName());
			if(arret[i] == false){
				tabT[i].suspend();
				arret[i] = true;
				tabBouton[i].setBackground(Color.RED);
				tabBouton[i].setText("Reprendre");
			}else{
				tabT[i].resume();
				arret[i] = false;
				tabBouton[i].setBackground(Color.GREEN);
				tabBouton[i].setText("Pause");
			}	
		}
	}
