package me.mrgeneralq.qwiredpage.parsers;

import gearth.extensions.parsers.catalog.HCatalogIndex;
import gearth.protocol.HMessage;
import gearth.protocol.HPacket;

public class QCatalogIndex {
    private final QCatalogPageIndex root;
    private final boolean newAdditionsAvailable;
    private String catalogType;

    public QCatalogIndex(HPacket packet) {
        this.root = new QCatalogPageIndex(packet);
        this.newAdditionsAvailable = packet.readBoolean();
        this.catalogType = packet.readString();
    }

    public QCatalogPageIndex getRoot() {
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
        QCatalogPageIndex qCatalogPageIndex = this.getRoot();

        HPacket newPacket = qCatalogPageIndex.appendToPacket(packet);
        newPacket.appendBoolean(this.isNewAdditionsAvailable());
        newPacket.appendString(this.getCatalogType());

        return newPacket;

    }



}
