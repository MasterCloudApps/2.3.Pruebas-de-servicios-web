package es.codeurjc.test.web;

import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import org.junit.jupiter.api.BeforeEach;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;

import com.intuit.karate.junit5.Karate;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MessageControllerTest {

    @LocalServerPort
    int port;

    @BeforeEach
    public void setPort() throws SocketException{
        System.setProperty("base.url", "http://localhost:" + port);
        // try (final DatagramSocket datagramSocket = new DatagramSocket()) {
        //     datagramSocket.connect(InetAddress.getByName("8.8.8.8"), 12345);
        //     String baseUrl = "http://" + datagramSocket.getLocalAddress().getHostAddress() + ":" + port;
        //     System.setProperty("base.url", baseUrl);
        // } catch (UnknownHostException e) {
        //     e.printStackTrace();
        // }
    }

    @Karate.Test
    public Karate testSample() throws UnknownHostException {
        return Karate.run("message").relativeTo(getClass());
    }

}
