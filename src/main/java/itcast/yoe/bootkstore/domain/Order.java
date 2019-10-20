package itcast.yoe.bootkstore.domain;

import java.util.Date;
import java.util.List;

public class Order {
    private String id;//order的id
    private double money;
    private String receiverAddress;
    private String receiverName;
    private String receiverPhone;
    private int payState;
    private Date orderTime;
    private User user;//如果有外键关系，一般设计为一个对象,这里用user代替user_id外键

    //添加orderItem的list列表
    private List<OrderItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public String getReceiverAddress() {
        return receiverAddress;
    }

    public void setReceiverAddress(String receiverAddress) {
        this.receiverAddress = receiverAddress;
    }

    public String getReceiverName() {
        return receiverName;
    }

    public void setReceiverName(String receiverName) {
        this.receiverName = receiverName;
    }

    public String getReceiverPhone() {
        return receiverPhone;
    }

    public void setReceiverPhone(String receiverPhone) {
        this.receiverPhone = receiverPhone;
    }

    public int getPayState() {
        return payState;
    }

    public void setPayState(int psyState) {
        this.payState = psyState;
    }


    public Date getOrderTime() {
        return orderTime;
    }

    public void setOrderTime(Date orderTime) {
        this.orderTime = orderTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<OrderItem> getItems() {
        return items;
    }

    public void setItems(List<OrderItem> list) {
        this.items = list;
    }


    @Override
    public String toString() {
        return "Order{" +
                "id='" + id + '\'' +
                ", money=" + money +
                ", receiverAddress='" + receiverAddress + '\'' +
                ", receiverName='" + receiverName + '\'' +
                ", receiverPhone='" + receiverPhone + '\'' +
                ", payState=" + payState +
                ", orderTime=" + orderTime +
                ", user=" + user +
                ", items=" + items +
                '}';
    }
}
