package my.iium.hr;

import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;

import my.iium.hr.Service.FileRefNumberService;
import my.iium.hr.Service.TemplateMainService;
import my.iium.hr.model.Document;
import my.iium.hr.model.FileRefNumber;
import my.iium.hr.model.VSStaff;
import my.iium.hr.model.ViewSisStaff;
import my.iium.hr.security.SecurityUtil;

@Controller
@RequestMapping("/document/process/chooseTemplate")
public class ApiController {

	@Value("${api.url}")
	private String apiUrl;
	@Autowired
	private TemplateMainService templateMainService;
	@Autowired
	private FileRefNumberService fileRefNumberService;

	@RequestMapping("/{id}/StaffAPI") // makes sure when the user enters the url they receive the home
														// screen
	public String apiStaff(@PathVariable(value = "id") long id, HttpServletRequest req, Model model, String page,
			String size) {

		model.addAttribute("templateID", id);

		VSStaff body = new VSStaff();

		String query = req.getQueryString();
		String querySearch = "";
		System.out.println("Query 0: " + query);
		if (query == null) {
			query = "";
		} else if (query.contains("search")) {
			query = "&" + query;
			if (query.contains("page")) {
				query = "&" + query.substring(8);
			} else {
				querySearch = "&" + query;
			}

		} else {
			query = "";
		}

		req.setAttribute("query", query);
		System.out.println("Query 1: " + query);

		try {
			// String url = "http://api-endpoint.iium.edu.my:8080/hrapi/staff/profile/";

			String url = apiUrl;
			String searchName = req.getParameter("searchName");
			String searchID = req.getParameter("searchID");
			String searchStatus;

			if (req.getParameter("searchStatus") != null) {
				searchStatus = "1";
			} else {
				searchStatus = "30";
			}

			req.setAttribute("searchName", searchName);
			req.setAttribute("searchID", searchID);
			req.setAttribute("searchStatus", searchStatus);

			System.out.println("Search Name : " + searchName);
			System.out.println("Page : " + page);
			System.out.println("Url : " + apiUrl);
			// request url

			if (page == null) {

			} else {
				url = apiUrl + "?page=" + page;

				if (querySearch != null || querySearch != "") {
					url = url + querySearch;
				}
			}

			if (searchName != null) {
				url = apiUrl + "?staffName=" + searchName;

				if (page != null)
					url = url + "&page=" + page;
			}

			if (searchID != null) {
				url = apiUrl + "?staffID=" + searchID;

				if (page != null)
					url = url + "&page=" + page;
			}

			if (searchStatus != null) {
				url = apiUrl + "?status=" + searchStatus;

				if (page != null)
					url = url + "&page=" + page;
			}

			if (searchName != null && searchID != null && searchID != null) {
				url = apiUrl + "?staffName=" + searchName + "&staffID=" + searchID + "&status=" + searchStatus;

				if (page != null)
					url = url + "&page=" + page;
			}

			// create an instance of RestTemplate
			RestTemplate restTemplate2 = new RestTemplate();

			// make an HTTP GET request

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity request = new HttpEntity(headers);

			ResponseEntity<VSStaff> exchange = restTemplate2.exchange(url, HttpMethod.GET, request, VSStaff.class);
			// ResponseEntity<ViewSisStaff> exchange = restTemplate2.exchange(url,
			// HttpMethod.GET,request,ViewSisStaff.class);
			/*
			 * ResponseEntity<List<ViewSisStaff>> exchange = restTemplate2.exchange(url,
			 * HttpMethod.GET, request, new ParameterizedTypeReference<List<ViewSisStaff>>()
			 * { });
			 */

			body = exchange.getBody();

			// ResponseEntity<ViewSisStaff[]> responseEntity =
			// restTemplate2.getForEntity(url, ViewSisStaff[].class);
			ViewSisStaff[] empList = body.getStaffList();

			// List<ViewSisStaff> body2 = Arrays.asList(empList);

			// System.out.println("Print body : " + exchange.getBody());

			model.addAttribute("staff", body);
			model.addAttribute("staff2", empList);

			return "staff_api";

		} catch (Exception e) {
			System.out.println(e.getMessage());
			body.setCurrentPage(0);
			body.setTotalPages(0);
			model.addAttribute("staff", body);
			model.addAttribute("staff2", null);
			return "staff_api";
			// throw e;
		}

	}

	@RequestMapping("/{id}/StaffAPI/{staffid}") // makes sure when the user enters the url they receive
																// the home screen
	public String apiStaffID(@PathVariable(value = "id") long id, @PathVariable(value = "staffid") String staffid,
			Model model) {

		if (id != 0)
			model.addAttribute("template", templateMainService.getTemplateById(id));
		else
			model.addAttribute("template", "0");

		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);

		ViewSisStaff body = new ViewSisStaff();
		try {
			body = getApiStaff(staffid);
			Document document = new Document();

			model.addAttribute("document", document);
			model.addAttribute("staff", body);

			return "new_draft";

		} catch (Exception e) {
			System.out.println(e.getMessage());
			model.addAttribute("staff", null);
			// throw e;
			return "staff_api";

		}

	}

	public ViewSisStaff getApiStaff(String staffid) {

		ViewSisStaff body = new ViewSisStaff();
		try {

			String url = apiUrl + staffid;

			// create an instance of RestTemplate
			RestTemplate restTemplate2 = new RestTemplate();

			// make an HTTP GET request

			HttpHeaders headers = new HttpHeaders();

			headers.setContentType(MediaType.APPLICATION_JSON);
			headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));

			HttpEntity request = new HttpEntity(headers);

			ResponseEntity<ViewSisStaff> exchange = restTemplate2.exchange(url, HttpMethod.GET, request,
					ViewSisStaff.class);

			body = exchange.getBody();

			return body;

		} catch (Exception e) {
			System.out.println(e.getMessage());
			// throw e;
			return body;

		}

	}

}
