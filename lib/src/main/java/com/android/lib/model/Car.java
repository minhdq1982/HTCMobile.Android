package com.android.lib.model;

import com.android.lib.model.response.CarsResponse;

import java.util.List;

/**
 * Created by ThinhNH on 12/12/2018.
 */
public class Car {
    /**
     * categoryId : 0
     * name : string
     * isActive : true
     * isDeleted : true
     * creationTime : 2018-11-16T07:35:47.139Z
     * lastModificationTime : 2018-11-16T07:35:47.139Z
     * deletionTime : 2018-11-16T07:35:47.139Z
     * creatorUserId : 0
     * lastModifierUserId : 0
     * deleterUserId : 0
     * slogan : string
     * shortDescription : string
     * highlights : string
     * exterior : string
     * furniture : string
     * operation : string
     * safe : string
     * convenient : string
     * catalog : string
     * images : ["string"]
     * version : [{"carId":0,"versionName":"string","price":0,"unit":"string","spec":[{"name":"string","details":[{"name":"string","value":"string"}]}],"id":0}]
     * id : 0
     */

    private int categoryId;
    private String name;
    private boolean isActive;
    private boolean isDeleted;
    private String creationTime;
    private String lastModificationTime;
    private String deletionTime;
    private int creatorUserId;
    private int lastModifierUserId;
    private int deleterUserId;
    private String slogan;
    private String shortDescription;
    private String highlights;
    private String exterior;
    private String furniture;
    private String operation;
    private String safe;
    private String convenient;
    private String catalog;
    private int id;
    private List<String> images;
    private List<Version> version;
    private boolean hasTestDrive;

    public boolean isHasTestDrive() {
        return hasTestDrive;
    }

    public void setHasTestDrive(boolean hasTestDrive) {
        this.hasTestDrive = hasTestDrive;
    }

    public Car(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public Car(String name, int id, List<Version> version) {
        this.name = name;
        this.id = id;
        this.version = version;
    }

    public Car(int categoryId, String name, String slogan, String shortDescription, int id, List<String> images, List<Car.Version> version) {
        this.categoryId = categoryId;
        this.name = name;
        this.slogan = slogan;
        this.shortDescription = shortDescription;
        this.id = id;
        this.images = images;
        this.version = version;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean isIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(boolean isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getCreationTime() {
        return creationTime;
    }

    public void setCreationTime(String creationTime) {
        this.creationTime = creationTime;
    }

    public String getLastModificationTime() {
        return lastModificationTime;
    }

    public void setLastModificationTime(String lastModificationTime) {
        this.lastModificationTime = lastModificationTime;
    }

    public String getDeletionTime() {
        return deletionTime;
    }

    public void setDeletionTime(String deletionTime) {
        this.deletionTime = deletionTime;
    }

    public int getCreatorUserId() {
        return creatorUserId;
    }

    public void setCreatorUserId(int creatorUserId) {
        this.creatorUserId = creatorUserId;
    }

    public int getLastModifierUserId() {
        return lastModifierUserId;
    }

    public void setLastModifierUserId(int lastModifierUserId) {
        this.lastModifierUserId = lastModifierUserId;
    }

    public int getDeleterUserId() {
        return deleterUserId;
    }

    public void setDeleterUserId(int deleterUserId) {
        this.deleterUserId = deleterUserId;
    }

    public String getSlogan() {
        return slogan;
    }

    public void setSlogan(String slogan) {
        this.slogan = slogan;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public String getHighlights() {
        return highlights;
    }

    public void setHighlights(String highlights) {
        this.highlights = highlights;
    }

    public String getExterior() {
        return exterior;
    }

    public void setExterior(String exterior) {
        this.exterior = exterior;
    }

    public String getFurniture() {
        return furniture;
    }

    public void setFurniture(String furniture) {
        this.furniture = furniture;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getSafe() {
        return safe;
    }

    public void setSafe(String safe) {
        this.safe = safe;
    }

    public String getConvenient() {
        return convenient;
    }

    public void setConvenient(String convenient) {
        this.convenient = convenient;
    }

    public String getCatalog() {
        return catalog;
    }

    public void setCatalog(String catalog) {
        this.catalog = catalog;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    public List<Version> getVersion() {
        return version;
    }

    public void setVersion(List<Version> version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return getName();
    }


    public static class Version {
        /**
         * carId : 0
         * versionName : string
         * price : 0
         * unit : string
         * spec : [{"name":"string","details":[{"name":"string","value":"string"}]}]
         * id : 0
         */

        private int carId;
        private String versionName;
        private int price;
        private String unit;
        private int id;
        private List<Spec> spec;
        private String code;
        private String status;

        public Version(String versionName, int id) {
            this.versionName = versionName;
            this.id = id;
        }

        public Version(String versionName) {
            this.versionName = versionName;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {
            return versionName;
        }

        public int getCarId() {
            return carId;
        }

        public void setCarId(int carId) {
            this.carId = carId;
        }

        public String getVersionName() {
            return versionName;
        }

        public void setVersionName(String versionName) {
            this.versionName = versionName;
        }

        public int getPrice() {
            return price;
        }

        public void setPrice(int price) {
            this.price = price;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public List<Spec> getSpec() {
            return spec;
        }

        public void setSpec(List<Spec> spec) {
            this.spec = spec;
        }

        public static class Spec {
            /**
             * name : string
             * details : [{"name":"string","value":"string"}]
             */

            private String name;
            private List<Detail> details;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public List<Detail> getDetails() {
                return details;
            }

            public void setDetails(List<Detail> details) {
                this.details = details;
            }

            public static class Detail {
                /**
                 * name : string
                 * value : string
                 */

                private String name;
                private String value;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getValue() {
                    return value;
                }

                public void setValue(String value) {
                    this.value = value;
                }
            }
        }
    }
}
