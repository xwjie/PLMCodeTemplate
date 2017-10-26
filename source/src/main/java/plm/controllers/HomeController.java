package plm.controllers;

import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import plm.common.beans.ResultBean;
import plm.common.utils.UserUtil;
import plm.config.ServerCfg;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		logger.info("Welcome home! The client locale is {}.", locale);

		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG,
				DateFormat.LONG, locale);

		String formattedDate = dateFormat.format(date);

		model.addAttribute("serverTime", formattedDate);

		return "home";
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public ResultBean<String> login(HttpSession session, String username) {
		logger.info("login user:" + username);

		// TODO 只是模拟登陆
		session.setAttribute(UserUtil.KEY_USER, username);

		return new ResultBean<String>(username);
	}

	@CrossOrigin
	@GetMapping(value = "/resttest/{key}")
	@ResponseBody
	public ResultBean<String> restTest(@PathVariable String key) {
		logger.info("resttest, key=" + key);
		try {
			Thread.sleep(2000);
		}
		catch (InterruptedException e) {
			e.printStackTrace();
		}
		return new ResultBean<String>("input key is " + key);
	}

	@Autowired
	ServerCfg cfg;

	@GetMapping(value = "/configTest")
	@ResponseBody
	public ResultBean<ServerCfg> configTest() {
		return new ResultBean<ServerCfg>(cfg);
	}
}
