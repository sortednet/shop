package net.sorted.shop.web;

import net.sorted.shop.dao.ShopUserDao;
import net.sorted.shop.dao.StockItemDao;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;
import net.sorted.shop.service.BasketService;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Required;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/shop/basket")
public class BasketController {
    
    String userName = "TestUser";
    
    
    protected static Logger logger = LoggerFactory.getLogger(BasketController.class);
    
    private BasketService basketService;
    private ShopUserDao shopUserDao;
    private StockItemDao stockItemDao;
    
//    public BasketController(BasketService basketService, ShopUserDao shopUserDao, StockItemDao stockItemDao) {
//        this.basketService = basketService;
//        this.shopUserDao = shopUserDao;
//        this.stockItemDao = stockItemDao;
//    }
    
    
    @RequestMapping(value = "/numItems", method = RequestMethod.GET, produces="application/json")
    public @ResponseBody Integer getNumItemsInBasket() {

        ShopUser user = shopUserDao.getByName(userName);

        return basketService.numItems(user);
    }    
    
    @Transactional
    @RequestMapping(value = "/addItem", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody Boolean addItemToBasket(@RequestParam(value = "stockItemId", required = true) long stockItemId, 
                                @RequestParam(value = "quantity", required = true) int quantity) {

        ShopUser user = shopUserDao.getByName(userName);
        StockItem stockItem = stockItemDao.getById(stockItemId);
        basketService.addItem(stockItem, user, new DateTime(), quantity);
        return Boolean.TRUE;
    }
    
    @Transactional
    @RequestMapping(value = "/removeItem", method = RequestMethod.POST, produces="application/json")
    public @ResponseBody Boolean removeItemFromBasket(@RequestParam(value = "stockItemId", required = true) long stockItemId, 
                                @RequestParam(value = "quantity", required = true) int quantity) {

        
        ShopUser user = shopUserDao.getByName(userName);
        StockItem stockItem = stockItemDao.getById(stockItemId);
        basketService.removeItem(stockItem, user, quantity);
        return Boolean.TRUE;
    }

    @Required
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }
    @Required
    public void setShopUserDao(ShopUserDao shopUserDao) {
        this.shopUserDao = shopUserDao;
    }
    @Required
    public void setStockItemDao(StockItemDao stockItemDao) {
        this.stockItemDao = stockItemDao;
    }
    
    
    
    

//    @RequestMapping(value = "/numItems", method = RequestMethod.GET, produces="application/json")
//    public @ResponseBody Integer getNumItemsInBasket(HttpServletRequest request, Principal principal) {
//        logger.debug("Received request to show AJAX, add page");
//    //    request.getSession().getAttribute("shopUser");
//        String userName = principal.getName();
//        if (userName == null) {
//            userName = "TestUser";
//        }
//        ShopUser user = shopUserDao.getByName(userName);
//
//        
//        return basketService.numItems(user);
//    }
    
    
    
    
    
    
    
    
}
