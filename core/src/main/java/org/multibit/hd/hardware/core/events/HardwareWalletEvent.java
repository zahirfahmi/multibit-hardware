package org.multibit.hd.hardware.core.events;

import com.google.common.base.Optional;
import com.google.protobuf.Message;

/**
 * <p>Event to provide the following to application API:</p>
 * <ul>
 * <li>Notification of a hardware event (system or protocol)</li>
 * </ul>
 * <p>Messages wrap the raw data from the specific hardware wallet (e.g. initialise, reset etc)</p>
 * <p>If a message is not present then the event wraps the general state of a hardware wallet (e.g. connected, disconnected etc)</p>
 *
 * @since 0.0.1
 *  
 */
public class HardwareWalletEvent {

  private final HardwareWalletMessageType messageType;

  private final Optional<Message> message;

  /**
   * @param messageType The message type
   * @param message     The protocol buffer message from the wire
   */
  public HardwareWalletEvent(HardwareWalletMessageType messageType, Optional<Message> message) {

    this.messageType = messageType;
    this.message = message;
  }

  /**
   * @return The protocol message type
   */
  public HardwareWalletMessageType getMessageType() {
    return messageType;
  }

  /**
   * @return The protocol buffer message from the wire if present
   */
  public Optional<Message> getMessage() {
    return message;
  }

  /**
   * <p>Convenience method to detect a failed device</p>
   *
   * @return True if the device has failed to complete an operation or is no longer communicating in a timely manner
   */
  public boolean isFailed() {
    return HardwareWalletMessageType.DEVICE_FAILED.equals(messageType);
  }

  /**
   * <p>Convenience method to detect a disconnected device</p>
   *
   * @return True if the device is disconnected
   */
  public boolean isDisconnected() {
    return HardwareWalletMessageType.DEVICE_DISCONNECTED.equals(messageType);
  }

}
