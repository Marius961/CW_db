package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Camera;

public interface CameraDAO {

    Camera getCamera(int id);

    Camera getCamera(Camera camera);

    int insertCamera(Camera camera);

    void deleteCamera(int id);

    void updateCamera(Camera camerac);
}
