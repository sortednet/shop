package net.sorted.shop.dao;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.fail;

import java.util.List;

import net.sorted.shop.domain.BasketItem;
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
public class BasketItemDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private StockItemDao stockItemDao;
    
    @Autowired
    private BasketItemDao basketItemDao;
    
    @Autowired
    private ShopUserDao shopUserDao;
        
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
        DateTime added = new DateTime();

        BasketItem i = getDefaultBasketItem();    
        i.setAddedToBasket(added);
        
        int before = basketItemDao.getAll().size();
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        int after = basketItemDao.getAll().size();
        assertEquals(1, (after-before));
    }
    
    @Test
    public void testGetById() {
        BasketItem i = getDefaultBasketItem();        
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        BasketItem j = basketItemDao.getById(i.getBasketItemId());
        assertEquals(i, j);
    }
    
    @Test
    public void testGetByIdFails() {
        BasketItem j = basketItemDao.getById(987654321);
        assertNull(j);
    }
    
    @Test
    public void testChangeItem() {
        BasketItem i = getDefaultBasketItem();  
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        StockItem item2 = getDefaultStockItem();
        item2.setSku("SKU2");
        stockItemDao.save(item2);
        i.setStockItem(item2);
        
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        BasketItem j = basketItemDao.getById(i.getBasketItemId());
        assertEquals(item2, j.getStockItem());
    }
    
    @Test
    public void testDelete() {
        BasketItem i = getDefaultBasketItem();
                
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        basketItemDao.delete(i);
        sessionFactory.getCurrentSession().flush();
        
        i = basketItemDao.getById(i.getBasketItemId());
        assertNull(i);
        assertNotNull(shopUserDao.getById(user.getShopUserId()));
        assertNotNull(stockItemDao.getById(item1.getStockItemId()));
    }
    
    @Test(expected=org.hibernate.exception.ConstraintViolationException.class)
    public void testCantSaveWithNullUser() throws Exception {
        BasketItem i = getDefaultBasketItem();
        i.setShopUser(null);
        
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        fail("Should not have been able to save basket item with a null user");
    }
    
    @Test
    public void testFindByItemAndUser() {
        BasketItem i = getDefaultBasketItem();
        i.setQuantity(5);
        basketItemDao.save(i);
        
        StockItem item2 = getDefaultStockItem();
        item2.setSku("SKU2");
        stockItemDao.save(item2);
        i = getDefaultBasketItem();
        i.setStockItem(item2);
        basketItemDao.save(i); // add second item to basket so we test getting the exact item and user and not all for user
        sessionFactory.getCurrentSession().flush();
        
        BasketItem j = basketItemDao.getByItemAndUser(item1, user);
        assertNotNull(j);
        assertEquals(5, j.getQuantity());
    }
    
    @Test
    public void testFindByUser() {
        BasketItem i = getDefaultBasketItem();
        basketItemDao.save(i);
        
        StockItem item2 = getDefaultStockItem();
        item2.setSku("SKU2");
        stockItemDao.save(item2);
        i = getDefaultBasketItem();
        i.setStockItem(item2);
        basketItemDao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        List<BasketItem> items = basketItemDao.getForUser(user);
        assertNotNull(items);
        assertEquals(2, items.size());
    }
    
    private BasketItem getDefaultBasketItem() {
        BasketItem i = new BasketItem();    
        i.setShopUser(user);
        i.setStockItem(item1);
        return i;
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
