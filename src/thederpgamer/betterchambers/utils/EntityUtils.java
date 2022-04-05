package thederpgamer.betterchambers.utils;

import com.bulletphysics.linearmath.Transform;
import org.schema.common.util.linAlg.Vector3fTools;
import org.schema.game.common.controller.SegmentController;

import javax.vecmath.Vector3f;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/26/2022]
 */
public class EntityUtils {

	public static float getDistance(SegmentController entityA, SegmentController entityB) {
		Transform transformA = new Transform(entityA.getWorldTransform());
		Transform transformB = new Transform(entityB.getWorldTransform());
		Vector3f posA = transformA.origin;
		Vector3f posB = transformB.origin;
		//if(!entityA.getSystem(new Vector3i()).equals(entityB.getSystem(new Vector3i()))) diff = 15 * ((Integer) ServerConfig.SECTOR_SIZE.getCurrentState());
		return Math.abs(Vector3fTools.distance(posA.x, posA.y, posA.z, posB.x, posB.y, posB.z));
	}
}
