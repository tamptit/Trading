package stock.trading.order.model;


public class OrderStatus {

    Status status;
    public OrderStatus(Status status) {
        this.status = status;
    }

    public String getDisplayStatus(){
        switch(status) {
            case CHO_KHOP:
                return "Chờ khớp";
            case DA_HUY:
                return "Đã hủy";
            case HOAN_TAT:
                return "Hoàn tất";
            default:
                return "Trạng thái";
        }
    }


}
