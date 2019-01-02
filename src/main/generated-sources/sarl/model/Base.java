package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.util.UUID;
import model.UAVBody;
import model.Vector3D;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Base extends UAVBody {
  public Base(final UUID id) {
    super(id, 0);
    Vector3D _vector3D = new Vector3D(200, 0, 0);
    this.setPos(_vector3D);
  }
}
