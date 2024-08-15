package com.antipin;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class OrderDto implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private UUID uuid;

    private String user;

    private Date date;

    private List<String> items;

    private BigDecimal amount;

    public OrderDto(String user, Date date, List<String> items, BigDecimal amount, UUID uuid) {
        this.user = user;
        this.date = date;
        this.items = items;
        this.amount = amount;
        this.uuid = uuid;
    }

    public OrderDto() {
    }

    public String getUser() {
        return user;
    }

    public Date getDate() {
        return date;
    }

    public List<String> getItems() {
        return items;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public UUID getUuid() {
        return uuid;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setItems(List<String> items) {
        this.items = items;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "OrderDto{" +
                "amount=" + amount +
                ", items=" + items +
                ", date=" + date +
                ", user='" + user + '\'' +
                '}';
    }
}
