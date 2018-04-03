package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Processor;

public interface ProcessorDAO {

    Processor getProcessor(int id);

    boolean insertProcessor(Processor processor);

    boolean deleteProcessor(int id);

    boolean updateProcessor(Processor processor);
}
