package view;

import io.sarl.javafx.FxViewerController;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import javafx.event.Event;
import org.eclipse.xtext.xbase.lib.InputOutput;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class MyController extends FxViewerController {
  public String _receiveEvent(final Event e) {
    return InputOutput.<String>println("event received");
  }
  
  @SyntheticMember
  public MyController() {
    super();
  }
}
