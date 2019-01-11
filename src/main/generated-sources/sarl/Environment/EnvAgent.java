package Environment;

import Environment.GraphicalManagingBehavior;
import Environment.NoGraphManagingBehavior;
import io.sarl.core.Behaviors;
import io.sarl.core.Initialize;
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

/**
 * @author morzzan
 * The initializer if the Environment Agent
 */
@SarlSpecification("0.8")
@SarlElementType(18)
@SuppressWarnings("all")
public class EnvAgent extends Agent {
  private int nbuavs;
  
  private int nbSurvivors;
  
  private void $behaviorUnit$Initialize$0(final Initialize occurrence) {
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("The EnvAgent was started.");
    Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1 = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
    _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER_1.setLoggingName("Env");
    Object _get = occurrence.parameters[0];
    this.nbuavs = (((Integer) _get)).intValue();
    Object _get_1 = occurrence.parameters[1];
    this.nbSurvivors = (((Integer) _get_1)).intValue();
    Object _get_2 = occurrence.parameters[2];
    if ((((Boolean) _get_2)).booleanValue()) {
      Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
      GraphicalManagingBehavior _graphicalManagingBehavior = new GraphicalManagingBehavior(this);
      _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER.registerBehavior(_graphicalManagingBehavior, Integer.valueOf(this.nbuavs), Integer.valueOf(this.nbSurvivors));
    } else {
      Behaviors _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER_1 = this.$castSkill(Behaviors.class, (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS == null || this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS = this.$getSkill(Behaviors.class)) : this.$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS);
      NoGraphManagingBehavior _noGraphManagingBehavior = new NoGraphManagingBehavior(this);
      _$CAPACITY_USE$IO_SARL_CORE_BEHAVIORS$CALLER_1.registerBehavior(_noGraphManagingBehavior, Integer.valueOf(this.nbuavs), Integer.valueOf(this.nbSurvivors));
    }
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
    EnvAgent other = (EnvAgent) obj;
    if (other.nbuavs != this.nbuavs)
      return false;
    if (other.nbSurvivors != this.nbSurvivors)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.nbuavs;
    result = prime * result + this.nbSurvivors;
    return result;
  }
  
  @SyntheticMember
  public EnvAgent(final UUID parentID, final UUID agentID) {
    super(parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  @Deprecated
  public EnvAgent(final BuiltinCapacitiesProvider provider, final UUID parentID, final UUID agentID) {
    super(provider, parentID, agentID);
  }
  
  @SyntheticMember
  @Inject
  public EnvAgent(final UUID parentID, final UUID agentID, final DynamicSkillProvider skillProvider) {
    super(parentID, agentID, skillProvider);
  }
}
