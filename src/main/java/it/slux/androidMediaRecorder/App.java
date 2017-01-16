package it.slux.androidMediaRecorder;

import java.io.IOException;
import java.net.InetAddress;

import javax.jmdns.JmDNS;
import javax.jmdns.ServiceInfo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author Alessio Di Fazio
 */
public class App
{
    /** The logger */
    private static final Logger LOG = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) throws Exception
    {
        System.out.println("Android Media Recorder - Started");

        try
        {
            // Create a JmDNS instance
            JmDNS jmdns = JmDNS.create(InetAddress.getLocalHost());

            // Register a service
            ServiceInfo serviceInfo = ServiceInfo.create(" _googlecast._tcp", "googlecast", 8009,
                    "Chromecast Receiver");
            LOG.info("Service Info: " + serviceInfo.getNiceTextString() + " " + serviceInfo.getProtocol() + " "
                    + serviceInfo.getName());

            jmdns.registerService(serviceInfo);
            LOG.info("DNS registered");

            // Wait a bit
            Thread.sleep(250000);

            // Unregister all services
            jmdns.unregisterAllServices();
            LOG.info("DNS service unregistered. Exiting...");

        }
        catch (IOException e)
        {
            LOG.error("Unknown Error", e);
        }
    }
}
