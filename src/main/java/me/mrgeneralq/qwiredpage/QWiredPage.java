package me.mrgeneralq.qwiredpage;


import gearth.extensions.Extension;
import gearth.extensions.ExtensionInfo;
import gearth.protocol.HMessage;
import gearth.protocol.HPacket;
import me.mrgeneralq.qwiredpage.parsers.CatalogIndex;
import me.mrgeneralq.qwiredpage.parsers.CatalogPage;
import me.mrgeneralq.qwiredpage.parsers.CatalogPageIndex;
import me.mrgeneralq.qwiredpage.services.Bootstrapper;
import me.mrgeneralq.qwiredpage.services.ICatalogIndexService;


@ExtensionInfo(
        Title =  "Q-Wired-Page",
        Description =  "Remove the additional variables tab from the wired furni page",
        Version =  "1.0",
        Author =  "MrGeneralQ"
)
public class QWiredPage extends Extension {

    private Bootstrapper bootstrapper;

    public QWiredPage(String[] args) {
        super(args);
    }

    public static void main(String[] args) {
        new QWiredPage(args).run();
    }

    @Override
    protected void initExtension() {

        //set up the bootstrapper
        this.bootstrapper = Bootstrapper.getInstance();
        bootstrapper.initialize(this);
        intercept(HMessage.Direction.TOCLIENT, "CatalogIndex" ,this::onCatalogIndex);

        /*
        for future use (not ready)
        intercept(HMessage.Direction.TOCLIENT, "CatalogPage" ,this::onCatalogPage);
        intercept(HMessage.Direction.TOSERVER, "GetCatalogPage" ,this::onGetCatalogPage);
        */
    }

    private void onCatalogIndex(HMessage hMessage) {
        CatalogIndex qCatalogIndex = new CatalogIndex(hMessage.getPacket());
        CatalogPageIndex root = qCatalogIndex.getRoot();

        if(!qCatalogIndex.getCatalogType().equals("BUILDERS_CLUB")) {
            return;
        }

        hMessage.setBlocked(true);
        ICatalogIndexService catalogIndexService = bootstrapper.getCatalogIndexService();
        CatalogPageIndex wiredPageIndex = catalogIndexService.findPageIndexByName(root, "wired2");
        CatalogPageIndex variablePageIndex = catalogIndexService.findPageIndexByName(root, "wired_variables_parent");

        for(CatalogPageIndex variableCategory : variablePageIndex.getChildren()){
            wiredPageIndex.getChildren().add(variableCategory);
        }

        wiredPageIndex.getChildren().removeIf(child -> child.getPageName().equals("wired_variables_parent"));
        HPacket newPacket = qCatalogIndex.toPacket();
        sendToClient(newPacket);
    }

    private void onCatalogPage(HMessage hMessage) {

        HPacket packet = hMessage.getPacket();
        CatalogPage catalogPage = new CatalogPage(packet);

        if(catalogPage.getPageId() != 1002){
            return;
        }
    }
}