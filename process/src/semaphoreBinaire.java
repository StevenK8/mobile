
public final class semaphoreBinaire extends semaphore {
public semaphoreBinaire(int valeurInitiale){
	super(valeurInitiale);
	//System.out.print(valeurInitiale);
}
public final synchronized void syncSignal(){
	super.syncSignal();
	//System.out.print(valeur);

}
}

