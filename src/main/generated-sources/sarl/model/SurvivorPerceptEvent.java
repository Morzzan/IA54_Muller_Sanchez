package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.List;
import java.util.UUID;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class SurvivorPerceptEvent extends Event {
  public List<UUID> around;
  
  public Vector3D pos;
  
  public SurvivorPerceptEvent(final List<UUID> around, final Vector3D pos) {
    this.around = around;
    this.pos = pos;
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
    builder.add("pos", this.pos);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 4056249102L;
}
