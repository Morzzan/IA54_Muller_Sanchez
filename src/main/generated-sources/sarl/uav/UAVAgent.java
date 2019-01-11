package uav;

import base.Base;
import com.vividsolutions.jts.geom.Geometry;
import io.sarl.core.Behaviors;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Initialize;
import io.sarl.core.Lifecycle;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.PerceptGuardEvaluator;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.BuiltinCapacitiesProvider;
import io.sarl.lang.core.DynamicSkillProvider;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.Collection;
import java.util.UUID;
import javax.inject.Inject;
import org.eclipse.xtext.xbase.lib.Extension;
import org.eclipse.xtext.xbase.lib.Inline;
import org.eclipse.xtext.xbase.lib.Pure;
import tools.DiscoveredEvent;
import tools.EndEvent;
import tools.RegisterEvent;
import tools.RelayedEvent;
import tools.Utils;
import uav.InformationBehavior;
import uav.MoveCapacity;
import uav.MoveSkill;
import uav.SearchBehavior;
import uav.SwarmInformationBehavior;
import uav.SwarmSearchBehavior;

/**
 * @author morzzan
 * The Decision-maker part of an UAV
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class UAVAgent extends Agent {
  private int nb;
  
  private Base base;
  
  private Geometry unexplored;
  
  private SearchBehavior searchBehavior;
  
  private InformationBehavior informationBehavior;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    if (Utils.swarmInteractions) {
      SwarmSearchBehavior _swarmSearchBehavior = new SwarmSearchBehavior(this);
      this.searchBehavior = _swarmSearchBehavior;
      SwarmInformationBehavior _swarmInformationBehavior = new SwarmInformationBehavior(this);
      this.informationBehavior = _swarmInformationBehavior;
    } else {
      SearchBehavior _searchBehavior = new SearchBehavior(this);
      this.searchBehavior = _searchBehavior;
      InformationBehavior _informationBehavior = new InformationBehavior(this);
      this.informationBehavior = _informationBehavior;
    }
    Object _get = occurrence.parameters[0];
    this.nb = (((Integer) _get)).intValue();
    Object _get_1 = occurrence.parameters[1];
    this.base = ((Base) _get_1);
    MoveSkill _moveSkill = new MoveSkill(this.base);
    this.<MoveSkill>setSkill(_moveSkill, MoveCapacity.class);
    Object _get_2 = occurrence.parameters[2];
    this.unexplored = ((Geometry) _get_2);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    String _string = Integer.valueOf(this.nb).toString();
    String _plus = (" Drone " + _string);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.setLoggingName(_plus);
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.registerBehavior(this.searchBehavior, this.unexplored);
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.info("The UAV was started.");
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    RegisterEvent _registerEvent = new RegisterEvent(this.nb, UAVAgent.class);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_registerEvent);
  }
  
  private void $behaviorUnit$EndEvent$1(final EndEvent occurrence) {
    Lifecycle _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER = this.$castSkill(Lifecycle.class, (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE == null || this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE = this.$getSkill(Lifecycle.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE);
    _$CAPACITY_USE$IO_SARL_CORE_LIFECYCLE$CALLER.killMe();
  }
  
  private void $behaviorUnit$DiscoveredEvent$2(final DiscoveredEvent occurrence) {
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.registerBehavior(this.informationBehavior, occurrence.survivors, this.base);
    MoveCapacity _$CAPACITY_USE$UAV_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$UAV_MOVECAPACITY == null || this.$CAPACITY_USE$UAV_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$UAV_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$UAV_MOVECAPACITY);
    _$CAPACITY_USE$UAV_MOVECAPACITY$CALLER.moveTo(this.base.getPos());
  }
  
  private void $behaviorUnit$RelayedEvent$3(final RelayedEvent occurrence) {
    Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
    _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.registerBehavior(this.searchBehavior, this.unexplored);
    MoveCapacity _$CAPACITY_USE$UAV_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$UAV_MOVECAPACITY == null || this.$CAPACITY_USE$UAV_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$UAV_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$UAV_MOVECAPACITY);
    _$CAPACITY_USE$UAV_MOVECAPACITY$CALLER.moveRandomly();
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
  
  @Extension
  @ImportedCapacityFeature(MoveCapacity.class)
  @SyntheticMember
  private transient ClearableReference<Skill> $CAPACITY_USE$UAV_MOVECAPACITY;
  
  @SyntheticMember
  @Pure
  @Inline(value = "$castSkill(MoveCapacity.class, ($0$CAPACITY_USE$UAV_MOVECAPACITY == null || $0$CAPACITY_USE$UAV_MOVECAPACITY.get() == null) ? ($0$CAPACITY_USE$UAV_MOVECAPACITY = $0$getSkill(MoveCapacity.class)) : $0$CAPACITY_USE$UAV_MOVECAPACITY)", imported = MoveCapacity.class)
  private MoveCapacity $CAPACITY_USE$UAV_MOVECAPACITY$CALLER() {
    if (this.$CAPACITY_USE$UAV_MOVECAPACITY == null || this.$CAPACITY_USE$UAV_MOVECAPACITY.get() == null) {
      this.$CAPACITY_USE$UAV_MOVECAPACITY = $getSkill(MoveCapacity.class);
    }
    return $castSkill(MoveCapacity.class, this.$CAPACITY_USE$UAV_MOVECAPACITY);
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
  private void $guardEvaluator$EndEvent(final EndEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$EndEvent$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$RelayedEvent(final RelayedEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$RelayedEvent$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$DiscoveredEvent(final DiscoveredEvent occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$DiscoveredEvent$2(occurrence));
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
