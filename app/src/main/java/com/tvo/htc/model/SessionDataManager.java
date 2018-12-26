package com.tvo.htc.model;

import com.android.lib.model.Card;
import com.android.lib.model.News;
import com.android.lib.model.response.AgenciesResponse.Agency;
import com.android.lib.model.response.AppointmentResponse;
import com.android.lib.model.Car;
import com.android.lib.model.response.CarCategoryResponse;
import com.android.lib.model.response.CarVersionNameResponse;
import com.android.lib.model.response.CityResponse.City;
import com.android.lib.model.response.GroupNews;
import com.android.lib.model.response.GuildBookItemDetailResponse;
import com.android.lib.model.response.MaintenanceLevelResponse.MaintenanceLevel;
import com.android.lib.model.response.SelectCarResponse;
import com.android.lib.model.response.SettingResponse;
import com.android.lib.model.response.TechnicalGuideResponse;

import java.util.HashMap;
import java.util.List;

import static com.tvo.htc.util.AppConstants.START_OFFSET;

public class SessionDataManager {
    private static final SessionDataManager ourInstance = new SessionDataManager();

    public static SessionDataManager getInstance() {
        return ourInstance;
    }

    private SessionDataManager() {
    }

    //Data of list select car
    private List<Card> cards;

    public List<Card> getCards() {
        return cards;
    }

    public void setCards(List<Card> cards) {
        this.cards = cards;
    }

    //Data of list city
    private List<City> cities;

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }

    //Data of agency
    private List<Agency> agencies;

    public List<Agency> getAgencies() {
        return agencies;
    }

    public void setAgencies(List<Agency> agencies) {
        this.agencies = agencies;
    }

    //Data of car
    private List<Car> cars;

    public List<Car> getCars() {
        return cars;
    }

    public void setCars(List<Car> cars) {
        this.cars = cars;
    }

    //Data of appointment
    private List<AppointmentResponse.Data> appoinments;
    private int appointmentOffSet = START_OFFSET;

    public void setAppointmentOffSet(int appointmentOffSet) {
        this.appointmentOffSet = appointmentOffSet;
    }

    public int getAppointmentOffSet() {
        return appointmentOffSet;
    }

    public List<AppointmentResponse.Data> getAppoinments() {
        return appoinments;
    }

    public void setAppoinments(List<AppointmentResponse.Data> appoinments) {
        this.appoinments = appoinments;
    }

    //Data of News Item
    public List<News> items;
    private int itemOffSet = START_OFFSET;

    public int getItemOffSet() {
        return itemOffSet;
    }

    public void setItemOffSet(int itemOffSet) {
        this.itemOffSet = itemOffSet;
    }

    //Data of MaintenanceLevel
    private List<MaintenanceLevel> maintenanceLevels;

    public List<MaintenanceLevel> getMaintenanceLevels() {
        return maintenanceLevels;
    }

    public void setMaintenanceLevels(List<MaintenanceLevel> maintenanceLevels) {
        this.maintenanceLevels = maintenanceLevels;
    }

    //Data of technical guide
    private List<TechnicalGuideResponse.Item> technicalList;
    private int technicalOffSet = -1;

    public List<TechnicalGuideResponse.Item> getTechnicalList() {
        return technicalList;
    }

    public void setTechnicalList(List<TechnicalGuideResponse.Item> technicalList) {
        this.technicalList = technicalList;
    }

    public int getTechnicalOffSet() {
        return technicalOffSet;
    }

    public void setTechnicalOffSet(int technicalOffSet) {
        this.technicalOffSet = technicalOffSet;
    }

    //data of guide book
    private HashMap<Integer, List<GuildBookItemDetailResponse.Item>> mapGuideBooks = new HashMap<>();

    public void setGuideBookItem(int id, List<GuildBookItemDetailResponse.Item> list) {
        mapGuideBooks.put(id, list);
    }

    public List<GuildBookItemDetailResponse.Item> getGuideBookItem(int id) {
        if (mapGuideBooks.containsKey(id)) {
            return mapGuideBooks.get(id);
        }
        return null;
    }

    //data of recommend select car
    private List<SelectCarResponse.Item> listSelectCar;

    public List<SelectCarResponse.Item> getListSelectCar() {
        return listSelectCar;
    }

    public void setListSelectCar(List<SelectCarResponse.Item> listSelectCar) {
        this.listSelectCar = listSelectCar;
    }

    private List<CarCategoryResponse.Category> listCategory;

    public List<CarCategoryResponse.Category> getListCategory() {
        return listCategory;
    }

    public void setListCategory(List<CarCategoryResponse.Category> listCategory) {
        this.listCategory = listCategory;
    }

    //data of setting
    private SettingResponse.Setting setting;

    public SettingResponse.Setting getSetting() {
        return setting;
    }

    public void setSetting(SettingResponse.Setting setting) {
        this.setting = setting;
    }

    //data of car version name
    private List<CarVersionNameResponse.CarVersion> carVersion;

    public List<CarVersionNameResponse.CarVersion> getCarVersion() {
        return carVersion;
    }

    public void setCarVersion(List<CarVersionNameResponse.CarVersion> carVersion) {
        this.carVersion = carVersion;
    }

    //HOme new home
    private List<GroupNews> groupNews;
    private boolean hasFirstLoadDataHome;

    public boolean isHasFirstLoadDataHome() {
        return hasFirstLoadDataHome;
    }

    public void setHasFirstLoadDataHome(boolean hasFirstLoadDataHome) {
        this.hasFirstLoadDataHome = hasFirstLoadDataHome;
    }

    public List<GroupNews> getGroupNews() {
        return groupNews;
    }

    public void setGroupNews(List<GroupNews> groupNews) {
        this.groupNews = groupNews;
    }
}
