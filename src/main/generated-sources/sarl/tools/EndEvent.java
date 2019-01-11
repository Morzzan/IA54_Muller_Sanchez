package tools;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Address;
import io.sarl.lang.core.Event;

/**
 * Finishing event requesting every agent to commit suicide as the simulation is terminated
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class EndEvent extends Event {
  @SyntheticMember
  public EndEvent() {
    super();
  }
  
  @SyntheticMember
  public EndEvent(final Address source) {
    super(source);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 588368462L;
}
