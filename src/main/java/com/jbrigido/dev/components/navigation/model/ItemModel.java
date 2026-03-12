package com.jbrigido.dev.components.navigation.model;

import javax.swing.*;

public class ItemModel {

    private String title;
    private Icon icon;
    private ItemType type;

    public ItemModel(String title, Icon icon, ItemType type) {
        this.title = title;
        this.icon = icon;
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }

    public enum ItemType {
        MENU, SUBMENU, SPACE
    }

}
