package me.mrgeneralq.qwiredpage.parsers;

import gearth.protocol.HPacket;

public class QGetCatalogPage {

    private final int pageId;
    private final int unkown;
    private final String catalogType;


    public QGetCatalogPage(HPacket packet) {
        this.pageId = packet.readInteger();
        this.unkown = packet.readInteger();
        this.catalogType = packet.readString();
    }


    public int getPageId() {
        return pageId;
    }

    public int getUnkown() {
        return unkown;
    }

    public String getCatalogType() {
        return catalogType;
    }
}
