package carsales.service;

import carsales.model.Announcement;
import carsales.model.Brand;

import java.util.List;

public interface Store {
    List<Announcement> getAnnouncementsByLastDay();

    List<Announcement> getAnnouncementsWithPhoto();

    List<Announcement> getAnnouncementsByBrand(Brand brandName);

}
