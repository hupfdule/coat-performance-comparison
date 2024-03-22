package io.github.joeljeremy7.java.config.lib.benchmarks;

import de.poiu.coat.annotation.Coat;
import java.time.LocalDate;
import java.util.List;


@Coat.Config
public interface Config {
  public String test1();

  public int testInt1();

  public List<LocalDate> listOfDates();

  @Coat.Embedded
  public MqttConfig mqtt();
}
