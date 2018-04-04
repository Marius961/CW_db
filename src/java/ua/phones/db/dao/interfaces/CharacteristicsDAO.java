package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Characteristics;

public interface CharacteristicsDAO {

    Characteristics getCharacteristics(int id);

    Characteristics getCharacteristics(Characteristics characteristics);

    int insertCharacteristics(Characteristics characteristics);

    boolean deleteCharacteristics(int id);

    boolean updateCharacteristics(Characteristics characteristics);
}
