package net.sorted.shop.dao;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNull;
import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;

import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.SiteRole;

import org.hibernate.SessionFactory;
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
@TransactionConfiguration(transactionManager = "transactionManager", defaultRollback = false)
public class ShopUserDaoTest extends AbstractTransactionalJUnit4SpringContextTests {
    
    @Autowired
    private ShopUserDao dao;
    
    @Autowired
    private SessionFactory sessionFactory;
    
    private SiteRole siteRole1;
    private SiteRole siteRole2;
    
    private List<SiteRole> allRoles;
    
    @Before
    public void setup() {
        siteRole1 = new SiteRole();
        siteRole1.setId(Long.valueOf(1));
        siteRole1.setName("ROLE_USER");
        
        siteRole2 = new SiteRole();
        siteRole2.setId(Long.valueOf(2));
        siteRole2.setName("ROLE_ADMIN");
        
        sessionFactory.getCurrentSession().save(siteRole1);
        sessionFactory.getCurrentSession().save(siteRole2);
        sessionFactory.getCurrentSession().flush();
        
        allRoles = new ArrayList<SiteRole>();
        allRoles.add(siteRole1);
        allRoles.add(siteRole2);
    }
    
    @Test
    public void testAddUser() {
        ShopUser i = getDefaultShopUser();       
        
        int before = dao.getAll().size();
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));
    }
    
    @Test
    public void testAddUserAndRole() {
        ShopUser i = getDefaultShopUser();       
        i.setSiteRoles(allRoles);
        
        int before = dao.getAll().size();
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        int after = dao.getAll().size();
        assertEquals(1, (after-before));
        
        ShopUser j = dao.getById(i.getShopUserId());
        assertNotNull(j);
        assertNotNull(j.getSiteRoles());
        assertEquals(2, j.getSiteRoles().size());
    }
    
    @Test
    public void testGetById() {
        ShopUser i = getDefaultShopUser();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        ShopUser j = dao.getById(i.getShopUserId());
        assertEquals(i, j);
    }
    
    @Test
    public void testGetByName() {
        ShopUser i = getDefaultShopUser();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        ShopUser j = dao.getByName("TestUser");
        assertEquals(i, j);
    }
    
    @Test
    public void testGetByIdFails() {
        ShopUser j = dao.getById(987654321);
        assertNull(j);
    }
    
    @Test
    public void testDelete() {
        ShopUser i = getDefaultShopUser();        
        dao.save(i);
        sessionFactory.getCurrentSession().flush();
        
        ShopUser j = dao.getById(i.getShopUserId());
        assertEquals(i, j);        
        dao.delete(j);
        sessionFactory.getCurrentSession().flush();
        
        j = dao.getById(i.getShopUserId());
        assertNull(j);
    }
    
    private ShopUser getDefaultShopUser() {
        ShopUser u = new ShopUser();
        u.setUserName("TestUser");
        u.setPassword("password");

        return u;
    }
}
