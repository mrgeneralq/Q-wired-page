package me.mrgeneralq.qwiredpage.services;

import me.mrgeneralq.qwiredpage.QWiredPage;

public class Bootstrapper {

    private ICatalogIndexService catalogIndexService;
    private static Bootstrapper instance;

    private Bootstrapper() {
    }

    public static Bootstrapper getInstance() {
        if(instance == null){
            instance = new Bootstrapper();
        }

        return new Bootstrapper();
    }

    public void initialize(QWiredPage qwiredPage){
        this.catalogIndexService = new CatalogIndexService();
    }

    public Bootstrapper(CatalogIndexService catalogIndexService) {
        this.catalogIndexService = catalogIndexService;
    }

    public ICatalogIndexService getCatalogIndexService() {
        return catalogIndexService;
    }
}
