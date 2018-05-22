package io.dama.vs.hase;

import java.util.HashSet;
import java.util.Set;

import jssc.SerialPort;
import jssc.SerialPortException;

/* macOS: driver https://plugable.com/drivers/prolific/ has to be installed manually */

public class HaSeConnector implements Runnable {
	private final String portName;
	private volatile boolean stopThread;
	private Set<HaSeObserver> observers;

	public HaSeConnector(String portName) {
		this.portName = portName;
		this.stopThread = false;
		this.observers = new HashSet<>();
	}

	public void register(HaSeObserver o) {
		synchronized (this.observers) {
			observers.add(o);
		}
	}
	
	public void unregister(HaSeObserver o) {
		synchronized (this.observers) {
			observers.remove(o);
		}
	}

	public void stopThread() {
		this.stopThread = true;
	}

	@Override
	public void run() {
		SerialPort port = new SerialPort(this.portName);
		try {
			port.openPort();
			port.setParams(//
					SerialPort.BAUDRATE_9600, //
					SerialPort.DATABITS_8, //
					SerialPort.STOPBITS_1, //
					SerialPort.PARITY_NONE);
			port.setFlowControlMode(SerialPort.FLOWCONTROL_RTSCTS_IN | SerialPort.FLOWCONTROL_RTSCTS_OUT);
			while (!stopThread) {
				String telegramString = port.readString(51);
				HaSeTelegram telegram = new HaSeTelegram(telegramString);
				Set<HaSeObserver> observerCopy; 
				synchronized (this.observers) {
					observerCopy = new HashSet<>(this.observers);
				}
				for (HaSeObserver o : observerCopy) {
					o.notify(telegram);
				}
				Thread.yield();
			}
		} catch (SerialPortException e) {
			System.err.println("error on COM-port: " + e);
		} finally {
			try {
				port.closePort();
			} catch (SerialPortException e) {
				System.err.println("error on COM-port: " + e);
			}
		}
	}

	public static void main(String[] args) {
		new Thread(new HaSeConnector("/dev/tty.usbserial")).start();
	}
}
