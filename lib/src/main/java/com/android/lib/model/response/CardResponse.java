package com.android.lib.model.response;

import com.android.lib.model.Card;

public class CardResponse extends BaseResponse {

    /**
     * data : {"membershipId":0,"cardNo":"001291013","vinNo":"213123131af","rank":"Silver","benefitTypeId":1,"pointsInThePeriod":33,"valueInThePeriod":52,"customerId":0,"customerName":0,"gender":null,"phone":null,"address":null,"type":"1","licensePlates":"29A95855","brand":"Hyundai","model":"I10","activeDate":"2018-11-02T00:00:00","rankExpiredDate":"2018-11-21T00:00:00","cardStatus":1,"description":null,"agencyId":1,"isActive":true,"membershipStatus":0,"agency":{"code":"VN1","name":"Huyndai Da Nang","cityId":1,"cityName":"Ha Noi","hotline":"0905555666","latitude":21.002937,"longitude":105.7818701,"image":null,"website":null,"detail":null,"phone":null,"address":"Da nang","display":null,"isActive":true,"id":1},"benefits":[{"content":"Tích điểm khi sử dụng dịch vụ","detail":"Đại lý chọn tỷ lệ tích điểm sử dụng dịch vụ.\r\nVí dụ:  KH làm dv 1 triệu VNĐ: tích lũy số điểm tương đương 50 nghìn VNĐ để tiêu dùng cho các lần sau (tỷ lệ tích điểm: 5%).\r\n(*) Đại lý chọn tỷ lệ tích điểm khác nhau cho các hạng thẻ khác nhau","id":1},{"content":"Đưa/đón KH","detail":"Đưa KH: Khi xe KH đang ở XDV, ĐL hỗ trợ đưa KH đi đến nơi cần đi.\r\nĐón KH: ĐL đón KH đang ở bên ngoài về XDV để nhận xe\r\n(*) ĐL có thể xem xét để đưa ra các giới hạn ví dụ như phạm vi nội thành hoặc giới hạn về km\u2026","id":4},{"content":"Nhắn tin, tặng hoa/quà sinh nhật","detail":"Ưu đãi dành cho dịp sinh nhật.\r\nĐL có thể cân nhắc lựa chọn các loại hình quà. Có thể áp dụng và việc tặng điểm tiêu dùng (tương đương với giá trị tiền mà ĐL mong muốn tặng cho KH)","id":5},{"content":"Tham dự các events, tiệc mà ĐL tổ chức","detail":null,"id":6},{"content":"Kiểm tra GDS","detail":null,"id":7},{"content":"Miễn phí rửa xe nội/ngoại thất khi không sử dụng dịch vụ","detail":"Ưu đãi áp dụng cho Kh ngay cả khi Kh không có nhu cầu sử dụng các dịch vụ mà chỉ đơn thuần tới XDV để rửa xe","id":8},{"content":"Tặng điểm tiêu dùng khi mua thêm xe","detail":"tặng điểm tiêu dùng (tương đương với giá trị tiền mà ĐL mong muốn tặng cho KH) khi mua thêm xe để tri ân KH","id":9},{"content":"Tặng điểm tiêu dùng khi giới thiệu xe","detail":"tặng điểm tiêu dùng (tương đương với giá trị tiền mà ĐL mong muốn tặng cho KH) khi mua thêm xe để tri ân KH","id":10}],"id":4}
     * status : null
     * code : 200
     * message : null
     */

    private Card data;

    public Card getData() {
        return data;
    }

    public void setData(Card data) {
        this.data = data;
    }

}
