package view;

import io.sarl.javafx.FxApplication;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import io.sarl.lang.core.EventSpace;
import io.sarl.util.OpenEventSpace;
import java.util.ArrayList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Camera;
import javafx.scene.Group;
import javafx.scene.PerspectiveCamera;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.stage.Stage;
import model.StartEvent;
import model.UAVBody;
import org.eclipse.xtext.xbase.lib.CollectionLiterals;
import org.eclipse.xtext.xbase.lib.Pure;
import view.MyController;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Fx3DView extends FxApplication {
  public static EventSpace sp;
  
  private MyController controller = new MyController();
  
  private Group grp;
  
  private Scene scene;
  
  private Camera camera;
  
  private ArrayList<Shape3D> drones = CollectionLiterals.<Shape3D>newArrayList();
  
  protected FXMLLoader doApplicationStart(final Stage stage) {
    int nbdrones = Integer.parseInt(this.getParameters().getRaw().get(0));
    this.controller.setUISpace(((OpenEventSpace) Fx3DView.sp));
    Group _group = new Group();
    this.grp = _group;
    for (int i = 0; (i < nbdrones); i++) {
      Box _makedrone = this.makedrone();
      Group _group_1 = new Group(_makedrone);
      this.grp.getChildren().add(_group_1);
    }
    Scene _scene = new Scene(this.grp, 600, 300);
    this.scene = _scene;
    PerspectiveCamera _perspectiveCamera = new PerspectiveCamera(false);
    this.camera = _perspectiveCamera;
    this.camera.setTranslateX((-300));
    this.camera.setTranslateY((-150));
    this.camera.setTranslateZ(0);
    this.scene.setCamera(this.camera);
    stage.setTitle("Drawing a Box");
    stage.setScene(this.scene);
    stage.show();
    StartEvent _startEvent = new StartEvent(this);
    this.controller.emitToAgents(_startEvent);
    return null;
  }
  
  public void moveUAVs(final Iterable<UAVBody> uavs) {
    for (final UAVBody uav : uavs) {
      {
        Shape3D _get = this.drones.get(uav.getNb());
        _get.setTranslateX(uav.getPos().getX());
        Shape3D _get_1 = this.drones.get(uav.getNb());
        _get_1.setTranslateY(uav.getPos().getY());
        Shape3D _get_2 = this.drones.get(uav.getNb());
        _get_2.setTranslateZ(uav.getPos().getZ());
      }
    }
  }
  
  public void registerUAV(final UAVBody uav) {
    Shape3D box = this.drones.get(uav.getNb());
    box.setTranslateX(uav.getPos().getX());
    box.setTranslateY(uav.getPos().getY());
    box.setTranslateZ(uav.getPos().getZ());
  }
  
  public Box makeGround() {
    Box box1 = new Box();
    box1.setWidth(300.0);
    box1.setHeight(300.0);
    box1.setDepth(1.0);
    box1.setTranslateX(0);
    box1.setTranslateY(0);
    box1.setTranslateZ(0);
    box1.setDrawMode(DrawMode.FILL);
    PhongMaterial blueMaterial = new PhongMaterial();
    blueMaterial.setSpecularColor(Color.BLUE);
    box1.setMaterial(blueMaterial);
    return box1;
  }
  
  public Box makedrone() {
    Box box = new Box();
    box.setWidth(10.0);
    box.setHeight(10.0);
    box.setDepth(10.0);
    box.setTranslateX(0);
    box.setTranslateY(0);
    box.setTranslateZ(0);
    box.setDrawMode(DrawMode.FILL);
    PhongMaterial redMaterial = new PhongMaterial();
    redMaterial.setSpecularColor(Color.ORANGE);
    redMaterial.setDiffuseColor(Color.RED);
    box.setMaterial(redMaterial);
    this.drones.add(box);
    return box;
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
  public Fx3DView() {
    super();
  }
}
