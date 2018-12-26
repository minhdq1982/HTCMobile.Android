package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Create by Ngocji on 10/29/2018
 **/


public class WarrantyPolicyResponse extends BaseResponse implements Parcelable {

    private List<WarrantyPolicy> data;

    public List<WarrantyPolicy> getData() {
        return data;
    }

    public void setData(List<WarrantyPolicy> data) {
        this.data = data;
    }



    public static class WarrantyPolicy implements Parcelable {
        /**
         * id : 0
         * name : string
         * content : string
         * modifiedDate : 2018-11-09T02:24:34.120Z
         * createdDate : 2018-11-09T02:24:34.120Z
         */

        private int id;
        private String name;
        private String content;
        private String modifiedDate;
        private String createdDate;

        private boolean selected;

        public boolean isSelected() {
            return selected;
        }

        public void setSelected(boolean selected) {
            this.selected = selected;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getModifiedDate() {
            return modifiedDate;
        }

        public void setModifiedDate(String modifiedDate) {
            this.modifiedDate = modifiedDate;
        }

        public String getCreatedDate() {
            return createdDate;
        }

        public void setCreatedDate(String createdDate) {
            this.createdDate = createdDate;
        }


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeInt(this.id);
            dest.writeString(this.name);
            dest.writeString(this.content);
            dest.writeString(this.modifiedDate);
            dest.writeString(this.createdDate);
        }

        public WarrantyPolicy() {
        }

        protected WarrantyPolicy(Parcel in) {
            this.id = in.readInt();
            this.name = in.readString();
            this.content = in.readString();
            this.modifiedDate = in.readString();
            this.createdDate = in.readString();
        }

        public static final Creator<WarrantyPolicy> CREATOR = new Creator<WarrantyPolicy>() {
            @Override
            public WarrantyPolicy createFromParcel(Parcel source) {
                return new WarrantyPolicy(source);
            }

            @Override
            public WarrantyPolicy[] newArray(int size) {
                return new WarrantyPolicy[size];
            }
        };
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeTypedList(this.data);
    }

    public WarrantyPolicyResponse() {
    }

    protected WarrantyPolicyResponse(Parcel in) {
        this.data = in.createTypedArrayList(WarrantyPolicy.CREATOR);
    }

    public static final Creator<WarrantyPolicyResponse> CREATOR = new Creator<WarrantyPolicyResponse>() {
        @Override
        public WarrantyPolicyResponse createFromParcel(Parcel source) {
            return new WarrantyPolicyResponse(source);
        }

        @Override
        public WarrantyPolicyResponse[] newArray(int size) {
            return new WarrantyPolicyResponse[size];
        }
    };
}
