package net.sorted.shop.service;

import java.util.List;

import net.sorted.shop.dao.BasketItemDao;
import net.sorted.shop.domain.BasketItem;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;

import org.joda.time.DateTime;

public class BasketServiceImpl implements BasketService {

    private final BasketItemDao basketItemDao;
    
    public BasketServiceImpl(BasketItemDao basketItemDao) {
        this.basketItemDao = basketItemDao;
    }

    @Override
    public void addItem(StockItem stockItem, ShopUser user, DateTime timeAdded, int number) {
        
        BasketItem b = basketItemDao.getByItemAndUser(stockItem, user);
        if (b != null) {
            b.setQuantity(b.getQuantity() + number);
        } else {
            b = new BasketItem();
            b.setStockItem(stockItem);
            b.setShopUser(user);
            b.setQuantity(number);
            b.setAddedToBasket(timeAdded);
        } 
        basketItemDao.save(b);
        
    }


    @Override
    public void removeItem(StockItem stockItem, ShopUser user, int number) {
        BasketItem existing = basketItemDao.getByItemAndUser(stockItem, user);
        if (existing != null) {
            int newQty = existing.getQuantity() - number;
            if (newQty <= 0) {
                basketItemDao.delete(existing);
            } else {
                existing.setQuantity(newQty);
                basketItemDao.save(existing);
            }
        }
    }

    @Override
    public List<BasketItem> getItems(ShopUser user) {
        return basketItemDao.getForUser(user);
    }


    @Override
    public int numItems(ShopUser user) {
        int n = 0;
        List<BasketItem> items = getItems(user);
        for (BasketItem basketItem : items) {
            n = n +basketItem.getQuantity();
        }
        
        return n;
    }
}
