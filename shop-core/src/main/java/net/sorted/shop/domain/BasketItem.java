package net.sorted.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

@Entity
@Table(name="basket_item",
    uniqueConstraints= { @UniqueConstraint (columnNames= { "stock_item_id", "shop_user_id" } ) }
        )
public class BasketItem {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="basket_item_seq")
    @SequenceGenerator(name="basket_item_seq", sequenceName="basket_item_seq", allocationSize=100)
    @Column(name = "basket_item_id")
    private long basketItemId;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="stock_item_id", nullable=false)
    private StockItem stockItem;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="shop_user_id", nullable=false)    
    private ShopUser shopUser;
    
    @Type(type = "org.joda.time.contrib.hibernate.PersistentDateTime")
    @Column(name="added")
    private DateTime addedToBasket;
    
    @Column(name="quantity")
    private int quantity;

    public long getBasketItemId() {
        return basketItemId;
    }

    public void setBasketItemId(long basketItemId) {
        this.basketItemId = basketItemId;
    }

    public StockItem getStockItem() {
        return stockItem;
    }

    public void setStockItem(StockItem stockItem) {
        this.stockItem = stockItem;
    }

    public ShopUser getShopUser() {
        return shopUser;
    }

    public void setShopUser(ShopUser shopUser) {
        this.shopUser = shopUser;
    }

    public DateTime getAddedToBasket() {
        return addedToBasket;
    }

    public void setAddedToBasket(DateTime addedToBasket) {
        this.addedToBasket = addedToBasket;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
}
