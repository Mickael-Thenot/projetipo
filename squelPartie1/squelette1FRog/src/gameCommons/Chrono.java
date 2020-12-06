package gameCommons;

public class Chrono implements Runnable{
    // VARIABLES
    private final int PAUSE = 1000; // le temps de pause entre 2 affichages en 1000 ms, donc 1 seconde.
    private int chrono;
    private String str;

    // CONSTRUCTEUR
    public Chrono(){
        this.chrono = 0;
        this.str = "Chrono : 0";

        Thread chrono = new Thread(this);
        chrono.start();
    }

    // GETTERS
    public int getChrono(){ return chrono; }
    public String getStr(){return str;}


    @Override
    public void run() {
        while (true){ // BOUCLE INFINIE
            try{Thread.sleep(PAUSE);}
            catch (InterruptedException e) {}
            this.chrono++;
            this.str = "Chrono : " + this.chrono;
        }
    }
}
