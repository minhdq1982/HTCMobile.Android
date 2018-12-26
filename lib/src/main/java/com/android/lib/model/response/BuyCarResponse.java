package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class BuyCarResponse extends BaseResponse implements Parcelable {

    /**
     * Id : 1
     * CategoryId : 1
     * Name : GRAND I10 SEDAN
     * Sologan : NHẤN NÚT KHỞI ĐỘNG CUỘC SỐNG MỚI
     * ShortDescription : - Thiết kế hiện đại, thể thao
     - Nội thất cao cấp, sang trọng và phong cách
     * Images : ["http://domainname/images/GRAND_I10_SEDAN.jpg","http://domainname/images/GRAND_I10_SEDAN_1.jpg"]
     * Price : 500000000
     * Unit : VNĐ
     */

    private int Id;
    private int CategoryId;
    private String Name;
    private String Sologan;
    private String ShortDescription;
    private int Price;
    private String Unit;
    private List<String> Images;

    public BuyCarResponse(String name) {
        Name = name;
    }

    public int getId() {
        return Id;
    }

    public void setId(int Id) {
        this.Id = Id;
    }

    public int getCategoryId() {
        return CategoryId;
    }

    public void setCategoryId(int CategoryId) {
        this.CategoryId = CategoryId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getSologan() {
        return Sologan;
    }

    public void setSologan(String Sologan) {
        this.Sologan = Sologan;
    }

    public String getShortDescription() {
        return ShortDescription;
    }

    public void setShortDescription(String ShortDescription) {
        this.ShortDescription = ShortDescription;
    }

    public int getPrice() {
        return Price;
    }

    public void setPrice(int Price) {
        this.Price = Price;
    }

    public String getUnit() {
        return Unit;
    }

    public void setUnit(String Unit) {
        this.Unit = Unit;
    }

    public List<String> getImages() {
        return Images;
    }

    public void setImages(List<String> Images) {
        this.Images = Images;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.Id);
        dest.writeInt(this.CategoryId);
        dest.writeString(this.Name);
        dest.writeString(this.Sologan);
        dest.writeString(this.ShortDescription);
        dest.writeInt(this.Price);
        dest.writeString(this.Unit);
        dest.writeStringList(this.Images);
    }

    public BuyCarResponse() {
    }

    protected BuyCarResponse(Parcel in) {
        this.Id = in.readInt();
        this.CategoryId = in.readInt();
        this.Name = in.readString();
        this.Sologan = in.readString();
        this.ShortDescription = in.readString();
        this.Price = in.readInt();
        this.Unit = in.readString();
        this.Images = in.createStringArrayList();
    }

    public static final Creator<BuyCarResponse> CREATOR = new Creator<BuyCarResponse>() {
        @Override
        public BuyCarResponse createFromParcel(Parcel source) {
            return new BuyCarResponse(source);
        }

        @Override
        public BuyCarResponse[] newArray(int size) {
            return new BuyCarResponse[size];
        }
    };
    @Override
    public String toString() {
        return Name ;
    }
}
