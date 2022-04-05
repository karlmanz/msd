package my.iium.hr;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

import my.iium.hr.Service.DocumentService;
import my.iium.hr.Service.FileRefNumberService;
import my.iium.hr.Service.TemplateMainService;
import my.iium.hr.model.*;
import my.iium.hr.repository.NotificationsRepository;
import my.iium.hr.security.MyUser;
import my.iium.hr.security.MyUserService;
import my.iium.hr.security.SecurityUserDetailsService;
import my.iium.hr.security.SecurityUtil;

@Controller
public class LoginController {
	@Autowired
	private SecurityUserDetailsService userDetailsManager;

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;

	@Autowired
	private SecurityUserDetailsService userService;

	@Autowired
	NotificationsRepository notificationsRepo;

	@Autowired
	private DocumentService documentService;
	@Autowired
	private FileRefNumberService fileRefNumService;
	@Autowired
	private TemplateMainService templateService;
	@Autowired
	private MyUserService usrService;

	@GetMapping("/login")
	public String login(HttpServletRequest request, HttpSession session) {
		session.setAttribute("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		return "login";
	}

	@GetMapping("/UserRegistration")
	public String register() {
		return "register";
	}

	@PostMapping(value = "/register", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, produces = {
			MediaType.APPLICATION_ATOM_XML_VALUE, MediaType.APPLICATION_JSON_VALUE })
	public String addUser(@RequestParam Map<String, String> body) {

		MyUser user = new MyUser();
		user.setUsername(SecurityUtil.cleanIt(body.get("username")));
		user.setPassword(passwordEncoder.encode(body.get("password")));
		user.setFullname(SecurityUtil.cleanIt(body.get("fullname")));
		user.setUseremail(SecurityUtil.cleanIt(body.get("useremail"))); // not sure if we need to sanitize this
		user.setUserID(SecurityUtil.cleanIt(body.get("userID")));
		if (body.get("hurisDept") != null)
			user.setHurisDept(SecurityUtil.cleanIt(body.get("hurisDept")));
		if (body.get("enterBy") != "")
			user.setEnterBy(SecurityUtil.cleanIt(body.get("enterBy")));
		else
			user.setEnterBy(SecurityUtil.cleanIt(body.get("username")));

		Date date1 = null;
		Date date2 = null;
		try {
			date1 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("dateStart"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			date2 = new SimpleDateFormat("yyyy-MM-dd HH:mm").parse(body.get("enterDate"));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		user.setDateStart(date1);
		user.setEnterDate(date2);
		user.setAccountNonLocked(true);

		// role implement
		// userService.registerDefaultUser(user);
		userDetailsManager.createUser(user);
		return "redirect:/";
	}

	@GetMapping("/error")
	public String error() {
		return "error";
	}

	@GetMapping("/access_denied")
	public String getAccessDenied(Model model) {
		MyUser user = userService.get(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("userName", user.getFullname());
		return "access_denied";
	}

	@RequestMapping("/") // makes sure when the user enters the url they receive the home screen
	public String home(Model model, Quote quote) {

		// request url
		String url = "https://api.quotable.io/random";

		// create an instance of RestTemplate
		RestTemplate restTemplate = new RestTemplate();

		// make an HTTP GET request

		HttpHeaders headers = new HttpHeaders();

		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

		HttpEntity request = new HttpEntity(headers);

		ResponseEntity<Quote> exchange = restTemplate.exchange(url, HttpMethod.GET, request, Quote.class);

		Quote body = exchange.getBody();
		String id = body.get_id();
		String[] tags = body.getTags();
		String content = body.getContent();
		String author = body.getAuthor();
		String authorSlug = body.getAuthorSlug();
		int length = body.getLength();

		List<Notifications> notifyList2 = new ArrayList<Notifications>();
		notificationsRepo.findAll().forEach(notifyList2::add);

		model.addAttribute("exchange", body);
		model.addAttribute("notify", notifyList2);
		model.addAttribute("docNum", documentService.getDocumentCount());
		model.addAttribute("refNum", fileRefNumService.getFileRefNumCount());
		model.addAttribute("templateNum", templateService.getTemplateCount());
		model.addAttribute("userNum", usrService.getUserCount());
		MyUser user = userService.get(SecurityContextHolder.getContext().getAuthentication().getName());
		model.addAttribute("userName", user.getFullname());
		return "home";
	}

	@GetMapping("/profile")
	public String profile(Model model, @RequestParam Map<String, String> body) {

		MyUser user = userService.get(SecurityContextHolder.getContext().getAuthentication().getName());
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);

		return "profile";
	}

	private String getErrorMessage(HttpServletRequest request, String key) {
		Exception exception = (Exception) request.getSession().getAttribute(key);
		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else {
			error = "Invalid username and password!";
		}
		return error;
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin/userRole")
	public String userRole(Model model, String keyword) {
		// List<MyUser> listUsers = userService.listAll();
		// model.addAttribute("listUsers", listUsers);
		model.addAttribute("listUsers", userService.getAllUser());

		return "userRole";
	}
	//DELETE AFTER TESTING USER ROLE
	/*
	 * @PreAuthorize("hasAuthority('Admin')")
	 * 
	 * @GetMapping("/admin/userRole/page/{pageNo}") public String
	 * findPaginatedUser(@PathVariable(value = "pageNo") int pageNo,
	 * 
	 * @RequestParam("sortField") String sortField, @RequestParam("sortDir") String
	 * sortDir, Model model, String keyword) {
	 * 
	 * int pageSize = 5;/* how many templates in one page
	 * 
	 * // List<MyUser> listUsers = userService.listAll(); //
	 * model.addAttribute("listUsers", listUsers);
	 * 
	 * Page<MyUser> page = userService.findPaginated(pageNo, pageSize, sortField,
	 * sortDir); List<MyUser> listUsers = page.getContent();
	 * 
	 * model.addAttribute("currentPage", pageNo); model.addAttribute("totalPages",
	 * page.getTotalPages()); model.addAttribute("totalItems",
	 * page.getTotalElements());
	 * 
	 * model.addAttribute("sortField", sortField); model.addAttribute("sortDir",
	 * sortDir); model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc"
	 * : "asc"); // i'm testing with this part refer to template for the original
	 * code. if (keyword != null) { model.addAttribute("listUsers", listUsers);
	 * model.addAttribute("listUsers", userService.findByKeyword(keyword)); } else {
	 * // listUsers = userService.listAll(); model.addAttribute("listUsers",
	 * listUsers); }
	 * 
	 * return "userRole"; }
	 */

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/admin/userRole/edit/{username}")
	public String editUser(@PathVariable("username") String username, Model model) {
		MyUser user = userService.get(username);
		List<Role> listRoles = userService.listRoles();
		model.addAttribute("user", user);
		model.addAttribute("listRoles", listRoles);
		return "update_user";
	}

	@PostMapping("/profile/editPass")
	public String savePassword(MyUser user, @RequestParam Map<String, String> body) {
		user.setUsername(SecurityUtil.cleanIt(body.get("username")));
		user.setFullname(SecurityUtil.cleanIt(body.get("fullname")));
		user.setUseremail(SecurityUtil.cleanIt(body.get("useremail"))); // not sure if we need to sanitize this
		user.setUserID(SecurityUtil.cleanIt(body.get("userID")));
		if (body.get("hurisDept") != null)
			user.setHurisDept(SecurityUtil.cleanIt(body.get("hurisDept")));
		user.setEnterBy(SecurityUtil.cleanIt(body.get("enterBy")));
		if (body.get("updateBy") != null)
			user.setUpdateBy(SecurityUtil.cleanIt(body.get("updateBy")));
		user.setPassword(passwordEncoder.encode(body.get("password")));
		userService.save(user);

		return "redirect:/profile/{username}";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/admin/users/save")
	public String savePasswordByAdmin(@ModelAttribute("user") MyUser user, Model model,
			@RequestParam Map<String, String> body) {

		user.setUsername(SecurityUtil.cleanIt(user.getUsername()));
		user.setFullname(SecurityUtil.cleanIt(user.getFullname()));
		user.setUseremail(SecurityUtil.cleanIt(user.getUseremail())); // not sure if we need to sanitize this
		user.setUserID(SecurityUtil.cleanIt(user.getUserID()));
		if (user.getHurisDept() != null)
			user.setHurisDept(SecurityUtil.cleanIt(user.getHurisDept()));
		user.setEnterBy(SecurityUtil.cleanIt(user.getEnterBy()));
		if (user.getUpdateBy() != null)
			user.setUpdateBy(SecurityUtil.cleanIt(user.getUpdateBy()));
		if (body.get("passwordCheck") != null) {
			System.out.print(body.get("passwordCheck"));
			user.setPassword(passwordEncoder.encode(user.getPassword()));
		} else {
			System.out.print("yes");
			user.setPassword(user.getPassword());
		}

		user.setDateStart(user.getDateStart());
		user.setEnterDate(user.getEnterDate());

		userService.save(user);

		return "redirect:/admin/userRole";
	}
}
