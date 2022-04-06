package thederpgamer.betterchambers.network.server;

import api.common.GameClient;
import api.common.GameCommon;
import api.network.Packet;
import api.network.PacketReadBuffer;
import api.network.PacketWriteBuffer;
import com.bulletphysics.linearmath.Transform;
import org.schema.game.common.controller.SegmentController;
import org.schema.game.common.data.player.PlayerState;
import org.schema.schine.graphicsengine.core.GlUtil;
import org.schema.schine.network.objects.Sendable;

import javax.vecmath.Vector3f;
import javax.vecmath.Vector4f;
import java.io.IOException;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [04/05/2022]
 */
public class SendAuraPulsePacket extends Packet {

	private Sendable source;
	private float radius;
	private long usableId;

	public SendAuraPulsePacket() {

	}

	public SendAuraPulsePacket(Sendable source, float radius, long usableId) {
		this.source = source;
		this.radius = radius;
		this.usableId = usableId;
	}

	@Override
	public void readPacketData(PacketReadBuffer packetReadBuffer) throws IOException {
		source = packetReadBuffer.readSendable();
		radius = packetReadBuffer.readFloat();
		usableId = packetReadBuffer.readLong();
	}

	@Override
	public void writePacketData(PacketWriteBuffer packetWriteBuffer) throws IOException {
		packetWriteBuffer.writeSendable(source);
		packetWriteBuffer.writeFloat(radius);
		packetWriteBuffer.writeLong(usableId);
	}

	@Override
	public void processPacketOnClient() {
		if(GameClient.getClientState() != null) {
			try {
				SegmentController segmentController = (SegmentController) source;
				Transform transform = new Transform(segmentController.getWorldTransform());
				Vector3f forward = new Vector3f();
				Vector3f reverse = new Vector3f();
				GlUtil.getForwardVector(forward, transform);
				GlUtil.getBackVector(reverse, transform);
				forward.sub(reverse);
				Vector3f direction = new Vector3f(forward);
				Vector3f color = GameCommon.getGameState().getFactionManager().getRelation(GameClient.getClientPlayerState().getFactionId(), segmentController.getFactionId()).defaultColor;
				GameClient.getClientState().getPulseController().addPushPulse(segmentController.getWorldTransform(), direction, segmentController, 0.0f, radius, usableId, new Vector4f(color.x, color.y, color.z, 0.75f));
			} catch(Exception ignored) { }
		}
	}

	@Override
	public void processPacketOnServer(PlayerState playerState) {

	}
}
