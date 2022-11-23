package app;

import java.io.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.*;
 
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

    public void saveMenager() throws IOException {
        try (
                FileOutputStream fileOutputStream = new FileOutputStream("menager.sr");
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)
        ) {
            objectOutputStream.writeObject(this);
        }
    }

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

    public void payRent(Player player, Contract contract) throws MoneyExeption{
        player.subMoney(contract.getRent());
        contract.getOwner().addMoney(contract.getRent());
    }

    public void buyContract(Player player, Contract contract) throws MoneyExeption{
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

            player.boxStart();
        }

        this.players.add(player);
    }

    public int getPlayersSize() {
        return players.size();
    }
}