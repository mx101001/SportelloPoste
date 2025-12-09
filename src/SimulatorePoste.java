
import java.time.Duration;
import java.time.LocalTime;

/**
 * Classe con il main, che avvia l'app
 * che rappresenta il flusso dei clienti di un ufficio postale
 * messi in attesa da un totem elettronico che assegna
 * un numero progressivo e stampa il ticket
 * Clienti gestiti da un solo sportello
 * @author frida
 * @version 1.0
 */
public class SimulatorePoste {
    public static void main(String[] args) {
        ListaClienti listaClienti = new ListaClienti();
        Thread arriviThread = new Thread(new GestoreArrivi(listaClienti, 1));
        Thread arriviThread2 = new Thread(new GestoreArrivi(listaClienti, 2));
        Thread sportelloThread = new Thread(new Sportello(listaClienti, " paolo"));
        Thread sportelloThread2 = new Thread(new Sportello(listaClienti , " marzia"));

        LocalTime time = LocalTime.now();

        arriviThread.start();
        arriviThread2.start();
        sportelloThread.start();
        sportelloThread2.start();

        try {
            arriviThread.join();
            arriviThread2.join();
            sportelloThread.join();
            sportelloThread2.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        LocalTime end = LocalTime.now();
        Duration duration = Duration.between(time, end);
        long secondsBetween = duration.getSeconds();
        long hours = secondsBetween / 3600;
        long minutes = (secondsBetween % 3600) / 60;

        System.out.println("Poste aperte per: " + hours + " ore e " + minutes + " minuti.");
    }
}
