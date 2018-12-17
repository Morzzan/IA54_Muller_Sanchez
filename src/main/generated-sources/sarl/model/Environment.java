package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import model.Base;
import model.UAVBody;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
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
  
  @Pure
  public boolean isOnZone(final UAVBody uav) {
    double _pow = Math.pow(uav.getPos().getX(), 2);
    double _pow_1 = Math.pow(uav.getPos().getY(), 2);
    double _minus = (_pow - _pow_1);
    return (_minus <= 1000);
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
  
  @SyntheticMember
  public Environment() {
    super();
  }
  
  @Pure
  public Base getBase() {
    return this.base;
  }
}
