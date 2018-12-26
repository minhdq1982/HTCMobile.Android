package com.android.lib.model.response;

import com.android.lib.model.Card;

import java.util.List;

public class CardListResponse extends BaseResponse {
    /**
     * data : [{"cardNumber":"004000889966","membershipId":0,"benefitTypeId":1,"benefitTypeName":"Silver","type":"1","customerId":0,"customerName":0,"customerGender":null,"customerPhone":null,"customerAddress":null,"carLicensePlate":"29A13256","carVINNo":"12345678","carBrand":"Hyundai","carModel":"Grand i10 1.2 AT (BA) 2018","activeDate":"2018-10-30T00:00:00","rankExpiredDate":"2019-10-30T00:00:00","pointsInThePeriod":0,"valueInThePeriod":0,"status":1,"description":null,"agencyId":1,"isActive":true,"membershipStatus":0,"agency":{"code":null,"name":"Huyndai Da Nang","cityId":1,"cityName":"Ha Noi","hotline":"0905555666","latitude":null,"longitude":null,"image":null,"website":null,"detail":null,"phone":null,"address":"da nang","display":null,"isActive":true,"id":1},"benefits":[{"content":"Tích điểm khi sử dụng dịch vụ","detail":"Đại lý chọn tỷ lệ tích điểm sử dụng dịch vụ.\r\nVí dụ:  KH làm dv 1 triệu VNĐ: tích lũy số điểm tương đương 50 nghìn VNĐ để tiêu dùng cho các lần sau (tỷ lệ tích điểm: 5%).\r\n(*) Đại lý chọn tỷ lệ tích điểm khác nhau cho các hạng thẻ khác nhau","id":1},{"content":"Ưu tiên Tiếp nhận xe","detail":"KH chạy xe và Xưởng --> Hệ thống nhận diện qua máy tính --> thu ngân thông báo qua điện đàm cho điều phối viên --> điều phối đón tiếp xác nhận nhu cầu khách hàng và mời kh vào phòng vip --> chuyển thông tin cho cvdv xử lý.\r\nMức độ ưu tiên: ưu tiên so với các xe đang chờ, không ưu tiên trước các xe đang được xử lý tiếp nhận + báo giá","id":2}],"id":1}]
     * status : null
     * message : null
     */

    private List<Card> data;

    public List<Card> getData() {
        return data;
    }

    @Override
    public String toString() {
        return data.toString();
    }

    public void setData(List<Card> data) {
        this.data = data;
    }
}
