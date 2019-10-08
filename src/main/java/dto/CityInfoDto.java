package dto;

import entities.Address;
import entities.CityInfo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ryge
 */
public class CityInfoDto {

    private int id;
    private int zipcode;
    private String city;
    private List<Address> address;
    private List<CityInfoDto> all;

    public CityInfoDto() {
    }
    
    public CityInfoDto(CityInfo cityInfo) {
        this.id = cityInfo.getId();
        this.zipcode = cityInfo.getZipcode();
        this.city = cityInfo.getCity();
        this.address = cityInfo.getAddresss();
    }

    public CityInfoDto(int zipcode, String city) {
        this.zipcode = zipcode;
        this.city = city;
    }

    public CityInfoDto(List<CityInfo> cI) {
        all = new ArrayList();
        cI.forEach((cityInfo) -> {
            all.add(new CityInfoDto(cityInfo));
        });
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getZipcode() {
        return zipcode;
    }

    public void setZipcode(int zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public List<Address> getAddress() {
        return address;
    }

    public void setAddress(List<Address> address) {
        this.address = address;
    }

    public List<CityInfoDto> getAll() {
        return all;
    }

    public void setAll(List<CityInfoDto> all) {
        this.all = all;
    }

}
