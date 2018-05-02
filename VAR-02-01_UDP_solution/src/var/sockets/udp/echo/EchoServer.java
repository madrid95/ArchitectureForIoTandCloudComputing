package var.sockets.udp.echo;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * Server for var.sockets.udp.echo.EchoServer service. Empfängt ANfrage von Clients und sendet den
 * Inhalt jeweils an den Client zurück
 * 
 * @author Sandro Leuchter
 *
 */
class EchoServer {
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4711;
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
      DatagramPacket packetOut = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);

      System.out.println("Server gestartet ...");

      while (true) {
        socket.receive(packetIn);
        System.out.println(
            "Received: " + packetIn.getLength() + " bytes: " + new String(packetIn.getData(), 0, packetIn.getLength()));
        packetOut.setData(packetIn.getData());
        packetOut.setLength(packetIn.getLength());
        packetOut.setSocketAddress(packetIn.getSocketAddress());
        socket.send(packetOut);
      }
    } catch (IOException e) {
      System.err.println(e);
    }
  }
}
