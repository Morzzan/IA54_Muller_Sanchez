package uav;

import base.Base;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Capacity;
import org.eclipse.xtext.xbase.lib.Pure;
import tools.Vector3D;
import uav.MoveSkill;
import uav.UAVBody;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(21)
@SuppressWarnings("all")
public class MoveWithSeparationSkill extends MoveSkill implements Capacity {
  @Override
  @Pure
  public Vector3D separation() {
    Vector3D vSep = new Vector3D(0, 0, 0);
    if (((this.getCurrentPercept() != null) && (this.getCurrentPercept().around != null))) {
      for (final UAVBody o : this.getCurrentPercept().around) {
        {
          Vector3D n = o.getPos().add(this.getCurrentPercept().pos.times((-1)));
          double _norm = n.norm();
          boolean _tripleEquals = (_norm == 0);
          if (_tripleEquals) {
            n = Vector3D.randomDirection();
          }
          double _pow = Math.pow(n.norm(), 2);
          double _divide = ((-1000) / _pow);
          Vector3D a = n.times(_divide);
          vSep = vSep.add(a);
        }
      }
    }
    return vSep;
  }
  
  @SyntheticMember
  public MoveWithSeparationSkill(final Base base) {
    super(base);
  }
}
