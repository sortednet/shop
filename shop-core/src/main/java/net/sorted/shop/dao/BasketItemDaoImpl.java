package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.BasketItem;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.orm.hibernate3.HibernateTemplate;

public class BasketItemDaoImpl implements BasketItemDao {
    
    private static final Logger log = LoggerFactory.getLogger(BasketItemDaoImpl.class);
    
    private final HibernateTemplate hibernateTemplate;
    
    public BasketItemDaoImpl(SessionFactory sessionFactory) {
        hibernateTemplate = new HibernateTemplate(sessionFactory);
    }
    
    @Override
    public BasketItem getById(long id) {
        return hibernateTemplate.get(BasketItem.class, id);
    }


    @SuppressWarnings("unchecked")
    @Override
    public BasketItem getByItemAndUser(StockItem item, ShopUser user) {
        List<BasketItem> items =  hibernateTemplate.find("from " + BasketItem.class.getName()+
                                      " as item where item.stockItem.id = '"+item.getStockItemId()+"' and item.shopUser.id='"+user.getShopUserId()+"'");
        
        if (items.size() == 1) {
            return items.get(0);
        } else if (items.size() == 0) {
            return null;
        } else {
            throw new RuntimeException("More than 1 basket item ("+items.size()+" found) for the same stock item ("+item+") and user ("+user+")");
        }
    }

    
    @SuppressWarnings("unchecked")
    @Override
    public List<BasketItem> getAll() {
        return hibernateTemplate.find("from " + BasketItem.class.getName());
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<BasketItem> getForUser(ShopUser user) {
        if (user == null) {
            log.info("getForUser called with a null user");
            return null;
        }
        
        return hibernateTemplate.find("from " + BasketItem.class.getName()+
                                                         " as item where item.shopUser.id='"+user.getShopUserId()+"'");
    }

    @Override
    public void save(BasketItem item) {
        hibernateTemplate.saveOrUpdate(item);
    }

    @Override
    public void delete(BasketItem item) {
        hibernateTemplate.delete(item);
    }

}
