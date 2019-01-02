package behaviors;

import com.google.common.base.Objects;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.operation.distance.DistanceOp;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Behavior;
import io.sarl.lang.core.Scope;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;
import java.util.concurrent.Semaphore;
import model.DiscoveredEvent;
import model.HelpMe;
import model.PerceptEvent;
import model.ShareExploredZoneEvent;
import model.Survivor;
import model.SurvivorSaved;
import model.UAVBody;
import model.Utils;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import skillscapacities.MoveCapacity;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(20)
@SuppressWarnings("all")
public class SearchBehavior extends Behavior {
  private Geometry unexplored;
  
  private final Semaphore lock = new Semaphore(0);
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    this.unexplored = ((Geometry) _get);
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
          ShareExploredZoneEvent _shareExploredZoneEvent = new ShareExploredZoneEvent(this.unexplored);
          final Scope<Address> _function = (Address it) -> {
            UUID _uUID = it.getUUID();
            UUID _id = uav.getId();
            return Objects.equal(_uUID, _id);
          };
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_shareExploredZoneEvent, _function);
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
  
  private void $behaviorUnit$ShareExploredZoneEvent$2(final ShareExploredZoneEvent occurrence) {
    try {
      this.lock.acquire();
      this.unexplored = this.unexplored.intersection(occurrence.zone);
      this.lock.release();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  private void $behaviorUnit$HelpMe$3(final HelpMe occurrence) {
    final ArrayList<Survivor> l = CollectionLiterals.<Survivor>newArrayList();
    l.add(occurrence.survivor);
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    DiscoveredEvent _discoveredEvent = new DiscoveredEvent(l);
    final Scope<Address> _function = (Address it) -> {
      UUID _uUID = it.getUUID();
      UUID _iD = this.getOwner().getID();
      return Objects.equal(_uUID, _iD);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_discoveredEvent, _function);
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.unregisterBehavior(this);
  }
  
  private void $behaviorUnit$SurvivorSaved$4(final SurvivorSaved occurrence) {
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    DiscoveredEvent _discoveredEvent = new DiscoveredEvent(occurrence.survivors);
    final Scope<Address> _function = (Address it) -> {
      UUID _uUID = it.getUUID();
      UUID _iD = this.getOwner().getID();
      return Objects.equal(_uUID, _iD);
    };
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_discoveredEvent, _function);
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.unregisterBehavior(this);
  }
  
  public void updateUnexploredZone() {
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
  
  public Vector3D makeExplorationStrategy() {
    MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
    final PerceptEvent currentPercept = _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.getCurrentPercept();
    Vector3D r = null;
    final Point p = new GeometryFactory().createPoint(currentPercept.pos.add(currentPercept.speed).toPlaneCoordinate());
    try {
      this.lock.acquire();
      final DistanceOp op = new DistanceOp(this.unexplored, p);
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
  @ImportedCapacityFeature(Behaviors.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(Behaviors.class, ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? ($0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $0$getSkill(Behaviors.class)) : $0$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS)", imported = Behaviors.class)
  private Behaviors $CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER() {
    if (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) {
      this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = $getSkill(Behaviors.class);
    }
    return $castSkill(Behaviors.class, this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
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
  private void $guardEvaluator$ShareExploredZoneEvent(final ShareExploredZoneEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$ShareExploredZoneEvent$2(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$HelpMe(final HelpMe occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$HelpMe$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SurvivorSaved(final SurvivorSaved occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SurvivorSaved$4(occurrence));
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
  public SearchBehavior(final Agent agent) {
    super(agent);
  }
}
