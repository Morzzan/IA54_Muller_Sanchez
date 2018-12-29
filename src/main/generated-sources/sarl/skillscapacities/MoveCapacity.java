package skillscapacities;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
import model.PerceptEvent;
import model.Vector3D;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(19)
@SuppressWarnings("all")
public interface MoveCapacity extends Capacity {
  public abstract void move(final Vector3D direction);
  
  public abstract void moveRandomly();
  
  public abstract void moveTo(final Vector3D position);
  
  public abstract PerceptEvent getCurrentPercept();
  
  public abstract void addPercept(final PerceptEvent p);
  
  /**
   * @ExcludeFromApidoc
   */
  public static class ContextAwareCapacityWrapper<C extends MoveCapacity> extends Capacity.ContextAwareCapacityWrapper<C> implements MoveCapacity {
    public ContextAwareCapacityWrapper(final C capacity, final AgentTrait caller) {
      super(capacity, caller);
    }
    
    public void move(final Vector3D direction) {
      try {
        ensureCallerInLocalThread();
        this.capacity.move(direction);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void moveRandomly() {
      try {
        ensureCallerInLocalThread();
        this.capacity.moveRandomly();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void moveTo(final Vector3D position) {
      try {
        ensureCallerInLocalThread();
        this.capacity.moveTo(position);
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public PerceptEvent getCurrentPercept() {
      try {
        ensureCallerInLocalThread();
        return this.capacity.getCurrentPercept();
      } finally {
        resetCallerInLocalThread();
      }
    }
    
    public void addPercept(final PerceptEvent p) {
      try {
        ensureCallerInLocalThread();
        this.capacity.addPercept(p);
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
