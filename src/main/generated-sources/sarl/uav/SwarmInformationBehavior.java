package uav;

import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import java.util.Collection;
import org.eclipse.xtext.xbase.lib.Pure;
import tools.PerceptEvent;
import tools.SurvivorSaved;
import tools.Vector3D;
import uav.InformationBehavior;
import uav.UAVBody;

/**
 * @author morzzan
 * Behavior specific to swarmed UAVS trying to communicate with the base to share information about survivors
 */
@SarlSpecification("0.8")
@SarlElementType(20)
@SuppressWarnings("all")
public class SwarmInformationBehavior extends InformationBehavior {
  private void $behaviorUnit$SurvivorSaved$0(final SurvivorSaved occurrence) {
    synchronized (this.survivors) {
      this.survivors.addAll(occurrence.survivors);
    }
  }
  
  /**
   * @return The UAV body in candidates closest to the base if it exists, null if it doesn't
   */
  @Override
  @Pure
  public UAVBody getClosestToBaseFromMe(final PerceptEvent currentPercept) {
    Vector3D closest = currentPercept.pos;
    UAVBody closestUAV = null;
    double minDist = closest.substract(this.base.getPos()).norm();
    boolean _isEmpty = currentPercept.around.isEmpty();
    boolean _not = (!_isEmpty);
    if (_not) {
      for (final UAVBody uav : currentPercept.around) {
        {
          final double dist = uav.getPos().substract(this.base.getPos()).norm();
          if ((dist < minDist)) {
            closestUAV = uav;
            minDist = dist;
            closest = uav.getPos();
          }
        }
      }
      return closestUAV;
    }
    return null;
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SurvivorSaved(final SurvivorSaved occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SurvivorSaved$0(occurrence));
  }
  
  @SyntheticMember
  public SwarmInformationBehavior(final Agent agent) {
    super(agent);
  }
}
