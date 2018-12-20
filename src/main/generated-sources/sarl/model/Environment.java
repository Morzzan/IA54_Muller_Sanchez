package model;

import com.google.common.base.Objects;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.util.GeometricShapeFactory;
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
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Base base = new Base();
  
  private Polygon zone;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final HashMap<UUID, UAVBody> uavs = CollectionLiterals.<UUID, UAVBody>newHashMap();
  
  private final Random rd = new Random();
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final HashMap<UUID, Survivor> survivors = CollectionLiterals.<UUID, Survivor>newHashMap();
  
  public Environment() {
    GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    Coordinate _coordinate = new Coordinate(0, 0);
    shapeFactory.setCentre(_coordinate);
    shapeFactory.setNumPoints(128);
    shapeFactory.setSize(200);
    this.zone = shapeFactory.createCircle();
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
  
  public UAVBody makeUAVBody(final UUID id, final int nb) {
    final UAVBody bdy = new UAVBody(id, nb);
    this.uavs.put(id, bdy);
    return bdy;
  }
  
  public Survivor makeSurvivorBody(final UUID id, final int nb) {
    Vector3D _randomPositionInZone = this.getRandomPositionInZone();
    final Survivor bdy = new Survivor(id, nb, _randomPositionInZone);
    this.survivors.put(id, bdy);
    return bdy;
  }
  
  @Pure
  public ArrayList<Vector3D> getNeighbors(final UAVBody uav) {
    ArrayList<Vector3D> around = CollectionLiterals.<Vector3D>newArrayList();
    Collection<UAVBody> _values = this.uavs.values();
    for (final UAVBody n : _values) {
      if (((n.getPos().add(uav.getPos().times((-1))).norm() < uav.getPerceptRadius()) && (!Objects.equal(n, uav)))) {
        around.add(n.getPos());
      }
    }
    return around;
  }
  
  @Pure
  public ArrayList<UUID> getSeenUAVs(final Survivor me) {
    ArrayList<UUID> around = CollectionLiterals.<UUID>newArrayList();
    Collection<UAVBody> _values = this.uavs.values();
    for (final UAVBody n : _values) {
      double _norm = n.getPos().add(me.getPos().times((-1))).norm();
      int _perceptRadius = me.getPerceptRadius();
      boolean _lessThan = (_norm < _perceptRadius);
      if (_lessThan) {
        around.add(n.getId());
      }
    }
    return around;
  }
  
  @Pure
  public Vector3D getRandomPositionInZone() {
    Vector3D _randomDirection = Vector3D.randomDirection();
    double _nextDouble = this.rd.nextDouble();
    double _multiply = (_nextDouble * 100);
    return _randomDirection.times(_multiply);
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
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    return result;
  }
  
  @Pure
  public Base getBase() {
    return this.base;
  }
  
  @Pure
  public HashMap<UUID, UAVBody> getUavs() {
    return this.uavs;
  }
  
  @Pure
  public HashMap<UUID, Survivor> getSurvivors() {
    return this.survivors;
  }
}
