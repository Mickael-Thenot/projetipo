package gameCommons;

public class CompteARebours implements Runnable {

    // VARIABBLES
    private final int PAUSE = 1000; // le temps de pause entre 2 affichages en 1000 ms, donc 1 seconde.
    private int compteurTemps;
    private String str;

    // CONSTRUCTEUR
    public CompteARebours(){
        this.compteurTemps = 30;
        this.str = "Temps restant : 30";

        Thread compteARebours = new Thread(this);
        compteARebours.start();
    }

    // GETTERS
    public int getCompteurTemps(){ return compteurTemps; }
    public String getStr(){return str;}


    @Override
    public void run() {
        while (true){ // BOUCLE INFINIE
            try{Thread.sleep(PAUSE);}
            catch (InterruptedException e) {}
            this.compteurTemps--;
            this.str = "Temps restant : " + this.compteurTemps;
        }
    }
}
