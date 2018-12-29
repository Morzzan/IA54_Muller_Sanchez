package model;

import com.vividsolutions.jts.geom.Coordinate;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Random;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Vector3D {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private double x;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private double y;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private double z;
  
  public Vector3D() {
  }
  
  public Vector3D(final double x, final double y, final double z) {
    this.x = x;
    this.y = y;
    this.z = z;
  }
  
  public Vector3D(final Vector3D v) {
    this.x = v.x;
    this.y = v.y;
    this.z = v.z;
  }
  
  public Vector3D(final Coordinate c) {
    this.x = c.x;
    this.y = c.y;
    this.z = 0;
  }
  
  @Pure
  public Vector3D add(final Vector3D v) {
    return new Vector3D((this.x + v.x), (this.y + v.y), (this.z + v.z));
  }
  
  @Pure
  public Vector3D substract(final Vector3D v) {
    return new Vector3D((this.x - v.x), (this.y - v.y), (this.z - v.z));
  }
  
  @Pure
  public double norm() {
    return Math.sqrt((((this.x * this.x) + (this.y * this.y)) + (this.z * this.z)));
  }
  
  @Pure
  public Coordinate toPlaneCoordinate() {
    return new Coordinate(this.x, this.y);
  }
  
  @Pure
  public Vector3D normalize() {
    double _norm = this.norm();
    double _divide = (1 / _norm);
    return this.times(_divide);
  }
  
  @Pure
  public Vector3D unitarize() {
    double _norm = this.norm();
    double _divide = (this.x / _norm);
    double _norm_1 = this.norm();
    double _divide_1 = (this.y / _norm_1);
    double _norm_2 = this.norm();
    double _divide_2 = (this.z / _norm_2);
    return new Vector3D(_divide, _divide_1, _divide_2);
  }
  
  @Pure
  public Vector3D times(final double k) {
    return new Vector3D((this.x * k), (this.y * k), (this.z * k));
  }
  
  @Pure
  public String toString() {
    return (((((("(" + Integer.valueOf(((int) this.x))) + ",") + Integer.valueOf(((int) this.y))) + ",") + Integer.valueOf(((int) this.z))) + ")");
  }
  
  public static Vector3D randomDirection() {
    Vector3D v = new Vector3D();
    final Random rd = new Random();
    double _nextDouble = rd.nextDouble();
    double _minus = (_nextDouble - 0.5);
    v.x = _minus;
    double _nextDouble_1 = rd.nextDouble();
    double _minus_1 = (_nextDouble_1 - 0.5);
    v.y = _minus_1;
    v.z = 0;
    return v.unitarize();
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
    Vector3D other = (Vector3D) obj;
    if (Double.doubleToLongBits(other.x) != Double.doubleToLongBits(this.x))
      return false;
    if (Double.doubleToLongBits(other.y) != Double.doubleToLongBits(this.y))
      return false;
    if (Double.doubleToLongBits(other.z) != Double.doubleToLongBits(this.z))
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (int) (Double.doubleToLongBits(this.x) ^ (Double.doubleToLongBits(this.x) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.y) ^ (Double.doubleToLongBits(this.y) >>> 32));
    result = prime * result + (int) (Double.doubleToLongBits(this.z) ^ (Double.doubleToLongBits(this.z) >>> 32));
    return result;
  }
  
  @Pure
  public double getX() {
    return this.x;
  }
  
  @Pure
  public double getY() {
    return this.y;
  }
  
  @Pure
  public double getZ() {
    return this.z;
  }
}
