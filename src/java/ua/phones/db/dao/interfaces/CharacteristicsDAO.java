package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Characteristics;

public interface CharacteristicsDAO {

    Characteristics getCharacteristics(int id);

    Characteristics getCharacteristics(Characteristics characteristics);

    int insertCharacteristics(Characteristics characteristics);

    void deleteCharacteristics(int id);

    void updateCharacteristics(Characteristics characteristics);

}
