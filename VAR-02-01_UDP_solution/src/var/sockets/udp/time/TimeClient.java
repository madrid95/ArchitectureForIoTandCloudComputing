package var.sockets.udp.time;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;

/**
 * Client for echo var.sockets.udp.time.TimeServer service. Sendet leeres Paket an Server, wartet
 * bis TIMEOUT aus Antwort vom Server, liest die Antwort und gibt sie aus
 * 
 * @author Sandro Leuchter
 *
 */
class TimeClient {
  /**
   * host on which server is running (IP-address or hostname)
   */
  private static final String HOST = "localhost";
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4712;
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
   *          ignored
   */
  public static void main(String[] args) {
    try (DatagramSocket socket = new DatagramSocket()) {
      socket.setSoTimeout(TIMEOUT); // Zeit in ms, für wie lange ein read() auf socket blockiert.
                                    // Bei timeout is java.net.SocketTimeoutException (TIMEOUT == 0
                                    // => blockiert für immer)
      InetAddress iaddr = InetAddress.getByName(HOST);
      DatagramPacket packetOut = new DatagramPacket(new byte[0], 0, iaddr, PORT);
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