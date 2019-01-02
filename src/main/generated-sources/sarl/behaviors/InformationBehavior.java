package behaviors;

import com.google.common.base.Objects;
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
import model.Base;
import model.HelpMe;
import model.PerceptEvent;
import model.RelayedEvent;
import model.Survivor;
import model.SurvivorSaved;
import model.UAVBody;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
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
public class InformationBehavior extends Behavior {
  private Collection<Survivor> survivors;
  
  private Base base;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Object _get = occurrence.parameters[0];
    this.survivors = ((Collection<Survivor>) _get);
    Object _get_1 = occurrence.parameters[1];
    this.base = ((Base) _get_1);
  }
  
  private void $behaviorUnit$HelpMe$1(final HelpMe occurrence) {
    synchronized (this.survivors) {
      this.survivors.add(occurrence.survivor);
    }
  }
  
  private void $behaviorUnit$SurvivorSaved$2(final SurvivorSaved occurrence) {
    synchronized (this.survivors) {
      this.survivors.addAll(occurrence.survivors);
    }
  }
  
  private void $behaviorUnit$PerceptEvent$3(final PerceptEvent occurrence) {
    MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
    _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.addPercept(occurrence);
    this.relayInformations();
  }
  
  public Behavior relayInformations() {
    Behavior _xblockexpression = null;
    {
      MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
      final PerceptEvent currentPercept = _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER.getCurrentPercept();
      final Vector3D currentpos = currentPercept.pos;
      final UAVBody closest = currentpos.getClosestToBaseFromMe(currentPercept.around, this.base);
      MoveCapacity _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_1 = this.$castSkill(MoveCapacity.class, (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY == null || this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY.get() == null) ? (this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY = this.$getSkill(MoveCapacity.class)) : this.$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY);
      _$CAPACITY_USE$SKILLSCAPACITIES_MOVECAPACITY$CALLER_1.moveTo(this.base.getPos());
      Behavior _xifexpression = null;
      if ((closest != null)) {
        Behavior _xblockexpression_1 = null;
        {
          final ArrayList<Survivor> s = CollectionLiterals.<Survivor>newArrayList();
          synchronized (this.survivors) {
            s.addAll(this.survivors);
          }
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
          SurvivorSaved _survivorSaved = new SurvivorSaved(s);
          final Scope<Address> _function = (Address it) -> {
            UUID _uUID = it.getUUID();
            UUID _id = closest.getId();
            return Objects.equal(_uUID, _id);
          };
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_survivorSaved, _function);
          DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1 = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
          RelayedEvent _relayedEvent = new RelayedEvent();
          _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER_1.emit(_relayedEvent);
          Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
          _xblockexpression_1 = _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.unregisterBehavior(this);
        }
        _xifexpression = _xblockexpression_1;
      }
      _xblockexpression = _xifexpression;
    }
    return _xblockexpression;
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
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$PerceptEvent$3(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$HelpMe(final HelpMe occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$HelpMe$1(occurrence));
  }
  
  @SyntheticMember
  @PerceptGuardEvaluator
  private void $guardEvaluator$SurvivorSaved(final SurvivorSaved occurrence, final Collection<Runnable> ___SARLlocal_runnableCollection) {
    assert occurrence != null;
    assert ___SARLlocal_runnableCollection != null;
    ___SARLlocal_runnableCollection.add(() -> $behaviorUnit$SurvivorSaved$2(occurrence));
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
  public InformationBehavior(final Agent agent) {
    super(agent);
  }
}
