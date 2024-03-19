import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Cliente {
    private final String HOST;
    private final int PORTA;
    private Socket socket;

    private BufferedReader entrada;
    private PrintWriter saida;
    private BufferedReader teclado;

    private String mensagemCliente;
    private String mensagemServidor;

    public Cliente(String host, int porta) {
        this.HOST = host;
        this.PORTA = porta;
        try {
            this.socket = new Socket(this.HOST, this.PORTA);
            this.entrada = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
            this.saida = new PrintWriter(this.socket.getOutputStream(), true);
            this.teclado = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void ClienteServer() {
        try {
            while (true) {
                System.out.println("Cliente: ");
                this.mensagemCliente = teclado.readLine();
                saida.println("Cliente: " + this.mensagemCliente);

                this.mensagemServidor = entrada.readLine();
                System.out.println("Servidor: " + this.mensagemServidor);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Cliente clienteServidor = new Cliente("localhost", 12345);
        clienteServidor.ClienteServer();
    }
}