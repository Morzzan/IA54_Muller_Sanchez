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
import javafx.scene.shape.Circle;
import javafx.scene.shape.DrawMode;
import javafx.scene.shape.Shape3D;
import javafx.scene.shape.Sphere;
import javafx.stage.Stage;
import model.Base;
import model.StartEvent;
import model.Survivor;
import model.UAVBody;
import model.Utils;
import org.eclipse.xtend.lib.annotations.AccessorType;
import org.eclipse.xtend.lib.annotations.Accessors;
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
  
  private final int sceneX = 1200;
  
  private final int sceneY = 600;
  
  private MyController controller = new MyController();
  
  private Group grp = new Group();
  
  private Scene scene;
  
  private Camera camera;
  
  private Shape3D base;
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private ArrayList<Shape3D> drones = CollectionLiterals.<Shape3D>newArrayList();
  
  @Accessors(AccessorType.PUBLIC_GETTER)
  private ArrayList<Shape3D> survivors = CollectionLiterals.<Shape3D>newArrayList();
  
  protected FXMLLoader doApplicationStart(final Stage stage) {
    int nbdrones = Integer.parseInt(this.getParameters().getRaw().get(0));
    int nbSurvivors = Integer.parseInt(this.getParameters().getRaw().get(1));
    this.controller.setUISpace(((OpenEventSpace) Fx3DView.sp));
    this.makeBase();
    this.makeDrones(nbdrones);
    this.makeSurvivors(nbSurvivors);
    this.setCamera();
    stage.setTitle("See the drones");
    stage.setScene(this.scene);
    stage.show();
    StartEvent _startEvent = new StartEvent(this);
    this.controller.emitToAgents(_startEvent);
    return null;
  }
  
  public void updateObjects(final Iterable<UAVBody> uavs, final Iterable<Survivor> survivors, final Base base) {
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
    for (final Survivor survivor : survivors) {
      {
        Shape3D box = this.survivors.get(survivor.getNb());
        box.setTranslateX(survivor.getPos().getX());
        box.setTranslateY(survivor.getPos().getY());
        box.setTranslateZ(survivor.getPos().getZ());
        boolean _isRescued = survivor.isRescued();
        if (_isRescued) {
          PhongMaterial gray = new PhongMaterial();
          gray.setSpecularColor(Color.GRAY);
          gray.setDiffuseColor(Color.GRAY);
          box.setMaterial(gray);
        }
      }
    }
    this.base.setTranslateX(base.getPos().getX());
    this.base.setTranslateY(base.getPos().getY());
    this.base.setTranslateZ(base.getPos().getZ());
  }
  
  public void registerUAV(final UAVBody uav) {
    Shape3D box = this.drones.get(uav.getNb());
    box.setTranslateX(uav.getPos().getX());
    box.setTranslateY(uav.getPos().getY());
    box.setTranslateZ(uav.getPos().getZ());
  }
  
  public void registerSurvivor(final Survivor survivor) {
    Shape3D box = this.survivors.get(survivor.getNb());
    box.setTranslateX(survivor.getPos().getX());
    box.setTranslateY(survivor.getPos().getY());
    box.setTranslateZ(survivor.getPos().getZ());
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
    blueMaterial.setDiffuseColor(Color.BLANCHEDALMOND);
    box1.setMaterial(blueMaterial);
    return box1;
  }
  
  public boolean makeBase() {
    boolean _xblockexpression = false;
    {
      Box box1 = new Box();
      box1.setWidth(20.0);
      box1.setHeight(20.0);
      box1.setDepth(20.0);
      box1.setTranslateX(0);
      box1.setTranslateY(0);
      box1.setTranslateZ(0);
      box1.setDrawMode(DrawMode.FILL);
      PhongMaterial blueMaterial = new PhongMaterial();
      blueMaterial.setSpecularColor(Color.BLUE);
      blueMaterial.setDiffuseColor(Color.BLANCHEDALMOND);
      box1.setMaterial(blueMaterial);
      this.base = box1;
      Group _group = new Group(box1);
      _xblockexpression = this.grp.getChildren().add(_group);
    }
    return _xblockexpression;
  }
  
  public void makeDrones(final int nbDrones) {
    for (int i = 0; (i < nbDrones); i++) {
      {
        Sphere box = new Sphere();
        box.setRadius(5);
        box.setTranslateX(0);
        box.setTranslateY(0);
        box.setTranslateZ(0);
        box.setDrawMode(DrawMode.FILL);
        PhongMaterial blueMaterial = new PhongMaterial();
        blueMaterial.setSpecularColor(Color.BLUE);
        blueMaterial.setDiffuseColor(Color.BLANCHEDALMOND);
        box.setMaterial(blueMaterial);
        this.drones.add(box);
        Circle c = new Circle();
        c.centerXProperty().bind(box.translateXProperty());
        c.centerYProperty().bind(box.translateYProperty());
        c.setRadius(Utils.perceptRadius);
        c.setFill(Color.TRANSPARENT);
        c.setStroke(Color.GREENYELLOW);
        Group _group = new Group(box, c);
        this.grp.getChildren().add(_group);
      }
    }
  }
  
  public void makeSurvivors(final int nbSurvivors) {
    for (int i = 0; (i < nbSurvivors); i++) {
      {
        Sphere box = new Sphere();
        box.setRadius(5);
        box.setTranslateX(0);
        box.setTranslateY(0);
        box.setTranslateZ(0);
        box.setDrawMode(DrawMode.FILL);
        PhongMaterial redMaterial = new PhongMaterial();
        redMaterial.setSpecularColor(Color.ORANGE);
        redMaterial.setDiffuseColor(Color.RED);
        box.setMaterial(redMaterial);
        this.survivors.add(box);
        Group _group = new Group(box);
        this.grp.getChildren().add(_group);
      }
    }
  }
  
  public void setCamera() {
    Scene _scene = new Scene(this.grp, this.sceneX, this.sceneY);
    this.scene = _scene;
    PerspectiveCamera _perspectiveCamera = new PerspectiveCamera();
    this.camera = _perspectiveCamera;
    this.camera.setFarClip(1000);
    this.camera.setTranslateX(((-this.sceneX) / 2));
    this.camera.setTranslateY(((-this.sceneY) / 2));
    this.camera.setTranslateZ(200);
    this.scene.setCamera(this.camera);
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
    Fx3DView other = (Fx3DView) obj;
    if (other.sceneX != this.sceneX)
      return false;
    if (other.sceneY != this.sceneY)
      return false;
    return super.equals(obj);
  }
  
  @Override
  @Pure
  @SyntheticMember
  public int hashCode() {
    int result = super.hashCode();
    final int prime = 31;
    result = prime * result + this.sceneX;
    result = prime * result + this.sceneY;
    return result;
  }
  
  @SyntheticMember
  public Fx3DView() {
    super();
  }
  
  @Pure
  public ArrayList<Shape3D> getDrones() {
    return this.drones;
  }
  
  @Pure
  public ArrayList<Shape3D> getSurvivors() {
    return this.survivors;
  }
}
