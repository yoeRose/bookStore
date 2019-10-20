package itcast.yoe.bootkstore.domain;

public class OrderItem {

    //用order代替order_id
    private Order order;
    //用product代替product_id
    private Product product;

    private int buyNum;

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getBuyNum() {
        return buyNum;
    }

    public void setBuyNum(int buyNum) {
        this.buyNum = buyNum;
    }


    @Override
    public String toString() {
        return "OrderItem{" +
                ", order=" + order +
                ", product=" + product +
                ", buyNum=" + buyNum +
                '}';
    }
}
