package net.sorted.shop.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name="stock_item")
public class StockItem {

    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="stock_item_seq")
    @SequenceGenerator(name="stock_item_seq", sequenceName="stock_item_seq", allocationSize=100)
    @Column(name = "stock_item_id")
    private long id;
    
    @Column(name="sku")
    private String sku;
    
    @Column(name="short_description")
    private String shortDescription;
    
    @Column(name="full_description")
    private String fullDescription;
    

    public long getStockItemId() {
        return id;
    }

    public void setStockItemId(long id) {
        this.id = id;
    }

    
    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    
    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    
    public String getFullDescription() {
        return fullDescription;
    }

    public void setFullDescription(String fullDescription) {
        this.fullDescription = fullDescription;
    }

    @Override
    public String toString() {
        return "StockItem [id=" + id + ", sku=" + sku + ", shortDescription=" + shortDescription + ", fullDescription=" + fullDescription + "]";
    }

}
