package net.sorted.shop.dao;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import net.sorted.shop.domain.Slide;
import net.sorted.shop.domain.SlideShow;
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
public class SlideShowDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private SlideShowDao dao;
    
    
    @Autowired
    private SessionFactory sessionFactory;
    
    @Test
    public void testAddSlideShow() {
        SlideShow s = getDefaultSlideShow();       
        
        int before = dao.getAll().size();
        dao.save(s);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));
    }
    
    @Test
    public void testAddSlideShowWithSlides() {
        SlideShow s = getDefaultSlideShow();
        Slide slide1 = getDefaultStaticSlide();
        slide1.setTitle("Slide1");
        Slide slide2 = getDefaultStaticSlide();
        slide2.setTitle("Slide2");
        
        s.addSlide(slide1);
        s.addSlide(slide2);
        
        int before = dao.getAll().size();
        dao.save(s);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));   
        
        SlideShow ss = dao.getById(s.getSlideShowId());
        assertEquals(2, ss.getNumSlides());
        assertEquals("Slide1", ss.getSlide(0).getTitle());
        assertEquals("Slide2", ss.getSlide(1).getTitle());
    }
    
    @Test
    public void testGetById() {
        SlideShow i = getDefaultSlideShow();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        SlideShow j = dao.getById(i.getSlideShowId());
        assertEquals(i, j);
    }
    
    @Test
    public void testGetByIdFails() {
        SlideShow j = dao.getById(987654321);
        assertNull(j);
    }
    
    @Test
    public void testDelete() {
        SlideShow i = getDefaultSlideShow();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        SlideShow j = dao.getById(i.getSlideShowId());
        assertEquals(i, j);        
        dao.delete(j);
        sessionFactory.getCurrentSession().flush();
        
        j = dao.getById(i.getSlideShowId());
        assertNull(j);
    }
    
    private SlideShow getDefaultSlideShow() {
        SlideShow ss = new SlideShow();
        ss.setTitle("Test slide show");
        
        return ss;
    }
    
    private StaticSlide getDefaultStaticSlide() {
        StaticSlide ss = new StaticSlide();
        ss.setContent("Blah blah blah\nRubarb Rubarb Rubarb");
        ss.setLayout("flow");
        ss.setTitle("Test Static Slide");
        ss.setImageName("test.jpg");
        
        return ss;
    }
}
