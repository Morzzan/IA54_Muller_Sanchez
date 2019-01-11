package tools;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.Collection;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import survivor.Survivor;

/**
 * UAV internal event to request switching from SearchBehavior to InformationBehavior
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class DiscoveredEvent extends Event {
  public Collection<Survivor> survivors;
  
  public DiscoveredEvent(final Collection<Survivor> survivors) {
    this.survivors = survivors;
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
   * Returns a String representation of the DiscoveredEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("survivors", this.survivors);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 5262297762L;
}
