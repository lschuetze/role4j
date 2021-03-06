package demo.filetransfer.server;

import net.role4j.trans.Transaction;

import java.net.Socket;

/**
 * Created by nguonly on 10/14/16.
 */
public class ServiceHandler extends Thread {
    private Communicator communicator;
    private Socket client;
    private boolean isConnected=true;

    public ServiceHandler(Socket client){
        this.client = client;
        this.communicator = new Communicator();

    }

    public void run(){
        AppState.compartment.activate();
        communicator.setSocket(client);

        if(AppState.isTranquil) {
            try (Transaction tx = new Transaction()) {
                simulateTransaction();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }else{ //Without a transaction
            simulateTransaction();
        }
    }

    private void simulateTransaction(){
        while (isConnected) {
            communicator.send("Data");
            sleep(1000);
        }
    }

    private void sleep(int mili){
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void disconnect(){
        isConnected=false;
        communicator.send("DISCONNECT");
    }
}
