package model;

import io.sarl.lang.annotation.DefaultValue;
import io.sarl.lang.annotation.DefaultValueSource;
import io.sarl.lang.annotation.DefaultValueUse;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSourceCode;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import java.util.List;
import model.UAVBody;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class PerceptEvent extends Event {
  public Vector3D pos = new Vector3D();
  
  public boolean onZone;
  
  public List<UAVBody> around;
  
  public Vector3D speed;
  
  @DefaultValueSource
  public PerceptEvent(final Vector3D pos, final Vector3D speed, @DefaultValue("model.PerceptEvent#NEW_0") final List<UAVBody> around, final boolean onZone) {
    this.pos = pos;
    this.around = around;
    this.onZone = onZone;
    this.speed = speed;
  }
  
  /**
   * Default value for the parameter around
   */
  @SyntheticMember
  @SarlSourceCode("<Vector3D> newArrayList()")
  private final static List $DEFAULT_VALUE$NEW_0 = CollectionLiterals.<Vector3D>newArrayList();
  
  @DefaultValueUse("model.Vector3D,model.Vector3D,java.util.List<model.UAVBody>,boolean")
  @SyntheticMember
  public PerceptEvent(final Vector3D pos, final Vector3D speed, final boolean onZone) {
    this(pos, speed, $DEFAULT_VALUE$NEW_0, onZone);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public boolean equals(final Object obj) {
    if (this == obj)
      return true;
    if (obj == null)
      return false;
    if (getClass() != obj.getClass())
      return false;
    PerceptEvent other = (PerceptEvent) obj;
    if (other.onZone != this.onZone)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + (this.onZone ? 1231 : 1237);
    return result;
  }
  
  /**
   * Returns a String representation of the PerceptEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("pos", this.pos);
    builder.add("onZone", this.onZone);
    builder.add("around", this.around);
    builder.add("speed", this.speed);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 927151833L;
}
