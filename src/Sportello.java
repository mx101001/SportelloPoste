
/**
 * Classe che implementa il thread Sportello,
 * che serve i clienti delle
 * poste che arrivano via via, ovvero è il thread
 * consumatore delle risorse condivise
 * listaNumeri, ultimoArrivo, ultimoServito
 * @author frida
 * @version 1.0
 */
public class Sportello implements Runnable {
    /**
     * risorse condivise fra i due thread
     */
    private ListaClienti listaClienti;
    private final int minTempoServizio = 1000;
    private final int maxTempoServizio = 3000;
    private String nome;
    /**
     * constructor
     * @param listaClienti
     */
    public Sportello(ListaClienti listaClienti, String nome) {
        this.nome = nome;
        this.listaClienti = listaClienti;
    }

    /**
     * TODO: cosa fa?
     * @see Runnable
     */
   public void run() {
        try {
            while (!Thread.interrupted()) {
                Integer clienteServito = listaClienti.rimuoviCliente();
                if (clienteServito == null) {
                    // nessun altro cliente e lista chiusa
                    break;
                }
                //tempo di servizio variabile nel range [1,3] secondi
                int tempoServizio = (int) (Math.random() * (maxTempoServizio
                        - minTempoServizio + 1) + minTempoServizio);
                Thread.sleep(tempoServizio);
                System.out.println("Servito Cliente Numero \t " + clienteServito+
                        " dallo sportello" + nome);
            }
        } catch (InterruptedException e) {
            System.out.println("Thread interrotto durante lo sleep");
            Thread.currentThread().interrupt();
        } finally {
            System.out.println("Sportello Chiuso");
        }
    }
}