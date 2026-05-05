package fr.cpe.service;

public class GameState {

    private GameState(){

    }

    private static GameState instance;

    public static GameState getInstance(){
        if(instance == null){
            instance = new GameState();
        }
        return instance;
    }

    int nbTourConstruite;

    public int getNbTourConstruite() {
        return nbTourConstruite;
    }

    public void incrementerNbTourConstruite(){
        nbTourConstruite++;
    }
}
