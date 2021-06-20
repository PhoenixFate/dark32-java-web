package com.phoenix.item.dto;

import com.phoenix.item.pojo.Item;
import com.phoenix.item.pojo.ItemDescription;

public class ItemDto {

    private Item item;

    private String[] images;

    private ItemDescription itemDescription;

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public ItemDescription getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(ItemDescription itemDescription) {
        this.itemDescription = itemDescription;
    }
}
