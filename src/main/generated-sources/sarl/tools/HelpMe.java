package tools;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import survivor.Survivor;

/**
 * SOS message from a Survivor to signal his presence to an UAV
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class HelpMe extends Event {
  public Survivor survivor;
  
  public HelpMe(final Survivor survivor) {
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
   * Returns a String representation of the HelpMe event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("survivor", this.survivor);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1216103771L;
}
