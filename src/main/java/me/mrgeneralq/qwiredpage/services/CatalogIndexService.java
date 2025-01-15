package me.mrgeneralq.qwiredpage.services;

import me.mrgeneralq.qwiredpage.parsers.CatalogPageIndex;

public class CatalogIndexService implements ICatalogIndexService {

    public CatalogPageIndex findPageIndexByName(CatalogPageIndex root, String name) {
        // Recursive function to find the page by name in the children of the root
        for (CatalogPageIndex child : root.getChildren()) {
            if (child.getPageName().equals(name)) {
                return child; // Found the page, return it
            }
            CatalogPageIndex result = findPageIndexByName(child, name); // Search recursively
            if (result != null) {
                return result; // Found in a deeper level, return the result
            }
        }
        return null; // Return null if not found
    }


}
