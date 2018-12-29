package model;

import com.google.common.base.Objects;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.distance.DistanceOp;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import javax.inject.Inject;
import model.Base;
import model.EndEvent;
import model.HelpMe;
import model.PerceptEvent;
import model.RegisterEvent;
import model.ShareExploredZone;
import model.SurvivorSaved;
import model.UAVBody;
import model.Utils;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import skillscapacities.MoveCapacity;
import skillscapacities.MoveSkill;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class UAVAgent extends Agent {
  private int nb;
  
  private Base base;
  
  private Geometry unexplored;
  
  private final Semaphore lock = new Semaphore(0);
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    this.nb = (((Integer) _get)).intValue();
    Object _get_1 = occurrence.parameters[1];
    this.base = ((Base) _get_1);
    Object _get_2 = occurrence.parameters[2];
    this.unexplored = ((Geometry) _get_2);
    MoveSkill _moveSkill = new MoveSkill();
    this.<MoveSkill>setSkill(_moveSkill, MoveCapacity.class);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    String _string = Integer.valueOf(this.nb).toString();
    String _plus = (" Drone " + _string);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName(_plus);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info("The UAV was started.");
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    RegisterEvent _registerEvent = new RegisterEvent(this.nb, UAVAgent.class);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_registerEvent);
    this.lock.release();
  }
  
  private void $behaviorUnit$PerceptEvent$1(final PerceptEvent occurrence) {
    try {
      MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
      _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.addPercept(occurrence);
      this.updateUnexploredZone();
      for (final UAVBody uav : occurrence.around) {
        {
          this.lock.acquire();
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
          ShareExploredZone _shareExploredZone = new ShareExploredZone(this.unexplored);
          final Scope<Address> _function = (Address it) -> {
            UUID _uUID = it.getUUID();
            UUID _id = uav.getId();
            return Objects.equal(_uUID, _id);
          };
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_shareExploredZone, _function);
          this.lock.release();
        }
      }
      this.lock.acquire();
      double _area = this.unexplored.getArea();
      boolean _greaterThan = (_area > 0);
      if (_greaterThan) {
        this.lock.release();
        MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_1 = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
        _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_1.moveTo(this.makeExplorationStrategy());
      } else {
        this.lock.release();
        MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_2 = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
        _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_2.moveRandomly();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void $behaviorUnit$ShareExploredZone$2(final ShareExploredZone occurrence) {
    try {
      this.lock.acquire();
      this.unexplored = this.unexplored.intersection(occurrence.zone);
      this.lock.release();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void $behaviorUnit$EndEvent$3(final EndEvent occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$HelpMe$4(final HelpMe occurrence) {
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    UUID _uUID = occurrence.getSource().getUUID();
    SurvivorSaved _survivorSaved = new SurvivorSaved(_uUID);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_survivorSaved);
  }
  
  protected void updateUnexploredZone() {
    try {
      MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
      final Vector3D pos = _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.getCurrentPercept().pos;
      double _x = pos.getX();
      double _y = pos.getY();
      Coordinate _coordinate = new Coordinate(_x, _y);
      final Polygon myGeom = Utils.createCircle(Utils.perceptRadius, _coordinate);
      this.lock.acquire();
      this.unexplored = this.unexplored.difference(myGeom);
      this.lock.release();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  protected Vector3D makeExplorationStrategy() {
    MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
    final PerceptEvent currentPercept = _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.getCurrentPercept();
    Vector3D r = null;
    try {
      this.lock.acquire();
      Polygon _createCircle = Utils.createCircle(1, currentPercept.pos.add(currentPercept.speed).toPlaneCoordinate());
      final DistanceOp op = new DistanceOp(this.unexplored, _createCircle);
      Coordinate _get = op.nearestPoints()[0];
      Vector3D _vector3D = new Vector3D(_get);
      r = _vector3D;
      this.lock.release();
    } catch (final Throwable _t) {
      if (_t instanceof Exception) {
        Vector3D _vector3D_1 = new Vector3D();
        r = _vector3D_1;
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return r;
  }
  
  @Extension
  @ImportedCapacityFeature(Logging.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LOGGING;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Logging.class, ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || $0$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LOGGING = $0$getSkill(Logging.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LOGGING)", imported = Logging.class)
  private Logging $CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = $getSkill(Logging.class);
    }
    return $castSkill(Logging.class, this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
  }
  
  @Extension
  @ImportedCapacityFeature(DefaultContextInteractions.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(DefaultContextInteractions.class, ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $0$getSkill(DefaultContextInteractions.class)) : $0$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS)", imported = DefaultContextInteractions.class)
  private DefaultContextInteractions $CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = $getSkill(DefaultContextInteractions.class);
    }
    return $castSkill(DefaultContextInteractions.class, this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
  }
  
  @Extension
  @ImportedCapacityFeature(Lifecycle.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Lifecycle.class, ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $0$getSkill(Lifecycle.class)) : $0$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE)", imported = Lifecycle.class)
  private Lifecycle $CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = $getSkill(Lifecycle.class);
    }
    return $castSkill(Lifecycle.class, this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
  }
  
  @Extension
  @ImportedCapacityFeature(MoveCapacity.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(MoveCapacity.class, ($0$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || $0$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? ($0$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = $0$getSkill(MoveCapacity.class)) : $0$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY)", imported = MoveCapacity.class)
  private MoveCapacity $CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER() {
    if (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) {
      this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = $getSkill(MoveCapacity.class);
    }
    return $castSkill(MoveCapacity.class, this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$Initialize(final Initialize occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$Initialize$0(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$PerceptEvent(final PerceptEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PerceptEvent$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$HelpMe(final HelpMe occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$HelpMe$4(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$EndEvent(final EndEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$EndEvent$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$ShareExploredZone(final ShareExploredZone occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ShareExploredZone$2(occurrence));
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
    UAVAgent other = (UAVAgent) obj;
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
    result = prime * result + this.nb;
    return result;
  }
  
  @SyntheticMember
  public UAVAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public UAVAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public UAVAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
