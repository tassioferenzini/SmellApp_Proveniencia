package control;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import gnu.io.SerialPortEvent;
import gnu.io.SerialPortEventListener;
import java.util.Enumeration;

public class GasSmell implements SerialPortEventListener {

    private static GasSmell instance;

    private GasSmell() {

    }

    public static synchronized GasSmell getInstance() {
        if (instance == null) {
            instance = new GasSmell();
            instance.initialize();
        }
        return instance;
    }

    int numMeasures = 0;
    int numVariables = 0;//4
    Fila<Measurement> environmentQueue;
    Fila<Measurement> personQueue;
    boolean startReadGasEnvironment = false, startReadGasPerson = false;
    SerialPort serialPort;
    String connectedPortName = ""; 

    double co2 = 0, ch4 = 0, h2 = 0, alcohol = 0;
    /**
     * The port we're normally going to use.
     */
    private static final String PORT_NAMES[] = {
        //"/dev/tty.usbserial-A9007UX1", // Mac OS X
        "/dev/tty.usbmodem1411", // Mac OS X
    // "/dev/ttyACM0", // Raspberry Pi
    //"/dev/ttyUSB0", // Linux
    //"COM3", // Windows
    };
    /**
     * A BufferedReader which will be fed by a InputStreamReader converting the
     * bytes into characters making the displayed results codepage independent
     */
    private BufferedReader input;
    /**
     * The output stream to the port
     */
    private OutputStream output;
    /**
     * Milliseconds to block while waiting for port open
     */
    private static final int TIME_OUT = 2000;
    /**
     * Default bits per second for COM port.
     */
    private static final int DATA_RATE = 9600;

    public String getConnectedPortName() {
        return connectedPortName;
    }
    
    

    private void initialize() {
        // the next line is for Raspberry Pi and 
        // gets us into the while loop and was suggested here was suggested http://www.raspberrypi.org/phpBB3/viewtopic.php?f=81&t=32186
        //System.setProperty("gnu.io.rxtx.SerialPorts", "/dev/ttyACM0");

        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();

        //First, Find an instance of serial port as set in PORT_NAMES.
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier) portEnum.nextElement();
            for (String portName : PORT_NAMES) {
                if (currPortId.getName().equals(portName)) {
                    portId = currPortId;
                    this.connectedPortName = portName;
                    break;
                }
            }
        }
        if (portId == null) {
            System.out.println("Could not find COM port.");
            // this.close();
            return;
        }

        try {

            environmentQueue = new Fila<Measurement>();
            personQueue = new Fila<Measurement>();
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(),
                    TIME_OUT);

            // set port parameters
            serialPort.setSerialPortParams(DATA_RATE,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams
            input = new BufferedReader(new InputStreamReader(serialPort.getInputStream()));
            output = serialPort.getOutputStream();

            // add event listeners
            serialPort.addEventListener(this);
            serialPort.notifyOnDataAvailable(true);
        } catch (Exception e) {
            System.err.println(e.toString());
        }

        //  return true;
    }

    /**
     * This should be called when you stop using the port. This will prevent
     * port locking on platforms like Linux.
     */
    private synchronized void close() {
        if (serialPort != null) {
            environmentQueue = new Fila<Measurement>();
            personQueue = new Fila<Measurement>();
            startReadGasPerson = false;
            startReadGasEnvironment = false;
            serialPort.removeEventListener();
            serialPort.close();
        }
    }

    public synchronized void reinit() {
        if (serialPort != null) {
            environmentQueue = new Fila<Measurement>();
            personQueue = new Fila<Measurement>();
            startReadGasPerson = false;
            startReadGasEnvironment = false;
            // serialPort.removeEventListener();
            // serialPort.close();
        }
    }

    /**
     * Handle an event on the serial port. Read the data and print it.
     */
    public synchronized void serialEvent(SerialPortEvent oEvent) {
        if (oEvent.getEventType() == SerialPortEvent.DATA_AVAILABLE) {
            try {

                String inputLine = input.readLine();
                // System.out.println(inputLine);

                String values[] = inputLine.split(":");

                switch (values[0]) {
                    case "CO2":
                        co2 = Double.parseDouble(values[1]);
                        break;
                    case "CH4":
                        ch4 = Double.parseDouble(values[1]);
                        break;
                    case "H2":
                        h2 = Double.parseDouble(values[1]);
                        break;
                    case "Alcohol":
                        alcohol = Double.parseDouble(values[1]);
                        break;
                    default:
                        System.out.println("Gás nao cadastrado");
                }
                this.numVariables++;
                if (this.numVariables == 4) {
                    Measurement measurement = new Measurement();
                    measurement.co2 = co2;
                    measurement.ch4 = ch4;
                    measurement.h2 = h2;
                    measurement.alcohol = alcohol;

                    //   System.out.println("Measurement is: ");
                    //  System.out.println("CO2: "+co2);
                    //  System.out.println("CH4: "+ch4);
                    //  System.out.println("H2: "+h2);
                    //  System.out.println("Alcohol: "+alcohol);
                    this.numVariables = 0;

                    if (!startReadGasPerson && startReadGasEnvironment) {
                        //   System.out.println("ENTREI EM ENVIRONMENT QUEUE");
                        environmentQueue.insere(measurement);
                    }
                    if (startReadGasPerson) {
                        //  System.out.println("ENTREI EM PERSON QUEUE");
                        personQueue.insere(measurement);
                    }

                }
            } catch (Exception e) {
                System.err.println(e.toString());
            }
        }
        // Ignore all the other eventTypes, but you should consider the other ones.
    }

    public Fila<Measurement> getEnvironmentQueue() {
        return environmentQueue;
    }

    public void setEnvironmentQueue(Fila<Measurement> environmentQueue) {
        this.environmentQueue = environmentQueue;
    }

    public Fila<Measurement> getPersonQueue() {
        return personQueue;
    }

    public void setPersonQueue(Fila<Measurement> personQueue) {
        this.personQueue = personQueue;
    }

    public boolean isStartReadGasEnvironment() {
        return startReadGasEnvironment;
    }

    public void setStartReadGasEnvironment(boolean startReadGasEnvironment) {
        this.startReadGasEnvironment = startReadGasEnvironment;
    }

    public boolean isStartReadGasPerson() {
        return startReadGasPerson;
    }

    public void setStartReadGasPerson(boolean startReadGasPerson) {
        this.startReadGasPerson = startReadGasPerson;
    }

//    public static void main(String[] args) throws Exception {
//        GasSmell main = new GasSmell();
//        main.initialize();
//        Thread t=new Thread() {
//			public void run() {
//				//the following line will keep this app alive for 1000 seconds,
//				//waiting for events to occur and responding to them (printing incoming messages to console).
//				try {Thread.sleep(1000000);} catch (InterruptedException ie) {}
//			}
//		};
//		t.start();
//        System.out.println("Started");
//    }
}
