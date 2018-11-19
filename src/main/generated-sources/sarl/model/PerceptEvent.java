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
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class PerceptEvent extends Event {
  public Vector3D pos;
  
  public List<Vector3D> around;
  
  @DefaultValueSource
  public PerceptEvent(final Vector3D pos, @DefaultValue("model.PerceptEvent#NEW_0") final List<Vector3D> around) {
    this.pos = pos;
    this.around = around;
  }
  
  /**
   * Default value for the parameter around
   */
  @SyntheticMember
  @SarlSourceCode("<Vector3D> newArrayList()")
  private final static List $DEFAULT_VALUE$NEW_0 = CollectionLiterals.<Vector3D>newArrayList();
  
  @DefaultValueUse("model.Vector3D,java.util.List<model.Vector3D>")
  @SyntheticMember
  public PerceptEvent(final Vector3D pos) {
    this(pos, $DEFAULT_VALUE$NEW_0);
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
   * Returns a String representation of the PerceptEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("pos", this.pos);
    builder.add("around", this.around);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 533775990L;
}
