package stock.trading.order.model;

public class OrderSign {
    Sign sign;

    public OrderSign(Sign sign) {
        this.sign = sign;
    }

    public void setSign(Sign sign) {
        this.sign = sign;
    }

    public String getDisplaySign(){
        switch(sign) {
            case P:
                return "Cty CK Tự doanh"; // thanh vien
            case C:
                return "Cá nhân"; // luu ki tai thanh vien
            case M:
                return "Người/Tổ chức lưu kí khác";
            // nguoi, to chuc luu ki tai cac to chuc luu ki khong phai thanh vien
            case F:
                return "Nước ngoài";
            default:
                return "Trạng thái";
        }
    }


}
