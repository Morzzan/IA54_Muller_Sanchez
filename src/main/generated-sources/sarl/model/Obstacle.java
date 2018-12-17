package model;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import model.SituatedObject;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Obstacle extends SituatedObject {
  @SyntheticMember
  public Obstacle() {
    super();
  }
}
