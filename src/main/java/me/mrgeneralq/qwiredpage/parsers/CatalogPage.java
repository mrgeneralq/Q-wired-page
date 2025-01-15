package me.mrgeneralq.qwiredpage.parsers;
import gearth.extensions.parsers.catalog.HFrontPageItem;
import gearth.extensions.parsers.catalog.HOffer;
import gearth.protocol.HMessage;
import gearth.protocol.HPacket;

import java.util.ArrayList;
import java.util.List;

public class CatalogPage {

    private int pageId;
    private String catalogType;
    private String layoutCode;
    private String[] images;
    private String[] texts;
    private List<HOffer> offers = new ArrayList();
    private int offerId;
    private boolean acceptSeasonCurrencyAsCredits;
    private List<HFrontPageItem> frontPageItems = new ArrayList();



    public CatalogPage(HPacket packet) {
        this.pageId = packet.readInteger();
        this.catalogType = packet.readString();
        this.layoutCode = packet.readString();
        this.images = new String[packet.readInteger()];

        int offerCount;
        for(offerCount = 0; offerCount < this.images.length; ++offerCount) {
            this.images[offerCount] = packet.readString();
        }

        this.texts = new String[packet.readInteger()];

        for(offerCount = 0; offerCount < this.texts.length; ++offerCount) {
            this.texts[offerCount] = packet.readString();
        }

        offerCount = packet.readInteger();

        int frontPageItemsCount;
        for(frontPageItemsCount = 0; frontPageItemsCount < offerCount; ++frontPageItemsCount) {
       //     this.offers.add(new HOffer(packet));
        }

        this.offerId = packet.readInteger();
        this.acceptSeasonCurrencyAsCredits = packet.readBoolean();
        if (packet.getReadIndex() < packet.getBytesLength()) {
            frontPageItemsCount = packet.readInteger();

            for(int i = 0; i < frontPageItemsCount; ++i) {
            //    this.frontPageItems.add(new HFrontPageItem(packet));
            }
        }

    }

    public int getPageId() {
        return pageId;
    }

    public void setPageId(int pageId) {
        this.pageId = pageId;
    }

    public String getCatalogType() {
        return catalogType;
    }

    public void setCatalogType(String catalogType) {
        this.catalogType = catalogType;
    }

    public String getLayoutCode() {
        return layoutCode;
    }

    public void setLayoutCode(String layoutCode) {
        this.layoutCode = layoutCode;
    }

    public String[] getImages() {
        return images;
    }

    public void setImages(String[] images) {
        this.images = images;
    }

    public String[] getTexts() {
        return texts;
    }

    public void setTexts(String[] texts) {
        this.texts = texts;
    }

    public List<HOffer> getOffers() {
        return offers;
    }

    public void setOffers(List<HOffer> offers) {
        this.offers = offers;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public boolean isAcceptSeasonCurrencyAsCredits() {
        return acceptSeasonCurrencyAsCredits;
    }

    public void setAcceptSeasonCurrencyAsCredits(boolean acceptSeasonCurrencyAsCredits) {
        this.acceptSeasonCurrencyAsCredits = acceptSeasonCurrencyAsCredits;
    }

    public List<HFrontPageItem> getFrontPageItems() {
        return frontPageItems;
    }

    public void setFrontPageItems(List<HFrontPageItem> frontPageItems) {
        this.frontPageItems = frontPageItems;
    }


    public HPacket toPacket(){

        HPacket packet = new HPacket("CatalogPage", HMessage.Direction.TOCLIENT);
        packet.appendInt(this.pageId);
        packet.appendString(this.catalogType);
        packet.appendString(this.layoutCode);
        packet.appendInt(this.images.length);
        for(String image : this.images){
            packet.appendString(image);
        }
        packet.appendInt(this.texts.length);
        for(String text : this.texts){
            packet.appendString(text);
        }
        packet.appendInt(this.offers.size());
        for(HOffer offer : this.offers){

            /*
            packet.appendInt(offer.getOfferId());
            packet.appendString(offer.get());
            packet.appendString(offer.getFurniType
             */
        }

        return packet;

    }

    public CatalogPage(int pageId, String catalogType, String layoutCode, int offerId, boolean acceptSeasonCurrencyAsCredits) {
        this.pageId = pageId;
        this.catalogType = catalogType;
        this.layoutCode = layoutCode;
        this.images = new String[0];
        this.texts = new String[0];
        this.offers = new ArrayList<>();
        this.offerId = offerId;
        this.acceptSeasonCurrencyAsCredits = acceptSeasonCurrencyAsCredits;
        this.frontPageItems = new ArrayList<>();
    }
}
