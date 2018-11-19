package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;
import view.Sarl3DView;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class StartEvent extends Event {
  public Sarl3DView fx;
  
  public StartEvent(final Sarl3DView fx) {
    this.fx = fx;
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
   * Returns a String representation of the StartEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("fx", this.fx);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = -730678185L;
}
