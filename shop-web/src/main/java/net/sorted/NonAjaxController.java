package net.sorted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/common/nonajax")
public class NonAjaxController {
    protected static Logger logger = LoggerFactory.getLogger("controller");

//    @Resource(name="springService")
//    private ArithmeticService springService;

    /**
     * Handles and retrieves the non-AJAX, ordinary Add page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getNonAjaxAddPage() {
        logger.debug("Received request to show non-AJAX, ordinary add page");
        System.out.println("Received request to show non-AJAX, ordinary add page");


        return "common/nonajax-add-page";
    }

    /**
     * Handles request for adding two numbers
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String add(@RequestParam(value = "inputNumber1", required = true) Integer inputNumber1,
                      @RequestParam(value = "inputNumber2", required = true) Integer inputNumber2,
                      Model model) {
        logger.debug("Received request to add two numbers");

        // Delegate to service to do the actual adding
//     Integer sum = springService.add(inputNumber1, inputNumber2);
        Integer sum = inputNumber1.intValue() + inputNumber2.intValue();

        // Add to model
        model.addAttribute("sum", sum);

        // This will resolve to /WEB-INF/jsp/nonajax-add-result-page.jsp
        return "common/nonajax-add-result-page";
    }
}
