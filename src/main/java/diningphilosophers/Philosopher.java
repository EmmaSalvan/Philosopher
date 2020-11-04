package diningphilosophers;

import java.util.Random;


public class Philosopher
        extends Thread {

    private static int seed = 1;
    // Un générateur aléatoire pour tirer au sort les durées
    private final Random myRandom = new Random(System.currentTimeMillis() + seed++);
    private final static int DELAY = 1000;
    private final ChopStick myLeftStick;
    private final ChopStick myRightStick;
    private boolean running = true;

    public Philosopher(String name, ChopStick left, ChopStick right) {
        super(name);
        myLeftStick = left;
        myRightStick = right;
    }

    @Override
    public void run() {
        while (running){
            try {	
                // tant qu'il ne quitte pas la table
                think();
                // Prendre les 2 baguettes
                myLeftStick.take();
                myRightStick.take();
                //Il peut manger
                eat();
                //Il relache les baguettes
                myLeftStick.release();
                myRightStick.release();
                }
            catch (InterruptedException ex) {
		break; // Sort du while, termine le Thread;
            }
        }
    }
    // Permet d'interrompre le philosophe "proprement" :
    // Il doit relâcher ses baguettes avant de s'arrêter
    public void leaveTable() {
        running = false;
    }

    private void think() throws InterruptedException {
        int delay = myRandom.nextInt(500 + DELAY);
        System.out.println(this.getName() + " Starts Thinking for: " + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Thinking");
    }

    private void eat() throws InterruptedException {
        int delay = myRandom.nextInt(100 + DELAY);
        System.out.println(this.getName() + " Starts Eating for:" + delay + " ms");
        sleep(delay); // Le thread peut être interrompu ici
        System.out.println(this.getName() + " Stops Eating");
    }
}
