package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.ShopUser;

import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class ShopUserDaoImpl implements ShopUserDao {
    private final HibernateTemplate hibernateTemplate;
    
    public ShopUserDaoImpl(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    public ShopUser getById(long id) {
        return hibernateTemplate.get(ShopUser.class, id);
    }

    @Override
    public ShopUser getByName(String name) {
        @SuppressWarnings("unchecked")
        List<ShopUser> users = hibernateTemplate.find("from " + ShopUser.class.getName()+" where userName=? ", name);
        if (users.size() == 1) {
            return users.get(0);
        } else if (users.size() == 0) {
            return null;
        } else {
            throw new RuntimeException("More than one user found with username "+name);
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<ShopUser> getAll() {
        return hibernateTemplate.find("from " + ShopUser.class.getName());
    }

    @Override
    public void save(ShopUser item) {
        hibernateTemplate.saveOrUpdate(item);
    }

    @Override
    public void delete(ShopUser item) {
        hibernateTemplate.delete(item);
    }

}
