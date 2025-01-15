package me.mrgeneralq.qwiredpage.services;

import me.mrgeneralq.qwiredpage.parsers.QCatalogPageIndex;

public interface ICatalogIndexService {

    QCatalogPageIndex findPageIndexByName(QCatalogPageIndex root, String name);

}
