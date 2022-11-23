package app;

import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;

/**
 * Classe per la gestione dei contratti e dei giocatori
 * Questa classe salva il suo stato in menager.sr tenendo conto dei giocatori e contratti presenti
*/ 
public class Menager implements Serializable{
    private List<Player> players;
    private List<Contract> contracts;
    private int numContract;
    public static final long serialVersionUID = 1L;

    public Menager() {
        
    }

    public void constructor(){
        this.players = new ArrayList<>();
        this.contracts = new ArrayList<>();
    }

    /**
     * Metodo per  il caricamento della partita salvata precedentemente in menager.sr
     * lancia una IOExceptoion e gestisce FileNotFoundException e ClassNotFoundException 
     * in caso il file o la classe Menager non sia trovata
     * 
     * @return Menager se viene trovato, null altrimenti
     * @throws IOException se il file o la classe non viene trovata
     */
    public static Menager loadMenager() throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream("menager.sr");
                ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)
        ) {
            Object o = objectInputStream.readObject();
            return (Menager) o;
        } catch (FileNotFoundException e) {
            return null;
        } catch (ClassNotFoundException ignore) {
            return null;
        }
    }

    /**
     * Metodo per il salvataggio della partita in menager.sr
     * lancia una IOException nel caso di errore di scrittura su menager.sr
     * 
     * @throws IOException in caso di errore di scrittura
     */
    public void saveMenager() throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("menager.sr");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(this);
        }
    }

    /**
     * Metodo per cercare se esiste già una paritita da caricare
     * lancia una IOException e gestisce FileNotFoundException nel caso non trovi il file.sr
     * 
     * @return true se la partita viene trovata, false altrimenti
     * @throws IOException e gestisce FileNotFoundException nel caso non trovi il file.sr
     */
    public static boolean searchMenager() throws IOException {
        try (
                FileInputStream fileInputStream = new FileInputStream("menager.sr");
        ) {
            return true;      
        } catch (FileNotFoundException e) {
            return false;
        }
    }

    public void start() {
        switch (players.size()) {
            case 2:
                for (Player player : players) {
                    player.setMoney(8750);
                }
                numContract = 7;
                break;
            case 3:
                for (Player player : players) {
                    player.setMoney(7500);
                }
                numContract = 6;
                break;
            case 4:
                for (Player player : players) {
                    player.setMoney(6250);
                }
                numContract = 5;
                break;
            case 5:
                for (Player player : players) {
                    player.setMoney(5000);
                }
                numContract = 4;
                break;
            case 6:
                for (Player player : players) {
                    player.setMoney(3750);
                }
                numContract = 3;
                break;
        }

        this.inizializeContracts();
        this.assignContracts(numContract);
    }

    private void inizializeContracts() {
        Contract vicoloCorto = new Contract("Vicolo corto", 60, 2);
        contracts.add(vicoloCorto);
        Contract vicoloStretto = new Contract("Vicolo Stretto", 60, 4);
        contracts.add(vicoloStretto);
        Contract bastioniGranSasso = new Contract("Bastoni Gran Sasso", 100, 6);
        contracts.add(bastioniGranSasso);
        Contract vialeMonterosa = new Contract("Viale Monterosa", 100, 6);
        contracts.add(vialeMonterosa);
        Contract vialeVesuvio = new Contract("Viale Vesuvio",120, 8);
        contracts.add(vialeVesuvio);
        Contract viaAccademia = new Contract("Via Accademia", 140, 10);
        contracts.add(viaAccademia);
        Contract corsoAteneo = new Contract("Corso Ateneo",140,10);
        contracts.add(corsoAteneo);
        Contract piazzaUniversita = new Contract("Piazza Università", 160, 12);
        contracts.add(piazzaUniversita);
        Contract viaVerdi = new Contract("Via Verdi", 180, 14);
        contracts.add(viaVerdi);
        Contract corsoRaffaello = new Contract("Corso Raffaello",180,14);
        contracts.add(corsoRaffaello);
        Contract piazzaDante = new Contract("Piazza Dante", 200, 16);
        contracts.add(piazzaDante);
        Contract viaMarcoPolo = new Contract("Via Marco Polo", 220, 18);
        contracts.add(viaMarcoPolo);
        Contract corsoMagellano = new Contract("Corso Magellano", 220, 18);
        contracts.add(corsoMagellano);
        Contract largoColombo =  new Contract("Largo Colombo", 240, 20);
        contracts.add(largoColombo);
        Contract vialeCostantino = new Contract("Viale Costantino", 260, 22);
        contracts.add(vialeCostantino);
        Contract vialeTraiano = new Contract("Viale Traiano", 260, 22);
        contracts.add(vialeTraiano);
        Contract piazzaGiulioCesare = new Contract("Piazza Giulio Cesare", 280, 24);
        contracts.add(piazzaGiulioCesare);
        Contract viaRoma = new Contract("Via Roma", 300, 26);
        contracts.add(viaRoma);
        Contract corsoImpero = new Contract("Corso Impero", 300, 26);
        contracts.add(corsoImpero);
        Contract largoAugusto = new Contract("Largo Augusto", 320, 28);
        contracts.add(largoAugusto);
        Contract vialeDeiGiardini = new Contract("Viale dei Giardini", 350, 35);
        contracts.add(vialeDeiGiardini);
        Contract parcoDellaVittoria = new Contract("Parco della Vittoria", 400, 40);
        contracts.add(parcoDellaVittoria);
        Contract stazioneSud = new Contract("Stazione Sud", 200, 25);
        contracts.add(stazioneSud);
        Contract stazioneNord = new Contract("Stazione Nord", 200, 25);
        contracts.add(stazioneNord);
        Contract stazioneEst = new Contract("Stazione Est", 200, 25);
        contracts.add(stazioneEst);
        Contract stazioneOvest = new Contract("Stazione Ovest", 200, 25);
        contracts.add(stazioneOvest); 
    }
    
    /**
     * Metodo per l'assegnazione dei contratti casuali a tutti i giocatori
     * 
     * @param numContract numeri di contratti da assegnare ad ogni giocatore
     */
    private void assignContracts(int numContract) {
        Collections.shuffle(contracts);

        int c = 0;
        for (Player player : players) {
            for (int i = 0; i < numContract; i++) { 
                    player.addContract(contracts.get(c));
                    c++;
                }
            }
    }

    /**
     * Metodo per il pagamento di un affitto
     * lancia una MoneyException se player.getMoney() < contract.getRent()
     * 
     * @param player il giocatore che paga l'affitto
     * @param contract il contratto da cui si prende l'affitto
     * @throws MoneyException se player.getMoney() < contract.getRent()
     */
    public void payRent(Player player, Contract contract) throws MoneyException{
        player.subMoney(contract.getRent());
        contract.getOwner().addMoney(contract.getRent());
    }

    /**
     * Metodo per acquistare un contratto,
     * lancia una MoneyExeption se  player.getMoney() < contract.getPrice()
     * 
     * @param player il giocatore che acquista
     * @param contract il contratto da acquistare
     * @throws MoneyException se i soldi non sono sufficienti
     */
    public void buyContract(Player player, Contract contract) throws MoneyException{
        player.subMoney(contract.getPrice());
        contract.setOwner(player);
        player.addContract(contract);
    }

    public List<Player> getPlayers(){
        return players;
    }

    public List<Contract> getContracts(){
        return contracts;
    }

    /**
     * Metodo per l'aggiunta di un giocatore all'interno della lista dei giocatori
     * lancia una PlayerException se il nome è vuoto, duplicato o il colore è duplicato
     * 
     * @param player il giocatore da aggiungere
     * @throws PlayerException se il nome è vuoto o dupllicato, oppure se il colore è duplicato
     */
    public void addPlayer(Player player) throws PlayerException{
        for (Player p : players) {
            if (player.getName() == null) {
                throw new PlayerException("Il nome non può essere vuoto");
            }
            if (p.getName().equals(player.getName())) {
                throw new PlayerException("Nome duplicato");
            }
            if (p.getColor().equals(player.getColor())) {
                throw new PlayerException("Colore duplicato");
            }
        }

        this.players.add(player);
    }

    public int getPlayersSize() {
        return players.size();
    }
}