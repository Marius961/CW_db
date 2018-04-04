package ua.phones.db.dao.interfaces;

import ua.phones.db.models.Camera;

public interface CameraDAO {

    Camera getCamera(int id);

    Camera getCamera(Camera camera);

    int insertCamera(Camera camera);

    boolean deleteCamera(int id);

    boolean updateCamera(Camera camerac);
}
