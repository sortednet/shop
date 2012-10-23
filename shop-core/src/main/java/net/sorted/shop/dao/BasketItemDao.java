package net.sorted.shop.dao;

import java.util.List;

import net.sorted.shop.domain.BasketItem;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;

public interface BasketItemDao {
    BasketItem getById(long id);
    BasketItem getByItemAndUser(StockItem item, ShopUser user);
    List<BasketItem> getForUser(ShopUser user);
    List<BasketItem> getAll();
    void save(BasketItem item);
    void delete(BasketItem item);
}
