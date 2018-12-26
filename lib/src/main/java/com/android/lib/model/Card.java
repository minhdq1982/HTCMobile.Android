package com.android.lib.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.lib.model.response.AgenciesResponse;
import com.google.gson.annotations.Expose;

import java.util.List;

public class Card implements Parcelable {
    /**
     * membershipId : 0
     * cardNumber : 001291013
     * vinNo : 213123131af
     * rank : Silver
     * benefitTypeId : 1
     * pointsInThePeriod : 33
     * valueInThePeriod : 52
     * customerId : 0
     * customerName : 0
     * gender :
     * phone :
     * address :
     * type : 1
     * licensePlates : 29A95855
     * brand : Hyundai
     * model : I10
     * activeDate : 2018-11-02T00:00:00
     * rankExpiredDate : 2018-11-21T00:00:00
     * cardStatus : 1
     * description :
     * agencyId : 1
     * isActive : true
     * membershipStatus : 0
     * agency : {"code":"","name":"Huyndai Da Nang","cityId":1,"cityName":"Ha Noi","hotline":"0905555666","latitude":"","longitude":"","image":"","website":"","detail":"","phone":"","address":"da nang","display":"","isActive":true,"id":1}
     * benefits : [{"content":"Tích điểm khi sử dụng dịch vụ","detail":"Đại lý chọn tỷ lệ tích điểm sử dụng dịch vụ.\r\nVí dụ:  KH làm dv 1 triệu VNĐ: tích lũy số điểm tương đương 50 nghìn VNĐ để tiêu dùng cho các lần sau (tỷ lệ tích điểm: 5%).\r\n(*) Đại lý chọn tỷ lệ tích điểm khác nhau cho các hạng thẻ khác nhau","id":1},{"content":"Ưu tiên Tiếp nhận xe","detail":"KH chạy xe và Xưởng --> Hệ thống nhận diện qua máy tính --> thu ngân thông báo qua điện đàm cho điều phối viên --> điều phối đón tiếp xác nhận nhu cầu khách hàng và mời kh vào phòng vip --> chuyển thông tin cho cvdv xử lý.\r\nMức độ ưu tiên: ưu tiên so với các xe đang chờ, không ưu tiên trước các xe đang được xử lý tiếp nhận + báo giá","id":2}]
     * id : 4
     */

    private int membershipId;
    private String cardNo;
    private String vinNo;
    private String rank;
    private int benefitTypeId;
    private int pointsInThePeriod;
    private int spentValueInThePeriod;
    private int balance;
    private int valueInThePeriod;
    private int customerId;
    private String customerName;
    private String gender;
    private String phone;
    private String address;
    private String type;
    private String licensePlates;
    private String brand;
    private String model;
    private String activeDate;
    private String rankExpiredDate;
    private int cardStatus;
    private String description;
    private int agencyId;
    private boolean isActive;
    private int membershipStatus;
    private AgenciesResponse.Agency agency;
    private int id;
    private List<Benefit> benefits;
    private String membershipCode;

    public int getSpentValueInThePeriod() {
        return spentValueInThePeriod;
    }

    public void setSpentValueInThePeriod(int spentValueInThePeriod) {
        this.spentValueInThePeriod = spentValueInThePeriod;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public String getMembershipCode() {
        return membershipCode;
    }

    public void setMembershipCode(String membershipCode) {
        this.membershipCode = membershipCode;
    }

    @Expose
    private boolean showExpand = false;

    public boolean isShowExpand() {
        return showExpand;
    }

    public void setShowExpand(boolean showExpand) {
        this.showExpand = showExpand;
    }

    public int getMembershipId() {
        return membershipId;
    }

    public void setMembershipId(int membershipId) {
        this.membershipId = membershipId;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getVinNo() {
        return vinNo;
    }

    public void setVinNo(String vinNo) {
        this.vinNo = vinNo;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public int getBenefitTypeId() {
        return benefitTypeId;
    }

    public void setBenefitTypeId(int benefitTypeId) {
        this.benefitTypeId = benefitTypeId;
    }

    public int getPointsInThePeriod() {
        return pointsInThePeriod;
    }

    public void setPointsInThePeriod(int pointsInThePeriod) {
        this.pointsInThePeriod = pointsInThePeriod;
    }

    public int getValueInThePeriod() {
        return valueInThePeriod;
    }

    public void setValueInThePeriod(int valueInThePeriod) {
        this.valueInThePeriod = valueInThePeriod;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLicensePlates() {
        return licensePlates;
    }

    public void setLicensePlates(String licensePlates) {
        this.licensePlates = licensePlates;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getActiveDate() {
        return activeDate;
    }

    public void setActiveDate(String activeDate) {
        this.activeDate = activeDate;
    }

    public String getRankExpiredDate() {
        return rankExpiredDate;
    }

    public void setRankExpiredDate(String rankExpiredDate) {
        this.rankExpiredDate = rankExpiredDate;
    }

    public int getCardStatus() {
        return cardStatus;
    }

    public void setCardStatus(int cardStatus) {
        this.cardStatus = cardStatus;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(int agencyId) {
        this.agencyId = agencyId;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public int getMembershipStatus() {
        return membershipStatus;
    }

    public void setMembershipStatus(int membershipStatus) {
        this.membershipStatus = membershipStatus;
    }

    public AgenciesResponse.Agency getAgency() {
        return agency;
    }

    public void setAgency(AgenciesResponse.Agency agency) {
        this.agency = agency;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Benefit> getBenefits() {
        return benefits;
    }

    public void setBenefits(List<Benefit> benefits) {
        this.benefits = benefits;
    }

    public static class Benefit implements Parcelable {
        /**
         * content : Tích điểm khi sử dụng dịch vụ
         * detail : Đại lý chọn tỷ lệ tích điểm sử dụng dịch vụ.
         * Ví dụ:  KH làm dv 1 triệu VNĐ: tích lũy số điểm tương đương 50 nghìn VNĐ để tiêu dùng cho các lần sau (tỷ lệ tích điểm: 5%).
         * (*) Đại lý chọn tỷ lệ tích điểm khác nhau cho các hạng thẻ khác nhau
         * id : 1
         */

        private String content;
        private String detail;
        private int id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getDetail() {
            return detail;
        }

        public void setDetail(String detail) {
            this.detail = detail;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(this.content);
            dest.writeString(this.detail);
            dest.writeInt(this.id);
        }

        public Benefit() {
        }

        protected Benefit(Parcel in) {
            this.content = in.readString();
            this.detail = in.readString();
            this.id = in.readInt();
        }

        public static final Creator<Benefit> CREATOR = new Creator<Benefit>() {
            @Override
            public Benefit createFromParcel(Parcel source) {
                return new Benefit(source);
            }

            @Override
            public Benefit[] newArray(int size) {
                return new Benefit[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.membershipId);
        dest.writeString(this.cardNo);
        dest.writeString(this.vinNo);
        dest.writeString(this.rank);
        dest.writeInt(this.benefitTypeId);
        dest.writeInt(this.pointsInThePeriod);
        dest.writeInt(this.valueInThePeriod);
        dest.writeInt(this.customerId);
        dest.writeString(this.customerName);
        dest.writeString(this.gender);
        dest.writeString(this.phone);
        dest.writeString(this.address);
        dest.writeString(this.type);
        dest.writeString(this.licensePlates);
        dest.writeString(this.brand);
        dest.writeString(this.model);
        dest.writeString(this.activeDate);
        dest.writeString(this.rankExpiredDate);
        dest.writeInt(this.cardStatus);
        dest.writeString(this.description);
        dest.writeInt(this.agencyId);
        dest.writeByte(this.isActive ? (byte) 1 : (byte) 0);
        dest.writeInt(this.membershipStatus);
        dest.writeParcelable(this.agency, flags);
        dest.writeInt(this.id);
        dest.writeTypedList(this.benefits);
    }

    public Card() {
    }

    protected Card(Parcel in) {
        this.membershipId = in.readInt();
        this.cardNo = in.readString();
        this.vinNo = in.readString();
        this.rank = in.readString();
        this.benefitTypeId = in.readInt();
        this.pointsInThePeriod = in.readInt();
        this.valueInThePeriod = in.readInt();
        this.customerId = in.readInt();
        this.customerName = in.readString();
        this.gender = in.readString();
        this.phone = in.readString();
        this.address = in.readString();
        this.type = in.readString();
        this.licensePlates = in.readString();
        this.brand = in.readString();
        this.model = in.readString();
        this.activeDate = in.readString();
        this.rankExpiredDate = in.readString();
        this.cardStatus = in.readInt();
        this.description = in.readString();
        this.agencyId = in.readInt();
        this.isActive = in.readByte() != 0;
        this.membershipStatus = in.readInt();
        this.agency = in.readParcelable(AgenciesResponse.Agency.class.getClassLoader());
        this.id = in.readInt();
        this.benefits = in.createTypedArrayList(Benefit.CREATOR);
    }

    public static final Creator<Card> CREATOR = new Creator<Card>() {
        @Override
        public Card createFromParcel(Parcel source) {
            return new Card(source);
        }

        @Override
        public Card[] newArray(int size) {
            return new Card[size];
        }
    };
}
