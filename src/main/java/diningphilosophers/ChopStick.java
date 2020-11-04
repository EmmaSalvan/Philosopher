package diningphilosophers;

public class ChopStick {
    // Le nombre total de baguettes
    private static int stickCount = 0;
    // Le numéro de chaque baguette
    private final int myNumber;
    // Est-ce que ma baguette est libre ?
    private boolean iAmFree = true;

    public ChopStick() {
        // Chaque baguette est numérotée 
        myNumber = ++stickCount;
    }

    synchronized void take() throws InterruptedException {
		// Attendre que la baguette soit libre
		while (!iAmFree) {
			wait(); // Peut lever InterruptedException
		}		// Dépiler
		assert iAmFree; // On est sur que la baguette est libre
		this.iAmFree=false;// On met la baguette comme étant prise 
		System.out.printf("Le philosophe prend la baguette "+myNumber +" ");
		notifyAll(); // Notifier que la baguette n'est plus libre
	}

    synchronized void release() throws InterruptedException {
		// Attendre que la baguette soit prise
		while (iAmFree) {
			wait(); // Peut lever InterruptedException
		}		// Dépiler
		assert !iAmFree; // On est sur que la baguette est prise
                this.iAmFree=true;// On met la baguette comme étant libre
		System.out.printf("Le philosophe lache la baguette "+myNumber +" ");
		notifyAll(); // Notifier que la baguette n'est plus prise 
	}
    
    @Override
    public String toString() {
        return "Stick#" + myNumber;
    }
}
