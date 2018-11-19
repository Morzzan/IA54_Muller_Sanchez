package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import model.Vector3D;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class UAVBody {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private final int perceptRadius = 50;
  
  private final int maxSpeed = 3;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private UUID id;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Vector3D pos = new Vector3D(0, 0, 0);
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private Vector3D speed = new Vector3D(0, 0, 0);
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private int nb;
  
  public UAVBody(final UUID id, final int nb) {
    this.id = id;
    this.nb = nb;
  }
  
  public Vector3D accelerate(final Vector3D a) {
    Vector3D _xblockexpression = null;
    {
      Vector3D v = this.speed.add(a);
      Vector3D _xifexpression = null;
      double _norm = v.norm();
      boolean _lessThan = (_norm < this.maxSpeed);
      if (_lessThan) {
        _xifexpression = this.speed = v;
      } else {
        _xifexpression = this.speed = v.unitarize().times(this.maxSpeed);
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
  }
  
  public void move() {
    this.pos = this.pos.add(this.speed);
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
    UAVBody other = (UAVBody) obj;
    if (other.perceptRadius != this.perceptRadius)
      return false;
    if (other.maxSpeed != this.maxSpeed)
      return false;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (other.nb != this.nb)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.perceptRadius;
    result = prime * result + this.maxSpeed;
    result = prime * result + Objects.hashCode(this.id);
    result = prime * result + this.nb;
    return result;
  }
  
  @Pure
  public int getPerceptRadius() {
    return this.perceptRadius;
  }
  
  @Pure
  public UUID getId() {
    return this.id;
  }
  
  @Pure
  public Vector3D getPos() {
    return this.pos;
  }
  
  @Pure
  public Vector3D getSpeed() {
    return this.speed;
  }
  
  @Pure
  public int getNb() {
    return this.nb;
  }
}
