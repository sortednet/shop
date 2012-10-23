package net.sorted.shop.service;

import java.util.List;

import net.sorted.shop.domain.BasketItem;
import net.sorted.shop.domain.ShopUser;
import net.sorted.shop.domain.StockItem;

import org.joda.time.DateTime;

public interface BasketService {
    void addItem(StockItem stockItem, ShopUser user, DateTime timeAdded, int number);
    void removeItem(StockItem stockItem, ShopUser user, int number);
    int numItems(ShopUser user);
    List<BasketItem> getItems(ShopUser user);
}
