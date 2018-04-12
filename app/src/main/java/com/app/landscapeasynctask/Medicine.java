package com.app.landscapeasynctask;

import java.io.Serializable;

/**
 * Created by User1 on 12-04-2018.
 */

public class Medicine implements Serializable {

    String entity_id;
    String name;
    String price;
    String oldprice;

    public String getEntity_id() {
        return entity_id;
    }

    public void setEntity_id(String entity_id) {
        this.entity_id = entity_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }
}
