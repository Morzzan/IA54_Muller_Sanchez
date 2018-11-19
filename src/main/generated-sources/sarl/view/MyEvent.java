package view;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.event.Event;
import javafx.event.EventType;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class MyEvent extends Event {
  public final static EventType<MyEvent> eventtype = new EventType<MyEvent>("OPTIONS_ALL");
  
  public MyEvent() {
    super(MyEvent.eventtype);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public MyEvent clone() {
    try {
      return (MyEvent) super.clone();
    } catch (Throwable exception) {
      throw new Error(exception);
    }
  }
  
  @SyntheticMember
  private final static long serialVersionUID = 32386442L;
}
