package net.sorted;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/common/ajax")
public class AjaxController {

    protected static Logger logger = LoggerFactory.getLogger("controller");


    /**
     * Handles and retrieves the AJAX Add page
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String getAjaxAddPage() {
        logger.debug("Received request to show AJAX, add page");

        return "common/ajax-add-page";
    }

    /**
     * Handles request for adding two numbers
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody Integer add(@RequestParam(value = "inputNumber1", required = true) Integer inputNumber1,
                                     @RequestParam(value = "inputNumber2", required = true) Integer inputNumber2,
                                     Model model) {
        logger.debug("Received request to add two numbers: "+inputNumber1+" + "+inputNumber2);

        Integer sum = inputNumber1.intValue() + inputNumber2.intValue();
        System.out.println("Returning sum "+sum.intValue());

        return sum;
    }
}
