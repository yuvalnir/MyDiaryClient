package model;

import Utilities.CostsManagerException;
import Utilities.Item;

import java.util.List;

public interface IItemDAO {
    void insertItem(Item item) throws CostsManagerException;
    Item getItem(int id) throws CostsManagerException;
    List<Item> getItems() throws CostsManagerException;

}
