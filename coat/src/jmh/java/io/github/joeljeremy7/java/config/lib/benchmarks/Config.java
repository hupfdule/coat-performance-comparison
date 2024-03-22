package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.annotation.Coat;
import java.time.LocalDate;
import java.util.List;


@Coat.Config
public interface Config extends SimpleConfig {

  public List<LocalDate> listOfDates();

  @Coat.Embedded
  public MqttConfig mqtt();
}
