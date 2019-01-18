
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pepej
 */
public class Trabalho2 implements Runnable{
    int porta;
    Servidor servidor;
    NewJFrame tela;
    Socket cliente;
    PrintStream saida;
    Trabalho2(int porta, NewJFrame tela) throws IOException{
        this.tela = tela;
        this.porta = porta;
    }

    @Override
    public void run() {
        try {
            iniciar();
        } catch (IOException ex) {
            Logger.getLogger(Trabalho2.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    public void conectar(String ip, int porta) throws IOException{
        Socket cliente = new Socket(ip, porta);
        this.cliente = cliente;
        tela.atualizarTexto("Você se conectou ao servidor!");
        saida = new PrintStream(cliente.getOutputStream());
    }
    
    public void iniciar() throws IOException{
        
        ServerSocket server = new ServerSocket(porta);
        tela.atualizarTexto("Porta "+porta+" aberta");
        Servidor servidor = new Servidor(server,tela);
        this.servidor = servidor;
        Thread t = new Thread(this.servidor);
        t.start();
    }
    public String enviar(String texto){
        saida.println(texto);
        tela.atualizarTexto(this.cliente.getLocalAddress().getHostAddress()+" : "+texto);
        return texto;
    }
}
