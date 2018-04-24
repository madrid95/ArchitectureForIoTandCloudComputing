package var.sockets.udp.messwerte;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * Server for var.sockets.udp.messwerte.MesswertServer service. Empfängt Messwerte von Clients und
 * gibt sie aus.
 * 
 * @author s.leuchter
 *
 */
class MesswertServer {
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4713;
  /**
   * maximum size of payload in datagram
   */
  private static final int BUFSIZE = 512;

  /**
   * main method: entrypoint to run
   * 
   * @param args
   *          ignored
   */
  public static void main(final String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT)) {
      DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

      System.out.println("Server gestartet ...");

      while (true) {
        socket.receive(packetIn);
        String jetzt = (new Date()).toString();
        System.out.println(packetIn.getAddress().getHostAddress() + ":" + packetIn.getPort() + " "
            + jetzt + " " + new String(packetIn.getData()));
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
