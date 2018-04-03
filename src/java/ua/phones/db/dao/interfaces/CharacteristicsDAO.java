package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Characteristics;

public interface CharacteristicsDAO {

    Characteristics getCharacteristics(int id);

    boolean insertCharacteristics(Characteristics characteristics);

    boolean deleteCharacteristics(int id);

    boolean updateCharacteristics(Characteristics characteristics);
}
