package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Display;

public interface DisplayDAO {

    Display getDisplay(int id);

    boolean insertDisplay(Display display);

    boolean deleteDisplay(int ind);

    boolean updateDisplay(Display display);
}
