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
 * Relay event to pass survivors informations to the next UAV that will carry this information to base
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class SurvivorSaved extends Event {
  public Collection<Survivor> survivors;
  
  public SurvivorSaved(final Collection<Survivor> survivors) {
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
   * Returns a String representation of the SurvivorSaved event's attributes only.
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
