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
public class Base extends SituatedObject {
  @SyntheticMember
  public Base() {
    super();
  }
}
