package stock.trading.Order.model;

enum Status {
    CHO_KHOP, DA_HUY, DA_KHOP, HET_HIEU_LUC,
    DA_DAT, DA_SUA, KHOP_HET, HOAN_TAT
}


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
