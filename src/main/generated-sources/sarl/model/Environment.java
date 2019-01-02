package model;

import com.google.common.base.Objects;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Random;
import java.util.UUID;
import model.Base;
import model.Survivor;
import model.UAVBody;
import model.Utils;
import model.Vector3D;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Environment {
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PUBLIC_SETTER })
  private Base base;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Polygon zone;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final HashMap<UUID, UAVBody> uavs = CollectionLiterals.<UUID, UAVBody>newHashMap();
  
  private final Random rd = new Random();
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private UUID id;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final HashMap<UUID, Survivor> survivors = CollectionLiterals.<UUID, Survivor>newHashMap();
  
  public Environment(final UUID id) {
    Coordinate _coordinate = new Coordinate(0, 0);
    this.zone = Utils.createCircle(200, _coordinate);
    this.id = id;
  }
  
  @Pure
  public boolean isOnZone(final UAVBody uav) {
    boolean _xblockexpression = false;
    {
      GeometryFactory factory = new GeometryFactory();
      double _x = uav.getPos().getX();
      double _y = uav.getPos().getY();
      Coordinate _coordinate = new Coordinate(_x, _y);
      _xblockexpression = this.zone.intersects(factory.createPoint(_coordinate));
    }
    return _xblockexpression;
  }
  
  public synchronized UAVBody makeUAVBody(final UUID id, final int nb) {
    final UAVBody bdy = new UAVBody(id, nb);
    this.uavs.put(id, bdy);
    return bdy;
  }
  
  public synchronized Survivor makeSurvivorBody(final UUID id, final int nb) {
    Vector3D _randomPositionInZone = Utils.getRandomPositionInZone(this.zone);
    final Survivor bdy = new Survivor(id, nb, _randomPositionInZone);
    this.survivors.put(id, bdy);
    return bdy;
  }
  
  @Pure
  public ArrayList<UAVBody> getNeighbors(final UAVBody uav) {
    ArrayList<UAVBody> around = CollectionLiterals.<UAVBody>newArrayList();
    Collection<UAVBody> _values = this.uavs.values();
    for (final UAVBody n : _values) {
      if (((n.getPos().substract(uav.getPos()).norm() < Utils.perceptRadius) && (!Objects.equal(n, uav)))) {
        around.add(n);
      }
    }
    double _norm = this.base.getPos().substract(uav.getPos()).norm();
    boolean _lessThan = (_norm < Utils.perceptRadius);
    if (_lessThan) {
      around.add(this.base);
    }
    return around;
  }
  
  @Pure
  public ArrayList<UUID> getSeenUAVs(final Survivor me) {
    ArrayList<UUID> around = CollectionLiterals.<UUID>newArrayList();
    Collection<UAVBody> _values = this.uavs.values();
    for (final UAVBody n : _values) {
      double _norm = n.getPos().add(me.getPos().times((-1))).norm();
      boolean _lessThan = (_norm < Utils.perceptRadius);
      if (_lessThan) {
        around.add(n.getId());
      }
    }
    return around;
  }
  
  public void saveSurvivor(final UUID survivorID) {
    Survivor survivor = this.survivors.get(survivorID);
    survivor.setRescued(true);
  }
  
  @Pure
  public int getNbRescuedSurvivors() {
    int nb = 0;
    Collection<Survivor> _values = this.survivors.values();
    for (final Survivor survivor : _values) {
      boolean _isRescued = survivor.isRescued();
      if (_isRescued) {
        nb++;
      }
    }
    return nb;
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    Environment other = (Environment) obj;
    if (!java.util.Objects.equals(this.id, other.id)) {
      return false;
    }
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + java.util.Objects.hashCode(this.id);
    return result;
  }
  
  @Pure
  public Base getBase() {
    return this.base;
  }
  
  public void setBase(final Base base) {
    this.base = base;
  }
  
  @Pure
  public Polygon getZone() {
    return this.zone;
  }
  
  @Pure
  public HashMap<UUID, UAVBody> getUavs() {
    return this.uavs;
  }
  
  @Pure
  public UUID getId() {
    return this.id;
  }
  
  @Pure
  public HashMap<UUID, Survivor> getSurvivors() {
    return this.survivors;
  }
}
