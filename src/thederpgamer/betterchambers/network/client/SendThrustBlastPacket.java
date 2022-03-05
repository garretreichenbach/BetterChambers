package thederpgamer.betterchambers.network.client;

import api.common.GameCommon;
import api.network.Packet;
import api.network.PacketReadBuffer;
import api.network.PacketWriteBuffer;
import api.utils.game.SegmentControllerUtils;
import org.schema.game.common.controller.ManagedUsableSegmentController;
import org.schema.game.common.controller.PlayerUsableInterface;
import org.schema.game.common.controller.elements.effectblock.EffectAddOn;
import org.schema.game.common.controller.elements.power.reactor.tree.ReactorElement;
import org.schema.game.common.data.element.ElementKeyMap;
import org.schema.game.common.data.player.PlayerState;
import org.schema.schine.network.objects.Sendable;

import java.io.IOException;

/**
 * <Description>
 *
 * @author TheDerpGamer
 * @version 1.0 - [03/05/2022]
 */
public class SendThrustBlastPacket extends Packet {

	private int controllerId;

	public SendThrustBlastPacket() {

	}

	public SendThrustBlastPacket(ManagedUsableSegmentController<?> segmentController) {
		this.controllerId = segmentController.getId();
	}

	@Override
	public void readPacketData(PacketReadBuffer packetReadBuffer) throws IOException {
		controllerId = packetReadBuffer.readInt();
	}

	@Override
	public void writePacketData(PacketWriteBuffer packetWriteBuffer) throws IOException {
		packetWriteBuffer.writeInt(controllerId);
	}

	@Override
	public void processPacketOnClient() {

	}

	@Override
	public void processPacketOnServer(PlayerState playerState) {
		Sendable sendable = GameCommon.getGameObject(controllerId);
		if(sendable instanceof ManagedUsableSegmentController<?>) {
			ManagedUsableSegmentController<?> segmentController = (ManagedUsableSegmentController<?>) sendable;
			ReactorElement reactorElement = SegmentControllerUtils.getChamberFromElement(segmentController, ElementKeyMap.getInfo(1057));
			if(reactorElement != null && reactorElement.isAllValid()) {
				PlayerUsableInterface playerUsable = segmentController.getManagerContainer().getPlayerUsable(PlayerUsableInterface.USABLE_ID_TAKE_OFF);
				if(playerUsable instanceof EffectAddOn) {
					EffectAddOn effectAddOn = (EffectAddOn) playerUsable;
					effectAddOn.setAutoChargeOn(true);
					if(effectAddOn.canExecute()) effectAddOn.executeModule();
				}
			}
		}
	}
}
