package me.mrgeneralq.qwiredpage.services;

import me.mrgeneralq.qwiredpage.parsers.CatalogPageIndex;

public interface ICatalogIndexService {

    CatalogPageIndex findPageIndexByName(CatalogPageIndex root, String name);

}
