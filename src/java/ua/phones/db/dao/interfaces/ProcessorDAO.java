package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Processor;

public interface ProcessorDAO {

    Processor getProcessor(int id);

    Processor getProcessor(Processor processor);

    int insertProcessor(Processor processor);

    void deleteProcessor(int id);

    void updateProcessor(Processor processor);
}
