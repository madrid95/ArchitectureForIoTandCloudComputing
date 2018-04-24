package var.sockets.udp.echo;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Client for echo var.sockets.udp.echo.EchoServer service. Sendet Kommandozeilenargument an HOST,
 * wartet bis TIMEOUT auf Antwort vom Server, liest sie und gibt den Inhalt aus.
 * 
 * @author Sandro Leuchter
 *
 */
class EchoClient {
  /**
   * host on which server is running (IP-address or hostname)
   */
  private static final String HOST = "localhost";
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4711;
  /**
   * maximum size of payload in datagram
   */
  private static final int BUFSIZE = 512;
  /**
   * timeout in ms for waiting for a response from server
   */
  private static final int TIMEOUT = 2000;

  /**
   * main method: entrypoint to run
   * 
   * @param args
   *          must be String[1]: message to be send to server
   */
  public static void main(String[] args) {
    byte[] data = args[0].getBytes();
    try (DatagramSocket socket = new DatagramSocket()) {
      socket.setSoTimeout(TIMEOUT); // Zeit in ms, für wie lange ein read() auf socket blockiert.
                                    // Bei timeout is java.net.SocketTimeoutException (TIMEOUT == 0
                                    // => blockiert für immer)
      InetAddress iaddr = InetAddress.getByName(HOST);
      DatagramPacket packetOut = new DatagramPacket(data, data.length, iaddr, PORT);
      socket.send(packetOut);
      DatagramPacket packetIn = new DatagramPacket(new byte[BUFSIZE], BUFSIZE);
      socket.receive(packetIn);
      String received = new String(packetIn.getData(), 0, packetIn.getLength());
      System.out.println("Received: " + received);
    } catch (SocketTimeoutException e) {
      System.err.println("Timeout: " + e.getMessage());
    } catch (Exception e) {
      System.err.println(e);
    }
  }
}