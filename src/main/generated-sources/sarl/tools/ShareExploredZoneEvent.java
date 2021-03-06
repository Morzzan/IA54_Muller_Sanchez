package tools;

import com.vividsolutions.jts.geom.Geometry;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.Event;
import org.eclipse.xtext.xbase.lib.Pure;
import org.eclipse.xtext.xbase.lib.util.ToStringBuilder;

/**
 * Information Event to share the explored zone with neighbor UAVS
 */
@SarlSpecification("0.8")
@SarlElementType(15)
@SuppressWarnings("all")
public class ShareExploredZoneEvent extends Event {
  public Geometry zone;
  
  public ShareExploredZoneEvent(final Geometry zone) {
    this.zone = zone;
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
   * Returns a String representation of the ShareExploredZoneEvent event's attributes only.
   */
  @SyntheticMember
  @Pure
  protected void toString(final ToStringBuilder builder) {
    super.toString(builder);
    builder.add("zone", this.zone);
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 1337186705L;
}
