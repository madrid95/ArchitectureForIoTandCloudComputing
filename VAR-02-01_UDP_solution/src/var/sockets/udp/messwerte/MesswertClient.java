package var.sockets.udp.messwerte;

import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketTimeoutException;
import java.util.Random;

/**
 * Client for var.sockets.udp.messwerte.MesswertServer service. Sendet fortwährend Messwerte
 * 
 * @author s.leuchter
 *
 */
public class MesswertClient {
  /**
   * host on which server is running (IP-address or hostname)
   */
  private static final String HOST = "localhost";
  /**
   * port on which service is running on host
   */
  private static final int PORT = 4713;
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
    Random randomGenerator = new Random();
    try (DatagramSocket socket = new DatagramSocket()) {
      socket.setSoTimeout(TIMEOUT); // Zeit in ms, für wie lange ein read() auf socket blockiert.
                                    // Bei timeout is java.net.SocketTimeoutException (TIMEOUT == 0
                                    // => blockiert für immer)
      InetAddress iaddr = InetAddress.getByName(HOST);
      while (true) {
        String messung = Double.toString(randomGenerator.nextDouble() * 100.0);
        DatagramPacket packetOut = new DatagramPacket(messung.getBytes(), messung.length(), iaddr,
            PORT);
        socket.send(packetOut);
        Thread.sleep(5000);
      }
    } catch (SocketTimeoutException e) {
      System.err.println("Timeout: " + e.getMessage());
    } catch (Exception e) {
      System.err.println(e);
    }
  }

}
