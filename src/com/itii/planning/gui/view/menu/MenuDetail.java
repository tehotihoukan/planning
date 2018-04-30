package com.itii.planning.gui.view.menu;

/**
 * Give name, help context, mnemonic for the given menu
 * 
 * @author Sebastien
 *
 */
public enum MenuDetail
{
    CREATE("Créer"),
    EDIT("Editer"), 
    DONE("Marquer effectué"),
    DUPLICATE( "Dupliquer"),
    DELETE("Supprimer");

    String displayName;

    private MenuDetail(String displayName)
    {
        this.displayName = displayName;
        // TODO Auto-generated constructor stub
    }
    
    public String getDisplayName()
    {
        return displayName;
    }
}
