package net.sorted.shop.dao;

import static junit.framework.Assert.assertEquals;
import net.sorted.shop.domain.StaticSlide;

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
public class SlideDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private SlideDao dao;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testAddSlide() {
        StaticSlide i = getDefaultStaticSlide();       
        i.setSlideNumber(0);
        
        int before = dao.getAll().size();
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));
    }
    
//    @Test
//    public void testGetById() {
//        StockItem i = getDefaultStaticSlide();        
//        dao.save(i);
//        sessionFactory.getCurrentSession().flush();
//        
//        StockItem j = dao.getById(i.getStockItemId());
//        assertEquals(i, j);
//    }
//    
//    @Test
//    public void testGetByIdFails() {
//        StockItem j = dao.getById(987654321);
//        assertNull(j);
//    }
//    
//    @Test
//    public void testDelete() {
//        StockItem i = getDefaultStaticSlide();        
//        dao.save(i);
//        sessionFactory.getCurrentSession().flush();
//        
//        StockItem j = dao.getById(i.getStockItemId());
//        assertEquals(i, j);        
//        dao.delete(j);
//        sessionFactory.getCurrentSession().flush();
//        
//        j = dao.getById(i.getStockItemId());
//        assertNull(j);
//    }
    
    private StaticSlide getDefaultStaticSlide() {
        StaticSlide ss = new StaticSlide();
        ss.setContent("Blah blah blah\nRubarb Rubarb Rubarb");
        ss.setLayout("flow");
        ss.setTitle("Test Static Slide");
        ss.setImageName("test.jpg");
        
        return ss;
    }
}
