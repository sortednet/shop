package net.sorted.shop.web;

import net.sorted.shop.dao.StockItemDao;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/shop/browser")
public class StockBrowserController {
    protected static Logger logger = LoggerFactory.getLogger(StockBrowserController.class);
    
    private final StockItemDao stockItemDao;
    
    public StockBrowserController(StockItemDao stockItemDao) {
        this.stockItemDao = stockItemDao;
    }

    @RequestMapping(value = "/stocklist", method = RequestMethod.GET)
    public ModelAndView getStockListPage() {
        logger.debug("Received request to show AJAX, add page");
        
        ModelAndView mav = new ModelAndView("shop/stocklist");
        mav.addObject("stockItems", stockItemDao.getAll());
        
        return mav;
    }
}
