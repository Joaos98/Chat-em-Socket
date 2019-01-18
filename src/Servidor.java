
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepej
 */
public class Servidor implements Runnable{
    Socket cliente;
    NewJFrame tela;
    Servidor(ServerSocket cliente,NewJFrame tela) throws IOException{
        this.tela = tela;
        this.cliente=cliente.accept();
        tela.atualizarTexto("O usuário "+this.cliente.getInetAddress().getHostAddress()+" se conectou.");
        recebe();
    }
    private void recebe() throws IOException{
        Scanner s = new Scanner(cliente.getInputStream());
        while (s.hasNextLine()){
            tela.atualizarTexto(this.cliente.getInetAddress().getHostAddress()+" : "+s.nextLine());
        }
    }

    @Override
    public void run() {

    }
    
}
