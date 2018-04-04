package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Display;

public interface DisplayDAO {

    Display getDisplay(int id);

    Display getDisplay(Display display);

    int insertDisplay(Display display);

    boolean deleteDisplay(int ind);

    boolean updateDisplay(Display display);
}
