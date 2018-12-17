package skillscapacities;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.core.AgentTrait;
import io.sarl.lang.core.Capacity;
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
  
  public abstract void goToBase();
  
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
    
    public void goToBase() {
      try {
        ensureCallerInLocalThread();
        this.capacity.goToBase();
      } finally {
        resetCallerInLocalThread();
      }
    }
  }
}
