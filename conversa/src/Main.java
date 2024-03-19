import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class Main {
    public static void main(String[] args) throws Exception {
        final int PORTA = 12345;

        try {
            ServerSocket servidor = new ServerSocket(PORTA);
            System.out.println("Esperando a conexão do cliente");
            Socket cliente = servidor.accept();
            System.out.println("Cliente conectado.");

            BufferedReader entrada = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
            PrintWriter saida = new PrintWriter(cliente.getOutputStream(), true);
            BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

            FileWriter arquivoLog = new FileWriter("ServerLog.txt", true);
            BufferedWriter escritoLog = new BufferedWriter(arquivoLog);
            escritoLog.append("Log da conversa do servidor: ");
            escritoLog.flush();

            String mensagemCliente;
            while ((mensagemCliente = entrada.readLine()) != null) {
                System.out.println("Cliente: " + mensagemCliente);
                escritoLog.append(mensagemCliente + '\n');

                escritoLog.flush();
                System.out.println("Servidor: ");
                String mensagemServidor = teclado.readLine();
                escritoLog.append(mensagemServidor + '\n');
                escritoLog.flush();
            }
            servidor.close();
            cliente.close();
            escritoLog.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
