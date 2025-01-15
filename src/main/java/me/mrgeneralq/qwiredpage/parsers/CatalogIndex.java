package me.mrgeneralq.qwiredpage.parsers;

import gearth.protocol.HMessage;
import gearth.protocol.HPacket;

public class CatalogIndex {
    private final CatalogPageIndex root;
    private final boolean newAdditionsAvailable;
    private String catalogType;

    public CatalogIndex(HPacket packet) {
        this.root = new CatalogPageIndex(packet);
        this.newAdditionsAvailable = packet.readBoolean();
        this.catalogType = packet.readString();
    }

    public CatalogPageIndex getRoot() {
        return root;
    }

    public boolean isNewAdditionsAvailable() {
        return newAdditionsAvailable;
    }

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }


    public HPacket toPacket() {

        HPacket packet = new HPacket("CatalogIndex", HMessage.Direction.TOCLIENT);
        CatalogPageIndex qCatalogPageIndex = this.getRoot();

        HPacket newPacket = qCatalogPageIndex.appendToPacket(packet);
        newPacket.appendBoolean(this.isNewAdditionsAvailable());
        newPacket.appendString(this.getCatalogType());

        return newPacket;

    }



}
