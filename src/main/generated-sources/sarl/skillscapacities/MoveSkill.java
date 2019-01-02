package skillscapacities;

import com.google.common.base.Objects;
import io.sarl.core.DefaultContextInteractions;
import io.sarl.core.Logging;
import io.sarl.lang.annotation.ImportedCapacityFeature;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Agent;
import io.sarl.lang.core.Skill;
import io.sarl.lang.util.ClearableReference;
import java.util.concurrent.Semaphore;
import model.GoThatWay;
import model.PerceptEvent;
import model.UAVBody;
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
@SarlElementType(21)
@SuppressWarnings("all")
public class MoveSkill extends Skill implements MoveCapacity {
  private final Semaphore lock = new Semaphore(1);
  
  private PerceptEvent percept = new PerceptEvent(new Vector3D(), new Vector3D(), CollectionLiterals.<UAVBody>newArrayList(), true);
  
  @Override
  public PerceptEvent getCurrentPercept() {
    try {
      this.lock.acquire();
      try {
        return new PerceptEvent(this.percept.pos, this.percept.speed, this.percept.around, this.percept.onZone);
      } finally {
        this.lock.release();
      }
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void addPercept(final PerceptEvent p) {
    try {
      this.lock.acquire();
      this.percept = p;
      this.lock.release();
    } catch (Throwable _e) {
      throw Exceptions.sneakyThrow(_e);
    }
  }
  
  @Override
  public void move(final Vector3D direction) {
    Vector3D d = direction.add(this.separation());
    DefaultContextInteractions _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER = this.$castSkill(DefaultContextInteractions.class, (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS == null || this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS = this.$getSkill(DefaultContextInteractions.class)) : this.$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS);
    Vector3D _stayOnZone = this.stayOnZone(d);
    GoThatWay _goThatWay = new GoThatWay(_stayOnZone);
    _$CAPACITY_USE$IO_SARL_CORE_DEFAULTCONTEXTINTERACTIONS$CALLER.emit(_goThatWay);
  }
  
  @Override
  public void moveRandomly() {
    this.move(Vector3D.randomDirection());
  }
  
  @Override
  public void moveTo(final Vector3D position) {
    this.move(position.substract(this.getCurrentPercept().pos).unitarize());
  }
  
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
          Vector3D _unitarize = n.unitarize();
          double _pow = Math.pow(n.norm(), 2);
          double _divide = ((-100) / _pow);
          Vector3D a = _unitarize.times(_divide);
          vSep = vSep.add(a);
        }
      }
    }
    return vSep;
  }
  
  public Vector3D stayOnZone(final Vector3D direction) {
    if ((Objects.equal(this.getCurrentPercept(), null) || this.getCurrentPercept().onZone)) {
      return direction;
    } else {
      Logging _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER = this.$castSkill(Logging.class, (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING == null || this.$CAPACITY_USE$IO_SARL_CORE_LOGGING.get() == null) ? (this.$CAPACITY_USE$IO_SARL_CORE_LOGGING = this.$getSkill(Logging.class)) : this.$CAPACITY_USE$IO_SARL_CORE_LOGGING);
      _$CAPACITY_USE$IO_SARL_CORE_LOGGING$CALLER.info("Not On Zone Anymore");
      return this.getCurrentPercept().pos.times((-1));
    }
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
  public MoveSkill() {
    super();
  }
  
  @SyntheticMember
  public MoveSkill(final Agent agent) {
    super(agent);
  }
}
