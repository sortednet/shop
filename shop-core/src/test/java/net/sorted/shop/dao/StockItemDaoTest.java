package net.sorted.shop.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import net.sorted.shop.domain.StockItem;

import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractTransactionalJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( { "/etc/spring/shop-context.xml" })
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class StockItemDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private StockItemDao dao;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testAddItem() {
        StockItem i = getDefaultStockItem();       
        
        int before = dao.getAll().size();
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));
    }
    
    @Test
    public void testGetById() {
        StockItem i = getDefaultStockItem();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        StockItem j = dao.getById(i.getStockItemId());
        assertEquals(i, j);
    }
    
    @Test
    public void testGetByIdFails() {
        StockItem j = dao.getById(987654321);
        assertNull(j);
    }
    
    @Test
    public void testDelete() {
        StockItem i = getDefaultStockItem();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        StockItem j = dao.getById(i.getStockItemId());
        assertEquals(i, j);        
        dao.delete(j);
        sessionFactory.getCurrentSession().flush();
        
        j = dao.getById(i.getStockItemId());
        assertNull(j);
    }
    
    private StockItem getDefaultStockItem() {
        StockItem i = new StockItem();
        i.setSku("SK1");
        i.setShortDescription("Short desc");
        i.setFullDescription("A much fuller description of something");
        return i;
    }
}
