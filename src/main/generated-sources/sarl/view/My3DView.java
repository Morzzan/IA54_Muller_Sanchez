package view;

import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import java.util.concurrent.CountDownLatch;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.eclipse.xtext.xbase.lib.Exceptions;
import org.eclipse.xtext.xbase.lib.InputOutput;
import org.eclipse.xtext.xbase.lib.Pure;
import view.MyEvent;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class My3DView extends Application {
  private EventType<MyEvent> OPTIONS_ALL = MyEvent.eventtype;
  
  public Box box1 = new Box();
  
  public Box box2 = new Box();
  
  private final static CountDownLatch latch = new CountDownLatch(1);
  
  private static My3DView me = null;
  
  private Stage st = null;
  
  public static My3DView waitForStartUpTest() {
    try {
      My3DView.latch.await();
    } catch (final Throwable _t) {
      if (_t instanceof InterruptedException) {
        final InterruptedException e = (InterruptedException)_t;
        e.printStackTrace();
      } else {
        throw Exceptions.sneakyThrow(_t);
      }
    }
    return My3DView.me;
  }
  
  public void start(final Stage stage) {
    abstract class __My3DView_0 implements EventHandler<WindowEvent> {
      public abstract void handle(final WindowEvent e);
    }
    
    My3DView.me = this;
    this.st = stage;
    __My3DView_0 handler = new __My3DView_0() {
      @Override
      public void handle(final WindowEvent e) {
        MyEvent _myEvent = new MyEvent();
        My3DView.this.box1.fireEvent(_myEvent);
      }
    };
    this.st.setOnShowing(handler);
    this.box1.setWidth(100.0);
    this.box1.setHeight(100.0);
    this.box1.setDepth(100.0);
    this.box1.setTranslateX(200);
    this.box1.setTranslateY(150);
    this.box1.setTranslateZ(0);
    this.box1.setDrawMode(DrawMode.FILL);
    PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setSpecularColor(Color.ORANGE);
    redMaterial.setDiffuseColor(Color.RED);
    this.box1.setMaterial(redMaterial);
    final EventHandler<MyEvent> _function = (MyEvent it) -> {
      this.animate();
    };
    this.box1.<MyEvent>addEventHandler(this.OPTIONS_ALL, _function);
    this.box2.setWidth(100.0);
    this.box2.setHeight(100.0);
    this.box2.setDepth(100.0);
    this.box2.setTranslateX(450);
    this.box2.setTranslateY(150);
    this.box2.setTranslateZ(300);
    this.box2.setDrawMode(DrawMode.FILL);
    Group root = new Group(this.box1, this.box2);
    Scene scene = new Scene(root, 600, 300);
    PerspectiveCamera camera = new PerspectiveCamera(false);
    camera.setTranslateX(0);
    camera.setTranslateY(0);
    camera.setTranslateZ(0);
    scene.setCamera(camera);
    stage.setTitle("Drawing a Box");
    stage.setScene(scene);
    stage.show();
  }
  
  public String animate() {
    String _xblockexpression = null;
    {
      this.box1.relocate(1, 1);
      _xblockexpression = InputOutput.<String>println("Dans Animate");
    }
    return _xblockexpression;
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
  
  @SyntheticMember
  public My3DView() {
    super();
  }
}
