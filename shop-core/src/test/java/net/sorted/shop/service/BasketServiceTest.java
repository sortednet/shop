package net.sorted.shop.service;

import static org.junit.Assert.assertEquals;
import net.sorted.shop.dao.BasketItemDao;
import net.sorted.shop.dao.ShopUserDao;
import net.sorted.shop.dao.StockItemDao;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;

import org.hibernate.SessionFactory;
import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/etc/spring/shop-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = true)
public class BasketServiceTest extends AbstractTransactionalJUnit4SpringContextTests {

    @Autowired
    private BasketService basketService;
    
    @Autowired
    private StockItemDao stockItemDao;
    
    @Autowired
    private ShopUserDao shopUserDao;
    
    @Autowired
    private BasketItemDao basketItemDao;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private StockItem item1;
    private ShopUser user;
    

    @Before
    public void setUp() {
        item1 = getDefaultStockItem();
        stockItemDao.save(item1);
        
        user = getDefaultShopUser();
        shopUserDao.save(user);
        sessionFactory.getCurrentSession().flush();
    }    
    
    @Test
    public void testAddItem() {

        DateTime timeAdded = new DateTime();
        int before = basketService.numItems(user);
        basketService.addItem(item1, user, timeAdded, 1);
        int after = basketService.numItems(user);
        assertEquals(1, (after-before));
    }
    
    @Test
    public void testQtyReducedWhenItemRemoved() {

        DateTime timeAdded = new DateTime();
        int before = basketService.numItems(user);
        basketService.addItem(item1, user, timeAdded, 3);
        basketService.removeItem(item1, user, 1);
        int after = basketService.numItems(user);
        assertEquals(2, (after-before));

    }
    
    @Test
    public void testBasketItemRemovedWhenQtyZero() {

        DateTime timeAdded = new DateTime();
        int before = basketService.numItems(user);
        basketService.addItem(item1, user, timeAdded, 3);
        
        // check that the basket item gets deleted when the quantity reaches 0
        basketService.removeItem(item1, user, 3);
        int after = basketService.numItems(user);
        assertEquals(0, (after-before));        
        assertEquals(0, basketItemDao.getForUser(user).size());
    }
        
    private StockItem getDefaultStockItem() {
        StockItem i = new StockItem();
        i.setSku("SK1");
        i.setShortDescription("Short desc");
        i.setFullDescription("A much fuller description of something");
        return i;
    }
    
    private ShopUser getDefaultShopUser() {
        ShopUser u = new ShopUser();
        u.setUserName("TestUser");
        u.setPassword("password");

        return u;
    }
}
