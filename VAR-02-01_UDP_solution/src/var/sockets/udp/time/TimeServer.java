package var.sockets.udp.time;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.util.Date;

/**
 * Server for echo var.sockets.udp.time.TimeServer service. Empfängt Datagramm von Client, liest
 * Absenderinformationen daraus und sendet Zeitinformation zurück.
 * 
 * @author Sandro Leuchter
 *
 */
class TimeServer {
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4712;
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
  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket(PORT)) {
      DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
      DatagramPacket packetOut = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

      System.out.println("Server gestartet ...");

      while (true) {
        socket.receive(packetIn);
        System.out.println(
            "Received from: " + packetIn.getAddress().getHostAddress() + ":" + packetIn.getPort());
        String jetzt = (new Date()).toString();
        packetOut.setData(jetzt.getBytes());
        packetOut.setLength(jetzt.length());
        packetOut.setSocketAddress(packetIn.getSocketAddress());
        packetOut.setPort(packetIn.getPort());
        socket.send(packetOut);
      }
    } catch (final IOException e) {
      System.err.println(e);
    }
  }

}
