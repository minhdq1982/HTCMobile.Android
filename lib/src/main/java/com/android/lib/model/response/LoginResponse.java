package com.android.lib.model.response;

import android.os.Parcel;
import android.os.Parcelable;

import com.android.lib.model.Card;

import java.util.List;

/**
 * Created by ThinhNH on 3/27/2018.
 */

public class LoginResponse extends BaseResponse {

    /**
     * data : {"accessToken":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjEiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiQTRVM1dIQzI3SjVEMlozNEc0WElWR1Y2M0JCU1NTUzMiLCJzdWIiOiIxIiwianRpIjoiM2FmNjlhMTctNzBjMC00ZmUwLWFiYTctYzUyZjc2OTE5NjlhIiwiaWF0IjoxNTQwOTY5MDU3LCJuYmYiOjE1NDA5NjkwNTcsImV4cCI6MTU0MTA1NTQ1NywiaXNzIjoiSFRDTW9iaWxlIiwiYXVkIjoiSFRDTW9iaWxlIn0.eaH3wT7nk7szX0ncGbacPGVgLicuEdE6iL2yE8KvYbI","encryptedAccessToken":"wNYmO41/48SHNstaLVXxHCCre29BZQl1NhC6NM3R3rzpXtPQxVzH6jEzA/QhXFN5tu6Fk7pO53uppm1mVXMZgxbyRVz26dnepi/FyB6axBY+6gq1GL+uRQgoiFUCjRN2p8w6LevViwKlHyWZZJZO1DGVSjAi1m2U+og9pkHw9/QR4Nl/DPnoP9JYDMpZ1zxx09u6s0GZ9/Q5Sjk+L0UfcSCbl38X8he5w9UIn/Hvxh7ysM1CiPLsoOwtbiieSRVmrmt0JjnipAn4/K283F8GrGwzwgehWsqefmUnM0ckMwNfOaJqxA4FdQOAkJd5W+XxnY47L5koHpOiYrY+0ae2XyEiQFo8RYxhAcoddusorkEKX61Y7Yb6h28Z7vbXRleHx0dmOFr17f8NIkS6qGHi4gdzjcXycq4CPvYT+OdorABJ1g6VqgWdTSoJ+fHpOZsHSgj0CYwddB+LkfWNWIjWW9g/yEcI+0qQWPXMB/98k1yK3em5Qad7q+0iJtASbTKU5ghtUXUiwzszbatXMEXuC5cqPMJrBZ2ycpkS4yueXt/m5oxY6PzmxN78e1bhYu/R3BmOWM2RVtIpqpq0qH4wTfACqp6Po7T5X3w5xHsqCtqTyNwofzvlQkqbCye3NXynYqOPqKRrgzcG3G2ohj74TlpUF/2ah6VwgFHjGiqPcZqyn2xAV/2T9lmmJGU0t6lRyWpPuvzkl9ixDLc9dkdK/7XXxziH0Ihi0P9if/ZCcH2wQjFvHtf1QaOVqsFhEV7/8oCquRGpisMQGLD0v3p4PV/Wt7xYVOZvEzvYoNHb9t/99Cxjtn0/8V/FHhNLSZbcjGHssa8svpSExoj1r8k4/QBwKujmQ4w5myW6z4y2GKc=","expireInSeconds":86400,"userId":1,"name":"admin","surname":"admin","avatar":"images\\test1.jpg","phone":"+84969996278","birthday":"0001-01-01T00:00:00","emailAddress":"admin@aspnetboilerplate.com","address":"","identityCard":"","isActive":true,"desctiption":null,"cars":[{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":11},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":12},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":13},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":14},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":15},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"VIN132464","licensePlate":"29A2256","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":16},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"VIN132464","licensePlate":"29A2256","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":17}],"cards":[{"membershipId":"1","cardNo":"004000889966","rank":"Silver","customerId":1,"customerName":"admin admin","gender":null,"phone":"+84969996278","address":"","licensePlates":"29A13256","vinNo":"12345678","brand":"Hyundai","model":"Grand i10 1.2 AT (BA) 2018","activeDate":"2018-10-30T00:00:00","rankExpiredDate":"2019-10-30T00:00:00","valueInThePeriod":0,"pointsInThePeriod":0,"cardStatus":1,"description":null,"membershipStatus":true,"id":1}]}
     * message : null
     */

    private Data data;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public static class Data {
        /**
         * accessToken : eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1laWRlbnRpZmllciI6IjEiLCJodHRwOi8vc2NoZW1hcy54bWxzb2FwLm9yZy93cy8yMDA1LzA1L2lkZW50aXR5L2NsYWltcy9uYW1lIjoiYWRtaW4iLCJodHRwOi8vc2NoZW1hcy5taWNyb3NvZnQuY29tL3dzLzIwMDgvMDYvaWRlbnRpdHkvY2xhaW1zL3JvbGUiOiJBZG1pbiIsIkFzcE5ldC5JZGVudGl0eS5TZWN1cml0eVN0YW1wIjoiQTRVM1dIQzI3SjVEMlozNEc0WElWR1Y2M0JCU1NTUzMiLCJzdWIiOiIxIiwianRpIjoiM2FmNjlhMTctNzBjMC00ZmUwLWFiYTctYzUyZjc2OTE5NjlhIiwiaWF0IjoxNTQwOTY5MDU3LCJuYmYiOjE1NDA5NjkwNTcsImV4cCI6MTU0MTA1NTQ1NywiaXNzIjoiSFRDTW9iaWxlIiwiYXVkIjoiSFRDTW9iaWxlIn0.eaH3wT7nk7szX0ncGbacPGVgLicuEdE6iL2yE8KvYbI
         * encryptedAccessToken : wNYmO41/48SHNstaLVXxHCCre29BZQl1NhC6NM3R3rzpXtPQxVzH6jEzA/QhXFN5tu6Fk7pO53uppm1mVXMZgxbyRVz26dnepi/FyB6axBY+6gq1GL+uRQgoiFUCjRN2p8w6LevViwKlHyWZZJZO1DGVSjAi1m2U+og9pkHw9/QR4Nl/DPnoP9JYDMpZ1zxx09u6s0GZ9/Q5Sjk+L0UfcSCbl38X8he5w9UIn/Hvxh7ysM1CiPLsoOwtbiieSRVmrmt0JjnipAn4/K283F8GrGwzwgehWsqefmUnM0ckMwNfOaJqxA4FdQOAkJd5W+XxnY47L5koHpOiYrY+0ae2XyEiQFo8RYxhAcoddusorkEKX61Y7Yb6h28Z7vbXRleHx0dmOFr17f8NIkS6qGHi4gdzjcXycq4CPvYT+OdorABJ1g6VqgWdTSoJ+fHpOZsHSgj0CYwddB+LkfWNWIjWW9g/yEcI+0qQWPXMB/98k1yK3em5Qad7q+0iJtASbTKU5ghtUXUiwzszbatXMEXuC5cqPMJrBZ2ycpkS4yueXt/m5oxY6PzmxN78e1bhYu/R3BmOWM2RVtIpqpq0qH4wTfACqp6Po7T5X3w5xHsqCtqTyNwofzvlQkqbCye3NXynYqOPqKRrgzcG3G2ohj74TlpUF/2ah6VwgFHjGiqPcZqyn2xAV/2T9lmmJGU0t6lRyWpPuvzkl9ixDLc9dkdK/7XXxziH0Ihi0P9if/ZCcH2wQjFvHtf1QaOVqsFhEV7/8oCquRGpisMQGLD0v3p4PV/Wt7xYVOZvEzvYoNHb9t/99Cxjtn0/8V/FHhNLSZbcjGHssa8svpSExoj1r8k4/QBwKujmQ4w5myW6z4y2GKc=
         * expireInSeconds : 86400
         * userId : 1
         * name : admin
         * surname : admin
         * avatar : images\test1.jpg
         * phone : +84969996278
         * birthday : 0001-01-01T00:00:00
         * emailAddress : admin@aspnetboilerplate.com
         * address :
         * identityCard :
         * isActive : true
         * desctiption : null
         * cars : [{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":11},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":12},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":13},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":14},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"abc","licensePlate":"avc","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":15},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"VIN132464","licensePlate":"29A2256","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":16},{"carId":1,"carName":"Grand i10 1.2 AT (BA) 2018","vinNo":"VIN132464","licensePlate":"29A2256","agencyId":1,"agencyName":"Huyndai Da Nang","purchaseDate":"2018-10-19T01:47:41.187","id":17}]
         * cards : [{"membershipId":"1","cardNo":"004000889966","rank":"Silver","customerId":1,"customerName":"admin admin","gender":null,"phone":"+84969996278","address":"","licensePlates":"29A13256","vinNo":"12345678","brand":"Hyundai","model":"Grand i10 1.2 AT (BA) 2018","activeDate":"2018-10-30T00:00:00","rankExpiredDate":"2019-10-30T00:00:00","valueInThePeriod":0,"pointsInThePeriod":0,"cardStatus":1,"description":null,"membershipStatus":true,"id":1}]
         * unreadNotification: 0
         */

        private String accessToken;
        private String encryptedAccessToken;
        private int expireInSeconds;
        private int id;
        private String name;
        private String surname;
        private String avatar;
        private String phoneNumber;
        private String birthday;
        private String emailAddress;
        private String address;
        private String identityCard;
        private boolean isActive;
        private String desctiption;
        private List<Car> cars;
        private List<Card> cards;
        private boolean isProfileFull;
        private boolean isNotification;
        private boolean isNewsfeed;
        private int unreadNotification;

        public int getUnreadNotification() {
            return unreadNotification;
        }

        public void setUnreadNotification(int unreadNotification) {
            this.unreadNotification = unreadNotification;
        }

        public boolean isNotification() {
            return isNotification;
        }

        public void setNotification(boolean notification) {
            isNotification = notification;
        }

        public boolean isNewsfeed() {
            return isNewsfeed;
        }

        public void setNewsfeed(boolean newsfeed) {
            isNewsfeed = newsfeed;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }

        public String getEncryptedAccessToken() {
            return encryptedAccessToken;
        }

        public void setEncryptedAccessToken(String encryptedAccessToken) {
            this.encryptedAccessToken = encryptedAccessToken;
        }

        public int getExpireInSeconds() {
            return expireInSeconds;
        }

        public void setExpireInSeconds(int expireInSeconds) {
            this.expireInSeconds = expireInSeconds;
        }

        public int getId() {
            return id;
        }

        public void setId(int userId) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getSurname() {
            return surname;
        }

        public void setSurname(String surname) {
            this.surname = surname;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phone) {
            this.phoneNumber = phone;
        }

        public String getBirthday() {
            return birthday;
        }

        public void setBirthday(String birthday) {
            this.birthday = birthday;
        }

        public String getEmailAddress() {
            return emailAddress;
        }

        public void setEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getIdentityCard() {
            return identityCard;
        }

        public void setIdentityCard(String identityCard) {
            this.identityCard = identityCard;
        }

        public boolean isIsActive() {
            return isActive;
        }

        public void setIsActive(boolean isActive) {
            this.isActive = isActive;
        }

        public String getDesctiption() {
            return desctiption;
        }

        public void setDesctiption(String desctiption) {
            this.desctiption = desctiption;
        }

        public List<Car> getCars() {
            return cars;
        }

        public void setCars(List<Car> cars) {
            this.cars = cars;
        }

        public List<Card> getCards() {
            return cards;
        }

        public void setCards(List<Card> cards) {
            this.cards = cards;
        }

        public boolean isProfileFull() {
            return isProfileFull;
        }

        public void setProfileFull(boolean profileFull) {
            isProfileFull = profileFull;
        }

        public static class Car implements Parcelable {
            /**
             * carId : 1
             * carName : Grand i10 1.2 AT (BA) 2018
             * vinNo : abc
             * licensePlate : avc
             * agencyId : 1
             * agencyName : Huyndai Da Nang
             * purchaseDate : 2018-10-19T01:47:41.187
             * id : 11
             */

            private int carId;
            private String carName;
            private String vinNo;
            private String licensePlate;
            private int agencyId;
            private String agencyName;
            private String purchaseDate;
            private String carImage;
            private int id;

            public Car(int id, int carID, String carName, String VINNo, String licensePlate, int agencyId, String agencyName, String purchaseDate, String carImage) {
                this.id = id;
                this.carId = carID;
                this.carName = carName;
                this.vinNo = VINNo;
                this.licensePlate = licensePlate;
                this.agencyId = agencyId;
                this.agencyName = agencyName;
                this.purchaseDate = purchaseDate;
                this.carImage = carImage;
            }

            public Car(int carId, String carName, String licensePlate) {
                this.carId = carId;
                this.carName = carName;
                this.licensePlate = licensePlate;
            }

            public String getCarImage() {
                return carImage;
            }

            public void setCarImage(String carImage) {
                this.carImage = carImage;
            }

            public int getCarID() {
                return carId;
            }

            public void setCarID(int carId) {
                this.carId = carId;
            }

            public String getCarName() {
                return carName;
            }

            public void setCarName(String carName) {
                this.carName = carName;
            }

            public String getVINNo() {
                return vinNo;
            }

            public void setVINNo(String vinNo) {
                this.vinNo = vinNo;
            }

            public String getLicensePlate() {
                return licensePlate;
            }

            public void setLicensePlate(String licensePlate) {
                this.licensePlate = licensePlate;
            }

            public int getAgencyId() {
                return agencyId;
            }

            public void setAgencyId(int agencyId) {
                this.agencyId = agencyId;
            }

            public String getAgencyName() {
                return agencyName;
            }

            public void setAgencyName(String agencyName) {
                this.agencyName = agencyName;
            }

            public String getPurchaseDate() {
                return purchaseDate;
            }

            public void setPurchaseDate(String purchaseDate) {
                this.purchaseDate = purchaseDate;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            @Override
            public String toString() {
                return carName + " - " + licensePlate;
            }

            @Override
            public int describeContents() {
                return 0;
            }

            @Override
            public void writeToParcel(Parcel dest, int flags) {
                dest.writeInt(this.carId);
                dest.writeString(this.carName);
                dest.writeString(this.vinNo);
                dest.writeString(this.licensePlate);
                dest.writeInt(this.agencyId);
                dest.writeString(this.agencyName);
                dest.writeString(this.purchaseDate);
                dest.writeString(this.carImage);
                dest.writeInt(this.id);
            }

            protected Car(Parcel in) {
                this.carId = in.readInt();
                this.carName = in.readString();
                this.vinNo = in.readString();
                this.licensePlate = in.readString();
                this.agencyId = in.readInt();
                this.agencyName = in.readString();
                this.purchaseDate = in.readString();
                this.carImage = in.readString();
                this.id = in.readInt();
            }

            public static final Creator<Car> CREATOR = new Creator<Car>() {
                @Override
                public Car createFromParcel(Parcel source) {
                    return new Car(source);
                }

                @Override
                public Car[] newArray(int size) {
                    return new Car[size];
                }
            };
        }

    }
}
