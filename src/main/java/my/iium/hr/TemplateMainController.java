package my.iium.hr;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import my.iium.hr.Service.FileRefNumberService;
import my.iium.hr.Service.TemplateMainService;
import my.iium.hr.model.FileRefNumber;
import my.iium.hr.model.TemplateMain;
import my.iium.hr.security.SecurityUtil;

@Controller
@RequestMapping("/template")
public class TemplateMainController {

	@Autowired
	private FileRefNumberService fileRefNumberService;
	@Autowired
	private TemplateMainService templateMainService;

	// Template drafting
	// @PreAuthorize("hasAuthority('Admin')")
	@GetMapping("")
	public String template(Model model) {

		model.addAttribute("listTemplate", templateMainService.getAllTemplateMain());

		return "template";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/showNewTemplateForm")
	public String showNewTemplateForm(Model model) {
		// create model attribute to bind form data
		TemplateMain templateMain = new TemplateMain();
		model.addAttribute("templateMain", templateMain);
		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);
		return "new_template";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@PostMapping("/saveTemplate")
	public String saveTemplate(@ModelAttribute("templateMain") TemplateMain templateMain) {

		templateMain.setRefCodeID(SecurityUtil.cleanIt(templateMain.getRefCodeID()));
		templateMain.setTitle(SecurityUtil.cleanIt(templateMain.getTitle()));
		templateMain.setContent(SecurityUtil.cleanIt(templateMain.getContent()));
		templateMain.setContentSign(SecurityUtil.cleanIt(templateMain.getContentSign()));
		templateMain.setContentCC(SecurityUtil.cleanIt(templateMain.getContentCC()));
		templateMain.setEnterBy(SecurityUtil.cleanIt(templateMain.getEnterBy()));
		if (templateMain.getUpdateBy() != null)
			templateMain.setUpdateBy(SecurityUtil.cleanIt(templateMain.getUpdateBy()));
		// save Template to database
		templateMainService.saveTemplate(templateMain);
		return "redirect:/template";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/showFormForUpdateTemplate/{id}")
	public String showFormForUpdateTemplate(@PathVariable(value = "id") long id, Model model) {

		// get template from the service
		TemplateMain templateMain = templateMainService.getTemplateById(id);

		// set template as a model attribute to pre-populate the form
		model.addAttribute("templateMain", templateMain);
		List<FileRefNumber> listFileRefNumber = fileRefNumberService.getAllFileRefNumber();
		model.addAttribute("listFileRefNumber", listFileRefNumber);

		return "update_template";
	}

	@PreAuthorize("hasAuthority('Admin')")
	@GetMapping("/deleteTemplate/{id}")
	public String deleteTemplate(@PathVariable(value = "id") long id) {

		// call delete employee method
		this.templateMainService.deleteTemplateById(id);
		return "redirect:/template";
	}

	@GetMapping("/printTemplate/{id}")
	public String printTemplate(@PathVariable(value = "id") long id, Model model) {

		// get template from the service
		TemplateMain templateMain = templateMainService.getTemplateById(id);

		// set template as a model attribute to pre-populate the form
		model.addAttribute("templateMain", templateMain);
		return "print_template";
	}

}
