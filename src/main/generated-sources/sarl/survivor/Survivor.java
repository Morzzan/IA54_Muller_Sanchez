package survivor;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.Objects;
import java.util.UUID;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
import org.eclipse.xtext.xbase.lib.Pure;
import tools.PerceptEvent;
import tools.SituatedObject;
import tools.Vector3D;

/**
 * @author morzzan
 * This class represents the body of a survivor.
 * The survivor cannot directly access it as it is part of the environment.
 * The survivor has to wait for @see PerceptEvent to get informations.
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Survivor extends SituatedObject {
  @Accessors(AccessorType.PUBLIC_GETTER)
  private UUID id;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private int nb;
  
  @Accessors({ AccessorType.PUBLIC_GETTER, AccessorType.PUBLIC_SETTER })
  private boolean rescued = false;
  
  public Survivor(final UUID id, final int nb, final Vector3D pos) {
    this.id = id;
    this.nb = nb;
    this.setPos(pos);
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
    Survivor other = (Survivor) obj;
    if (!Objects.equals(this.id, other.id)) {
      return false;
    }
    if (other.nb != this.nb)
      return false;
    if (other.rescued != this.rescued)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + Objects.hashCode(this.id);
    result = prime * result + this.nb;
    result = prime * result + (this.rescued ? 1231 : 1237);
    return result;
  }
  
  @Pure
  public UUID getId() {
    return this.id;
  }
  
  @Pure
  public int getNb() {
    return this.nb;
  }
  
  @Pure
  public boolean isRescued() {
    return this.rescued;
  }
  
  public void setRescued(final boolean rescued) {
    this.rescued = rescued;
  }
}
