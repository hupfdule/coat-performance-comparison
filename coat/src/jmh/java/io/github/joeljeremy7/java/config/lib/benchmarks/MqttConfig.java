package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.annotation.Coat;
import java.net.InetAddress;

@Coat.Config
public interface MqttConfig {

  public InetAddress hostName();
}
