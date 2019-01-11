package tools;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.List;
import java.util.UUID;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import survivor.Survivor;

/**
 * Carries Information about the percepts of a survivor
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class SurvivorPerceptEvent extends Event {
  public List<UUID> around;
  
  public Survivor survivor;
  
  public SurvivorPerceptEvent(final List<UUID> around, final Survivor survivor) {
    this.around = around;
    this.survivor = survivor;
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
  
  /**
   * Returns a String representation of the SurvivorPerceptEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("around", this.around);
    builder.add("survivor", this.survivor);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1918806096L;
}
