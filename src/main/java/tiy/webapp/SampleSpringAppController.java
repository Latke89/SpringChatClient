package tiy.webapp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;

/**
 * Created by Brett on 9/12/16.
 */
@Controller
public class SampleSpringAppController {

	@RequestMapping(path = "/person-url", method = RequestMethod.GET)
	public String person(Model model, String name, String city, int age) {
		Person p = new Person(name, city, age);
		model.addAttribute("person-object", p);
		return "person-view";

	}

	@RequestMapping(path = "/", method = RequestMethod.GET)
	public String home(Model model, HttpSession session) {
		model.addAttribute("name", session.getAttribute("userName"));
		return "home";
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	public String login(HttpSession session, String userName) {
		session.setAttribute("userName", userName);
		return "redirect:/";
	}

	@RequestMapping(path = "/chat", method = RequestMethod.GET)
	public String chat(Model model, HttpSession session) {
		return "chat";
	}

	@RequestMapping(path = "/input", method = RequestMethod.POST)
	public String input(HttpSession session, String message) {
		WebChatClient myClient = new WebChatClient();
		myClient.sendMessage(message);
		return "redirect:/chat";

	}
}
