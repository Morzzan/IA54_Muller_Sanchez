package model;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import com.vividsolutions.jts.geom.Polygon;
import com.vividsolutions.jts.shape.random.RandomPointsBuilder;
import com.vividsolutions.jts.util.GeometricShapeFactory;
import io.sarl.lang.annotation.SarlElementType;
import io.sarl.lang.annotation.SarlSpecification;
import io.sarl.lang.annotation.SyntheticMember;
import model.Vector3D;
import org.eclipse.xtext.xbase.lib.Pure;

/**
 * @author morzzan
 */
@SarlSpecification("0.8")
@SarlElementType(10)
@SuppressWarnings("all")
public class Utils {
  public final static int perceptRadius = 50;
  
  @Pure
  public static Vector3D getRandomPositionInZone(final Geometry zone) {
    RandomPointsBuilder factory = new RandomPointsBuilder();
    factory.setExtent(zone);
    factory.setNumPoints(1);
    Coordinate p = factory.getGeometry().getCoordinate();
    return new Vector3D(p.x, p.y, 0);
  }
  
  public static Polygon createCircle(final double radius, final Coordinate center) {
    final GeometricShapeFactory shapeFactory = new GeometricShapeFactory();
    Coordinate _coordinate = new Coordinate(center.x, center.y);
    shapeFactory.setCentre(_coordinate);
    shapeFactory.setNumPoints(16);
    shapeFactory.setSize((radius * 2));
    return shapeFactory.createCircle();
  }
  
  @SyntheticMember
  public Utils() {
    super();
  }
}
