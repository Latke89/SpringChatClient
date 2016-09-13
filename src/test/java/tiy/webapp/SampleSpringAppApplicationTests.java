package tiy.webapp;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.validation.constraints.AssertTrue;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SampleSpringAppApplicationTests {
	Server myServer;
	WebChatClient myClient;
	@Test
	public void contextLoads() {
	}

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {


	}

	@Test
	public void sendMessage() throws Exception {
		myServer = new Server();
		myClient = new WebChatClient();
		String message = "test message";
		Thread serverThread = new Thread(myServer);
		serverThread.start();
		myClient.sendMessage(message);
		System.out.println(myClient.serverResponse);
		assertNotNull(myClient.serverResponse);
	}

}
