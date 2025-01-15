package me.mrgeneralq.qwiredpage.parsers;

import gearth.protocol.HPacket;

import java.util.ArrayList;
import java.util.List;

public class CatalogPageIndex {

    private boolean visible;
    private int icon;
    private int pageId;
    private String pageName;
    private String localization;
    private List<Integer> offerIds = new ArrayList();
    private List<CatalogPageIndex> children = new ArrayList();


    public CatalogPageIndex(HPacket packet) {
        this.visible = packet.readBoolean();
        this.icon = packet.readInteger();
        this.pageId = packet.readInteger();
        this.pageName = packet.readString();
        this.localization = packet.readString();
        int offerCount = packet.readInteger();

        int childCount;
        for(childCount = 0; childCount < offerCount; ++childCount) {
            this.offerIds.add(packet.readInteger());
        }

        childCount = packet.readInteger();

        for(int i = 0; i < childCount; ++i) {
            this.children.add(new CatalogPageIndex(packet));
        }
    }

    public CatalogPageIndex(boolean visible, int icon, int pageId, String pageName, String localization) {
        this.visible = visible;
        this.icon = icon;
        this.pageId = pageId;
        this.pageName = pageName;
        this.localization = localization;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    public String getLocalization() {
        return localization;
    }

    public void setLocalization(String localization) {
        this.localization = localization;
    }

    public List<Integer> getOfferIds() {
        return offerIds;
    }

    public void setOfferIds(List<Integer> offerIds) {
        this.offerIds = offerIds;
    }

    public List<CatalogPageIndex> getChildren() {
        return children;
    }

    public void setChildren(List<CatalogPageIndex> children) {
        this.children = children;
    }

    public HPacket appendToPacket(HPacket packet) {

        packet.appendBoolean(this.isVisible());
        packet.appendInt(this.getIcon());
        packet.appendInt(this.getPageId());
        packet.appendString(this.getPageName());
        packet.appendString(this.getLocalization());
        packet.appendInt(this.getOfferIds().size());

        for(int offerId : this.getOfferIds()) {
            packet.appendInt(offerId);
        }

        packet.appendInt(this.getChildren().size());
        for(CatalogPageIndex child : this.getChildren()) {
            child.appendToPacket(packet);
        }

        return packet;
    }
}
