package base;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import java.util.UUID;
import tools.Vector3D;
import uav.UAVBody;

/**
 * @author morzzan
 * The base is the interface for the UAVS to communicate with the outside world
 * In this case, all information found about the survivors must transit through the base
 * The base would then call for further first-aiders if needed
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
  
  public UAVBody makeUAVBody(final UUID id, final int nb) {
    UAVBody uav = new UAVBody(id, nb);
    uav.setPos(this.getPos());
    return uav;
  }
}
